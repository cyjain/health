package com.health.dao;

import com.github.pagehelper.Page;
import com.health.pojo.CheckItem;

import java.util.List;

/**
 *  持久层dao接口
 */
public interface CheckItemDao {
    /**
     * 新增
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 条件查询
     * @param queryString
     * @return Page<CheckItem>
     */
    Page<CheckItem> selectByCondition(String queryString);

    long findCountByCheckItemId(Integer id);

    void deleteById(Integer id);

    //编辑检查项
    void edit(CheckItem checkItem);

    //根据id查询检查项
    CheckItem findById(Integer id);

    //查询所有
    List<CheckItem> findAll();
}
