package com.health.dao;

import com.github.pagehelper.Page;
import com.health.pojo.CheckGroup;
import com.health.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * 持久层dao接口
 */
public interface SetmealDao {
    //新增
    void add(Setmeal setmeal);

    //检查套餐和检查组关联
    void setSetmealAndCheckGroup(Map map);

    //分页
    Page<Setmeal> findByCondition(String queryString);

    //查询所有套餐
    List<Setmeal> findAll();

    Setmeal findById(int id);
}
