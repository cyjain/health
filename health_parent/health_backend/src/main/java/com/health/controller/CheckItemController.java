package com.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.constant.MessageConstant;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.Result;
import com.health.pojo.CheckItem;
import com.health.service.CheckItemService;
import org.apache.poi.util.Internal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 体检检查项
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    //查找服务
    @Reference
    private CheckItemService checkItemService;

    /**
     * 新增检查项
     * @param checkItem
     * @return Resut
     */
    @RequestMapping("/add")
    public Result add(@RequestBody  CheckItem checkItem){
        try {
            checkItemService.add(checkItem);

        }catch (Exception e){
            e.printStackTrace();
            //调用服务失败
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);//新增检查项失败
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);//新增检查项成功
    }

    /**
     * 新增检查项
     * @param queryPageBean
     * @return pageResult
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult pageResult = checkItemService.pageQuery(queryPageBean);
       return pageResult;
    }

    /**
     * 删除检查项
     * @param id
     * @return Result
     */
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkItemService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    /**
     * 编辑检查项
     * @param checkItem
     * @return Result
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.edit(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * 根据id查询检查项
     * @param id
     * @return Result
     */
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_SUCCESS);
        }
    }

    /**
     * 查询所有检查项
     * @return Result
     */
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<CheckItem> list = checkItemService.findAll();
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_SUCCESS);
        }
    }
}
