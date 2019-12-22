package com.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.container.page.PageHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.dao.CheckItemDao;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.pojo.CheckItem;
import com.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    //注入dao
    @Autowired
    private CheckItemDao checkItemDao;

    //新增
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 检查项分页查询
     * @param queryPageBean
     * @return PageResult
     */
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //完成分页查询，基于mybatis框架提供的分页插件完成
        //selet * form t_checkitem limit 0,10
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total,rows);
    }

    /**
     * 根据id删除检查项
     * @param id
     */
    public void deleteById(Integer id) {
        //判断当前检查项是否已关联检查组
        long count = checkItemDao.findCountByCheckItemId(id);
        if (count > 0){
            new RuntimeException();
        }
        checkItemDao.deleteById(id);
    }

    /**
     * 编辑检查项
     * @param checkItem
     */
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    /**
     * 根据id查询检查项
     * @param id
     * @return checkitem
     */
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    /**
     * 查询所有
     * @return List<CheckItem>
     */
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
