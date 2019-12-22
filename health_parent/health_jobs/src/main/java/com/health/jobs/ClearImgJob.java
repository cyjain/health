package com.health.jobs;

import com.health.constant.RedisConstant;
import com.health.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 自定义job,实现定时清理图片垃圾
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;

    /**
     * 定时清理图片垃圾
     */
    public void clearImg(){

        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if (set != null){
            for (String picName : set) {
                //删除七牛云服务器上的图片
                QiniuUtils.deleteFileFromQiniu(picName);
                //删除Redis中的图片
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,picName);
                System.out.println("自定义删除垃圾图片"+picName);
            }
        }
    }
}
