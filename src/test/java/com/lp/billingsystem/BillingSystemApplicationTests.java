package com.lp.billingsystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.billingsystem.config.redis.RedisCache;
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
    @Test
    void contextLoads() throws JsonProcessingException {
        HashMap map = new HashMap();
        map.put("11111111",222222222);
        map.put("2222",22241242);
        map.put("3333","231412");
        ObjectMapper mapper = new ObjectMapper();

        redisCache.setCacheObject("111111",mapper.writeValueAsString(map));
        String cacheObject = redisCache.getCacheObject("111111");
        System.out.println("cacheObject = " + cacheObject);
        HashMap map1 = mapper.readValue(cacheObject, HashMap.class);
        System.out.println("map1 = " + map1);
        log.error("111111111");
    }

}
