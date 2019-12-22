package com.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.health.dao.OrderSettingDao;
import com.health.pojo.OrderSetting;
import com.health.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预约设置接口
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;
    /**
     * 批量导入数据到数据库
     * @param list
     */
    public void add(List<OrderSetting> list) {
        if (list !=null && list.size() > 0){
            for (OrderSetting orderSetting : list) {
                //判断当前日期是否进行了设置
                long countByOrderDate = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (countByOrderDate > 0){
                    //已进行了预约设置,进行更新
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                }else {
                    //没有执行操作,执行插入
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    /**
     * 根据日期查询预约数据
     * @param date
     * @return
     */
    public List<Map> getOrderSettingByMonth(String date) {//日期格式：yyyy-mm
        String dateBean = date + "-1";//2019-12-1
        String dateEnd = date + "-31";//2019-12-30
        Map<String,String> map = new HashMap<String, String>();
        map.put("dateBean",dateBean);
        map.put("dateEnd",dateEnd);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> result = new ArrayList<Map>();
        if (list !=null && list.size() > 0){
            for (OrderSetting orderSetting : list) {
                Map<String, Object> m = new HashMap<String, Object>();
                m.put("date",orderSetting.getOrderDate().getDate());//获取当前的时间（几号）
                m.put("number",orderSetting.getNumber());
                m.put("reservations",orderSetting.getReservations());
                result.add(m);
            }
        }
        return result;
    }

    /**
     * 根据日期修改可预约人数
     * @param orderSetting
     */
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0){
            //当前预约已经进行了预约设置,需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else {
            //当前预约没有进行预约设置,进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}
