package com.health.service;

import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.pojo.CheckItem;

import java.util.List;

/**
 * 检查服务项接口
 */
public interface CheckItemService {
    /**
     * 新增检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    //分页查询
    PageResult pageQuery(QueryPageBean queryPageBean);

    //删除检查项
    void deleteById(Integer id);

    //编辑检查项
    void edit(CheckItem checkItem);

    //根据id查询检查项
    CheckItem findById(Integer id);

    //查询所有
    List<CheckItem> findAll();
}
