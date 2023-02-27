package com.lp.billingsystem;

import com.lp.billingsystem.bills.HuangguanBills;
import com.lp.billingsystem.bills.YaxingBills;
import com.lp.billingsystem.config.spring.SpringContextHolder;
import com.lp.billingsystem.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.*;

@SpringBootApplication
@MapperScan("com.lp.billingsystem.mapper")
@Log4j2
public class BillingSystemApplication {

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }


    public static void main(String[] args) {
        SpringApplication.run(BillingSystemApplication.class, args);
       log.info("启动成功");
        YaxingBills yaxing = SpringContextHolder.getBean(YaxingBills.class);
        HuangguanBills huangguanBills = SpringContextHolder.getBean(HuangguanBills.class);


//        yaxing.bills();
//        huangguanBills.bills();
        yaxing.writeYaxin2();
//        huangguanBills.write00();
        System.out.println("运行完毕");
//        huangguanBills.bills01(1);
    }


}
