package com.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.health.constant.MessageConstant;
import com.health.dao.MemberDao;
import com.health.dao.OrderDao;
import com.health.dao.OrderSettingDao;
import com.health.entity.Result;
import com.health.pojo.Member;
import com.health.pojo.Order;
import com.health.pojo.OrderSetting;
import com.health.service.OrderService;
import com.health.utils.DateUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

//体检预约服务
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSettingDao orderSettingDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;

    /**
     * 体检预约
     * @param map
     * @return result
     * @throws Exception
     */
    public Result order(Map map) throws Exception{
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(date);
        if (orderSetting == null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        //检查预约人数是否约满
        int number = orderSetting.getNumber();//可预约人数
        int reservations = orderSetting.getReservations();//已预约人数
        if (number >= reservations){
            //预约已满,不能预约
            return new Result(false,MessageConstant.ORDER_FULL);
        }

        //检查当前用户是否是会员,根据手机号码来判断
        String telephone = (String) map.get("telephone");
        Member member = memberDao.findByTelephone(telephone);
        //防止重复预约
        if (member != null){
            Integer menberId = member.getId();
            int setmealId = Integer.parseInt((String) map.get("setmealId"));
            Order order = new Order(menberId, date, null, null, setmealId);
            List<Order> list = orderDao.findByCondition(order);
            if (list != null && list.size() > 0 ){
                //已经完成预约,不能重复预约
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }
        //可以预约,预约人数加一
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingDao.editNumberByOrderDate(orderSetting);

        if (member != null){
            //当前用户不是会员，需要添加到会员表
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberDao.add(member);
        }
        //保存预约信息到预约列表
        Order order = new Order(member, date, (String) map.get("orderType"), Order.ORDERSTATUS_NO, Integer.parseInt((String) map.get("setmealid")));
        orderDao.add(order);
        return new Result(true,MessageConstant.ORDERSETTING_SUCCESS,order.getId());
    }

    /**
     * 查询预约
     * @param id
     * @return
     */
    public Map findById(Integer id) throws Exception{
        Map map = orderDao.findById4Detail(id);
        //处理日期格式
        if (map != null){
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate",DateUtils.parseDate2String(orderDate));
        }
        return map;
    }
}
