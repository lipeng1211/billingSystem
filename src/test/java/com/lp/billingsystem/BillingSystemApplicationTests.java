package com.lp.billingsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.billingsystem.config.redis.RedisCache;
import com.lp.billingsystem.config.spring.SpringContextHolder;
import com.lp.billingsystem.domain.HuangguanDaima;
import com.lp.billingsystem.domain.LpUsernameDaima;
import com.lp.billingsystem.service.HuangguanDaimaService;
import com.lp.billingsystem.service.LpUsernameDaimaService;
import com.lp.billingsystem.service.LpUsernameService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Log4j2
class BillingSystemApplicationTests {



    @Autowired
    LpUsernameDaimaService lpUsernameDaimaService;

//
//    @Autowired
//    LpUsernameService lpUsernameService;
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

    @Test
    void contextLoads() throws JsonProcessingException {


        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username_id","1");
        wrapper.eq("is_del",false);
        List<LpUsernameDaima> list = lpUsernameDaimaService.list(wrapper);
        System.out.println(list);
    }

}
