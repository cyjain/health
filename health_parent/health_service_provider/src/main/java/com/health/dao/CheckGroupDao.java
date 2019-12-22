package com.health.dao;

import com.github.pagehelper.Page;
import com.health.pojo.CheckGroup;
import com.health.pojo.CheckItem;

import java.util.List;
import java.util.Map;

/**
 * 持久层dao接口
 */
public interface CheckGroupDao {
    //新增
    void add(CheckGroup checkGroup);

    //检查项和检查组关联
    void setCheckGroupAndCheckItem(Map map);

    //查询分页
    Page<CheckGroup> findByCondition(String queryString);

    //根据id查询检查组
    CheckGroup findById(Integer id);

    //根据检查组合id查询对应的检查项id
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    //编辑修改检查组信息
    void edit(CheckGroup checkGroup);

    //编辑检查组信息，同时需关联检查项
    void deleteAssoication(Integer id);

    //查询检查组
    List<CheckGroup> findAll();
}
