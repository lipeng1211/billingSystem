package com.lp.billingsystem.bills;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.lp.billingsystem.config.redis.RedisCache;
import com.lp.billingsystem.config.redis.RedisKey;
import com.lp.billingsystem.domain.User;
import com.lp.billingsystem.domain.Yaxing;
import com.lp.billingsystem.service.UserService;
import com.lp.billingsystem.service.YaxingService;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static java.awt.SystemColor.text;

@Component
@Log4j2
public class YaxingBills {



    @Autowired(required=true)
    RedisCache redisCache;
    @Autowired(required=true)
    UserService userService;

    @Autowired
    YaxingService yaxingService;
    
    //亚星
    public void bills() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.isNull("account_number");
        List<User> list = userService.list(wrapper);
        for (User user: list) {
            Configuration.browserSize = "1280x800";
            SelenideLogger.addListener("allure", new AllureSelenide());
//        open("https://www.yaxin868.net/credit/login.jsp");

            open(user.getUrl());
            $("#txtUsername").sendKeys(user.getUsername());
            $("#txtPassword").sendKeys(user.getPassword());

//        mainPage.searchButton.click();
//
//        $("[data-test='search-input']").sendKeys("Selenium");
//        $("button[data-test='full-search-button']").click();  ZZPE90    Jun112233
//        xfft5j 密码 aaakkk222

            //        sky1212  aabb1122
//
//        $("input[data-test='search-input']").shouldHave(attribute("value", "Selenium"));
//        LocalDateTime now = LocalDateTime.now();
            LocalDate now = LocalDate.now();
            DayOfWeek dayOfWeek = now.getDayOfWeek();
//        LocalDate monday = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1);
//        LocalDate sunday = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusDays(1);
            LocalDate monday = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusWeeks(-1).plusDays(1);
            LocalDate sunday = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).plusWeeks(-1).minusDays(0);
//        LocalDateTime monday = now.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusWeeks(-1).plusDays(1).withHour(12).withMinute(0).withSecond(0);
//        LocalDateTime sunday = now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).plusWeeks(-1).minusDays(1).withHour(11).withMinute(59).withSecond(59);

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime tdate = LocalDateTime.now();
            String format = tdate.format(dateTimeFormatter);
            Boolean isTrue = true;
//        Jedis jedis = RedisConnection.getJedis();
            ArrayList<Yaxing> yaxings = new ArrayList<>();
            Yaxing yaxing = new Yaxing();
            int i1 = 0;
            while (isTrue){
                String winlosereport = null;
                int i = 0;
                while ($("li[class='menu_n']",i).exists()){
                    log.info($("li[class='menu_n']",i).toString());
                    if ($("li[class='menu_n']",i).attr("url").indexOf("winlosereport.jsp") != -1){
                        winlosereport = $("li[class='menu_n']",i).attr("url");
                    }
                    i++;
                }

                if (winlosereport !=null){
                    open(user.getUrl()+winlosereport);
                    sleep(2000);
                    $("#dateSpan2").click();
                    sleep(200);
                    //上周
                    $("option[value='lastWeek1']").click();
                    //上月
//                    $("option[value='lastMonth1']").click();
                    sleep(5000);
                    //正常来说有两个body
                    //第一个body存在
                    if ($("table", 0).exists()){
                        //取出表头
//                    .get(15).getText();
                        List<WebElement> thead = $("table", 0).findElements(By.tagName("thead")).get(0).findElements(By.tagName("td"));

//                        for (int j = 1; j < thead.size(); j++) {
//                            String text = thead.get().getText();

                            log.info("text::{}",text);
//                        }

//                    取出内容 遍历每行
                        List<WebElement> tbody = $("table", 0).findElements(By.tagName("tbody"));
                        for (int j = 0; j < tbody.size(); j++) {
//                        String text = tbody.get(j).getText();
                            List<WebElement> td = tbody.get(j).findElements(By.tagName("td"));
                            i1 = 1;
                            yaxing = new Yaxing();
                            yaxing.setJb(td.get(i1++).getText());
                            yaxing.setZh(td.get(i1++).getText());
                            yaxing.setLx(td.get(i1++).getText());
                            yaxing.setBs(td.get(i1++).getText());
                            yaxing.setTzje(td.get(i1++).getText());
                            yaxing.setZxml(td.get(i1++).getText());
                            yaxing.setSyje(td.get(i1++).getText());
                            yaxing.setGdzcb(td.get(i1++).getText());
                            yaxing.setJsxsy(td.get(i1++).getText());
                            yaxing.setJsxxml(td.get(i1++).getText());
                            yaxing.setXmb(td.get(i1++).getText());
                            yaxing.setXmyj(td.get(i1++).getText());
                            yaxing.setJsxjg(td.get(i1++).getText());
                            yaxing.setHll(td.get(i1++).getText());
                            yaxing.setGdjg(td.get(i1++).getText());
                            yaxing.setAccountNumber(user.getUsername());
                            yaxing.setCreatedate(new Date());
                            yaxing.setType("1");
                            yaxing.setNumberOfLayers(1);
                            yaxings.add(yaxing);

                            log.info("yaxing::{}",yaxing);
//                            }
                        }

                    }

                    //第二个body存在
                    if ($("table", 1).exists()){
                        log.info("开始第二个");
                        List<WebElement> thead = $("table", 1).findElements(By.tagName("thead")).get(0).findElements(By.tagName("td"));
//                        for (int j = 1; j < thead.size(); j++) {
//                            String text = thead.get(j).getText();
//                            log.info("text::{}",text);
//                        }
//                    取出内容 遍历每行
                        List<WebElement> tbody = $("table", 1).findElements(By.tagName("tbody"));
                        for (int j = 0; j < tbody.size(); j++) {
//                        String text = tbody.get(j).getText();
                            List<WebElement> td = tbody.get(j).findElements(By.tagName("td"));
                            i1 = 1;
                            yaxing = new Yaxing();
                            yaxing.setJb(td.get(i1++).getText());
                            yaxing.setZh(td.get(i1++).getText());
                            yaxing.setBm(td.get(i1++).getText());
                            yaxing.setLx(td.get(i1++).getText());
                            yaxing.setBs(td.get(i1++).getText());
                            yaxing.setTzje(td.get(i1++).getText());
                            yaxing.setZxml(td.get(i1++).getText());
                            yaxing.setSyje(td.get(i1++).getText());
                            yaxing.setZdlzcb(td.get(i1++).getText());
                            yaxing.setJsxsy(td.get(i1++).getText());
                            yaxing.setJsxxml(td.get(i1++).getText());
                            yaxing.setXmb(td.get(i1++).getText());
                            yaxing.setXmyj(td.get(i1++).getText());
                            yaxing.setJsxjg(td.get(i1++).getText());
                            yaxing.setHll(td.get(i1++).getText());
                            yaxing.setGdjg(td.get(i1++).getText());
                            yaxing.setCreatedate(new Date());
                            yaxing.setAccountNumber(user.getUsername());
                            yaxing.setNumberOfLayers(1);
                            yaxing.setType("1");
                            yaxings.add(yaxing);

                        }

                    }

                    QueryWrapper wrapper2 = new QueryWrapper();
                    wrapper2.eq("account_number",user.getUsername());
                    List<User> list2 = userService.list(wrapper2);
                    HashMap map = new HashMap();
                    for (User user2:list2
                         ) {
                        //第二个body存在
                        if ($("table", 1).exists()){

//                    取出内容 遍历每行
                            List<WebElement> tbody = $("table", 1).findElements(By.tagName("tbody"));
                            for (int j = 0; j < tbody.size(); j++) {
//                        String text = tbody.get(j).getText();
                                List<WebElement> td = tbody.get(j).findElements(By.tagName("td"));

                                String daima = td.get(2).getText();

                                log.info("代码:::{}",td.get(2).getText());

                                //需要进入明细
                                if (daima.equals(user2.getUsername().toUpperCase()) || daima.equals(user2.getUsername().toLowerCase())){
                                    if (map.get(user2.getUsername())!=null){
                                        continue;
                                    }
                                    td.get(0).click();
                                    sleep(5000);


                                    //二层第二个body存在
                                    if ($("table", 1).exists()){
                                        //取出表头
//                    .get(15).getText();
                                        List<WebElement> thead = $("table", 1).findElements(By.tagName("thead")).get(0).findElements(By.tagName("td"));
                                        for (int k = 1; k < thead.size(); k++) {
                                            String text = thead.get(k).getText();
                                            log.info("text::{}",text);
                                        }

//                    取出内容 遍历每行
                                        List<WebElement> tbody2 = $("table", 1).findElements(By.tagName("tbody"));
                                        for (int k = 0; k < tbody2.size(); k++) {
//                        String text = tbody.get(j).getText();
                                            td = tbody2.get(k).findElements(By.tagName("td"));
                                            i1 = 1;
                                            yaxing = new Yaxing();
                                            yaxing.setJb(td.get(i1++).getText());
                                            yaxing.setZh(td.get(i1++).getText());
                                            yaxing.setBm(td.get(i1++).getText());
                                            yaxing.setLx(td.get(i1++).getText());
                                            yaxing.setBs(td.get(i1++).getText());
                                            yaxing.setTzje(td.get(i1++).getText());
                                            yaxing.setZxml(td.get(i1++).getText());
                                            yaxing.setSyje(td.get(i1++).getText());
                                            yaxing.setZdlzcb(td.get(i1++).getText());
                                            yaxing.setJsxsy(td.get(i1++).getText());
                                            yaxing.setJsxxml(td.get(i1++).getText());
                                            yaxing.setXmb(td.get(i1++).getText());
                                            yaxing.setXmyj(td.get(i1++).getText());
                                            yaxing.setJsxjg(td.get(i1++).getText());
                                            yaxing.setHll(td.get(i1++).getText());
                                            yaxing.setGdjg(td.get(i1++).getText());
                                            yaxing.setCreatedate(new Date());
                                            yaxing.setAccountNumber(user.getUsername());
                                            yaxing.setType("1");
                                            yaxing.setNumberOfLayers(2);
                                            yaxings.add(yaxing);

//                                            for (int l = 1; l < td2.size(); l++) {
//                                                String text = td2.get(l).getText();
//                                                log.info("text::{}",text);
//                                            }
                                        }

                                    }
                                    map.put(user2.getUsername(),true);

                                    back();
                                    sleep(3000);
                                    break;
                                }


                            }


                        }


                    }

                    yaxingService.saveBatch(yaxings);

                }

                sleep(5000);

            }

        }
    }

    public void test(){
        redisCache.setCacheObject("11",11);

//        System.out.println("list = " + list);
    }
}
