package com.lp.billingsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.billingsystem.config.redis.RedisCache;
import com.lp.billingsystem.domain.HuangguanDaima;
import com.lp.billingsystem.service.HuangguanDaimaService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;

@SpringBootTest
@Log4j2
class BillingSystemApplicationTests {


    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisCache redisCache;

    @Autowired
    HuangguanDaimaService huangguanDaimaService;
    @Test
    void contextLoads() throws JsonProcessingException {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.likeLeft("zh","xb27777");
        HuangguanDaima one = huangguanDaimaService.getOne(wrapper);
        System.out.println(one);
    }

}
