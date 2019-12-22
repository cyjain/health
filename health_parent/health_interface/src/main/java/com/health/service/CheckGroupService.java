package com.health.service;

import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.pojo.CheckGroup;

import java.util.List;

/**
 * 检查组服务接口
 */
public interface CheckGroupService {

    //新增
    void add(CheckGroup checkGroup,Integer[] checkitemIds);

    //分页查询
    PageResult pageQuery(QueryPageBean queryPageBean);

    //根据id查询
    CheckGroup findById(Integer id);

    //根据检查组合id查询对应的检查项id
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    //编辑
    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    //查询检查组
    List<CheckGroup> findAll();
}
