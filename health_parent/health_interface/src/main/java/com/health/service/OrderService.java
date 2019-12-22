package com.health.service;

import com.health.entity.Result;

import java.util.Map;

public interface OrderService {
    Result order(Map map) throws Exception;

    //查询预约
    Map findById(Integer id) throws Exception;
}
