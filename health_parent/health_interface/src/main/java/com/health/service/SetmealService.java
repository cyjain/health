package com.health.service;

import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.pojo.Setmeal;

import java.util.List;

/**
 * 体检套餐服务接口
 */
public interface SetmealService {
    //新增套餐
    void add(Setmeal setmeal,Integer[] checkgroupIds);

    //分页查询
    PageResult findPage(QueryPageBean queryPageBean);

    //查询所有套餐
    List<Setmeal> findAll();

    Setmeal findById(int id);
}
