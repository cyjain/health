package com.health.service;

import com.health.pojo.OrderSetting;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Map;

/**
 * 预约设置
 */
public interface OrderSettingService {
    void add(List<OrderSetting> date);

    List<Map> getOrderSettingByMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
