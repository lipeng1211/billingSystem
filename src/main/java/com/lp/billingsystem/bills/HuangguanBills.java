package com.lp.billingsystem.bills;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.lp.billingsystem.config.redis.RedisCache;
import com.lp.billingsystem.domain.Huangguan;
import com.lp.billingsystem.domain.HuangguanDaima;
import com.lp.billingsystem.domain.User;
import com.lp.billingsystem.domain.Yaxing;
import com.lp.billingsystem.service.HuangguanDaimaService;
import com.lp.billingsystem.service.HuangguanService;
import com.lp.billingsystem.service.UserService;
import com.lp.billingsystem.util.DateUtil;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
@Component
public class HuangguanBills {


    @Autowired(required=true)
    RedisCache redisCache;
    @Autowired(required=true)
    UserService userService;
    @Autowired(required=true)
    HuangguanService huangguanService;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public void bills() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.isNull("account_number");
        wrapper.eq("is_del",false);
        wrapper.eq("type","2");
        List<User> list = userService.list(wrapper);

        QueryWrapper queryWrapper = new QueryWrapper();
        for (User user: list
             ) {
            Huangguan huangguan = new Huangguan();
            ArrayList<Huangguan> huangguans = new ArrayList<>();
            try {
                Configuration.browserSize = "1280x800";
                SelenideLogger.addListener("allure", new AllureSelenide());

                open("http://205.201.2.203");
//        open("https://www.yaxin868.net/credit/login.jsp");
//        Ys301b 账号  YS301ABC  密码   Aa5599876 安全码 Asdf9988
                $("#username" ).sendKeys(user.getUsername());
                $("#pwd" ).sendKeys(user.getPassword());
                $("#pwd_safe" ).sendKeys(user.getSecurityCode());


//        $("#username" ).sendKeys("YS301B");
//        $("#pwd" ).sendKeys("Jun112233");
//        $("#pwd_safe" ).sendKeys("Asdf998877");

//                Thread.sleep(2000);
                log.info("开始登录");
//                $("#loginBtn" ).click();
//                Thread.sleep(2000);
                if ($("#loginBtn" ).exists()){
                    $("#loginBtn" ).click();
                }
                Wait().until(ExpectedConditions.elementToBeClickable(By.id("notice_close")));
                $("#notice_close").click();
//            Thread.sleep(5000);
//            log.info("判断弹窗是否存在");
//            if ($("#notice_close").exists()){
//                log.info("点击主页弹窗");
//
//            }

                Thread.sleep(2000);
                if ($("#anno_ok_btn" ).exists()){
                    $("#anno_ok_btn" ).click();
                }

                Thread.sleep(2000);
                //元素存在
                Wait().until(ExpectedConditions.elementToBeClickable(By.id("date_div")));
                $("#date_div" ).click();
                Thread.sleep(500);

                //上星期
                $("#date_lw" ).click();

                //本周
//                $("#date_tw" ).click();
                Thread.sleep(500);
                $("#search_btn" ).click();

                Thread.sleep(5000);
                int k = 0;
                Boolean isTrue = true;
                while (isTrue){


                    Wait().until(ExpectedConditions.elementToBeClickable(By.id("div_show")));
                    //开始爬取数据
                    SelenideElement selenideElement = $("#div_show");

                    //            System.out.println("selenideElement = " + selenideElement);
                    SelenideElement selenideElement1 = selenideElement.$(".re_headbg02G");
                    //            System.out.println("selenideElement1 = " + selenideElement1);
                    List<WebElement> div = selenideElement1.$(".re_div_moveG").findElements(By.tagName("div"));
                    //            System.out.println("div = " + div.size());
//                    System.out.println("股东 = " + div.get(0).getText());
//                    System.out.println("股东结果 = " + div.get(1).getText());
//                    System.out.println("股东实货量 = " + div.get(2).getText());
                    huangguan = new Huangguan();
                    HashMap map = new HashMap();
                    map.put("gudong",div.get(0).getText());
                    map.put("gudongnmame","YS301ABC");
                    map.put("gudongjieguo",div.get(1).getText());
                    map.put("gdshl",div.get(2).getText());

                    huangguan.setGd(div.get(0).getText());
                    huangguan.setZh(user.getUsername());
                    huangguan.setGdjg(div.get(1).getText());
                    huangguan.setGdshl(div.get(2).getText());

                    huangguan.setCreatedate(new Date());
                    huangguan.setAccountNumber(user.getUsername());
                    huangguan.setNumberOfLayers(1);
                    huangguan.setType("2");
                    huangguan.setStartDate(DateUtil.lastMonday());
                    huangguan.setEndDate(DateUtil.lastSunday());

                    queryWrapper =new QueryWrapper();
                    queryWrapper.eq("start_date", DateUtil.lastMonday().format(dateTimeFormatter));
                    queryWrapper.eq("end_date", DateUtil.lastSunday().format(dateTimeFormatter));
                    queryWrapper.eq("account_number",user.getUsername());
                    queryWrapper.eq("type",2);
                    queryWrapper.eq("zh",huangguan.getZh());

                    if (huangguanService.getOne(queryWrapper) == null){
                        if (huangguan.getZh() !=null)
                        huangguans.add(huangguan);
                    }


//                RedisOps.set(RedisKey.HG0_GUDONG+"YS301ABC", JSONObject.toJSONString(map));

                    //            SelenideElement selenideElement2 = selenideElement.$(".re_bodybg01G");
                    List<WebElement> re_bodybg01G = selenideElement.findElements(By.className("re_bodybg01G"));
                    System.out.println("re_bodybg01G = " + re_bodybg01G.size());
                    for (int i = 0; i < re_bodybg01G.size(); i++) {
                        WebElement word_link = re_bodybg01G.get(i).findElement(By.className("word_link"));

                        System.out.println("股东代码 " + word_link.getText());
                        WebElement re_div_moveG = re_bodybg01G.get(i).findElement(By.className("re_div_moveG"));
                        //                System.out.println("re_div_moveG = " + re_div_moveG.getText());
                        List<WebElement> div2 = re_div_moveG.findElements(By.tagName("div"));
                        //                System.out.println("div2 = " + div2.size());
//                        System.out.println("股东 = " + div2.get(0).getText());
//                        System.out.println("股东结果 = " + div2.get(1).getText());
//                        System.out.println("股东实货量 = " + div2.get(2).getText());
                        map = new HashMap();
                        map.put("gudong",div2.get(0).getText());
                        map.put("gudongnmame",word_link.getText());
                        map.put("gudongjieguo",div2.get(1).getText());
                        map.put("gdshl",div2.get(2).getText());
                        huangguan = new Huangguan();
                        huangguan.setGd(div2.get(0).getText());
                        huangguan.setZh(word_link.getText());
                        huangguan.setGdjg(div2.get(1).getText());
                        huangguan.setGdshl(div2.get(2).getText());

                        huangguan.setCreatedate(new Date());
                        huangguan.setAccountNumber(user.getUsername());
                        huangguan.setNumberOfLayers(1);
                        huangguan.setType("2");
                        huangguan.setStartDate(DateUtil.lastMonday());
                        huangguan.setEndDate(DateUtil.lastSunday());


                        queryWrapper =new QueryWrapper();
                        queryWrapper.eq("start_date", DateUtil.lastMonday().format(dateTimeFormatter));
                        queryWrapper.eq("end_date", DateUtil.lastSunday().format(dateTimeFormatter));
                        queryWrapper.eq("account_number",user.getUsername());
                        queryWrapper.eq("type",2);
                        queryWrapper.eq("zh",huangguan.getZh());

                        if (huangguanService.getOne(queryWrapper) == null){
                            if (huangguan.getZh() !=null)
                            huangguans.add(huangguan);
                        }


//                        huangguans.add(huangguan);
//                    RedisOps.set(RedisKey.HG0_DAILI+word_link.getText(), JSONObject.toJSONString(map));
                        isTrue = false;
                    }

                    //            System.out.println("selenideElement2 = " + selenideElement2.findElements());
                    //            SelenideElement selenideElement2 = selenideElement.$(".selenideElement :nth-child(2)");
                    //            System.out.println("selenideElement1 = " + selenideElement1);
                    //            System.out.println("selenideElement1 = " + selenideElement2);
                    System.out.println("========================================================================");

                }
                //存储点击过 的
                ArrayList arrayList = new ArrayList();
                isTrue = true;
                int num = 0;
                while (isTrue){

//                    executeJavaScript("document.getElementById('footer_show').scrollIntoView()");
//                    sleep(1000);
//                    executeJavaScript("document.getElementById('footer_show').scrollIntoView()");
//                    sleep(1000);
//                    executeJavaScript("document.getElementById('footer_show').scrollIntoView()");
//                    sleep(1000);
//                    executeJavaScript("document.getElementById('footer_show').scrollIntoView()");
//                    sleep(1000);
                    Wait().until(ExpectedConditions.elementToBeClickable(By.id("div_show")));
                    sleep(3000);
                    //开始爬取数据
                    SelenideElement selenideElement = $("#div_show");

                    //            System.out.println("selenideElement = " + selenideElement);
                    SelenideElement selenideElement1 = selenideElement.$(".re_headbg02G");
                    //            System.out.println("selenideElement1 = " + selenideElement1);
                    List<WebElement> div = selenideElement1.$(".re_div_moveG").findElements(By.tagName("div"));
                    //            System.out.println("div = " + div.size());
                    System.out.println("股东 = " + div.get(0).getText());
                    System.out.println("股东结果 = " + div.get(1).getText());
                    System.out.println("股东实货量 = " + div.get(2).getText());

                    HashMap map = new HashMap();
                    map.put("gudong",div.get(0).getText());
                    map.put("gudongnmame","YS301ABC");
                    map.put("gudongjieguo",div.get(1).getText());
                    map.put("gdshl",div.get(2).getText());
                    huangguan = new Huangguan();
                    log.info("这是啥：：：：：：：：：",map);
//                    huangguan.setGd();

//                    huangguans.add(huangguan);
//                RedisOps.set(RedisKey.HG0_GUDONG+"YS301ABC", JSONObject.toJSONString(map));

                    //            SelenideElement selenideElement2 = selenideElement.$(".re_bodybg01G");
                    List<WebElement> re_bodybg01G = selenideElement.findElements(By.className("re_bodybg01G"));
                    System.out.println("re_bodybg01G = " + re_bodybg01G.size());
                    for (int i = 0; i < re_bodybg01G.size(); i++) {
                        WebElement word_link = re_bodybg01G.get(i).findElement(By.className("word_link"));

                        System.out.println("股东代码 " + word_link.getText());
                        WebElement re_div_moveG = re_bodybg01G.get(i).findElement(By.className("re_div_moveG"));
                        //                System.out.println("re_div_moveG = " + re_div_moveG.getText());
                        List<WebElement> div2 = re_div_moveG.findElements(By.tagName("div"));
                        //                System.out.println("div2 = " + div2.size());
//                    System.out.println("股东 = " + div2.get(0).getText());
//                    System.out.println("股东结果 = " + div2.get(1).getText());
//                    System.out.println("股东实货量 = " + div2.get(2).getText());
                        map = new HashMap();
                        map.put("gudong",div2.get(0).getText());
                        map.put("gudongnmame",word_link.getText());
                        map.put("gudongjieguo",div2.get(1).getText());
                        map.put("gdshl",div2.get(2).getText());
                        log.info("map:::::::::{}",map);

                        if (!arrayList.contains(word_link.getText())){
                            num++;
                            arrayList.add(word_link.getText());
                            word_link.click();

                            Wait().until(ExpectedConditions.elementToBeClickable(By.id("div_show")));
                            sleep(2000);
                            $("#footer_show").scrollIntoView(false);
                            sleep(2000);
                            $("#footer_show").scrollIntoView(false);
                            sleep(2000);
                            $("#footer_show").scrollIntoView(false);
                            sleep(2000);
                            $("#re_body").scrollIntoView(true);
                            sleep(1000);
                            SelenideElement selenideElement2 = $("#div_show");
                            List<WebElement> re_bodybg01G2 = selenideElement2.findElements(By.className("re_bodybg01G"));
//                        System.out.println("re_bodybg01G = " + re_bodybg01G.size());
                            Wait().until(ExpectedConditions.elementToBeClickable(By.className("re_left_fixed")));

                            for (int i2 = 0; i2 < re_bodybg01G2.size(); i2++) {
//                            WebElement word_link2 = re_bodybg01G2.get(i2).findElement(By.className("word_link"));
                                List<WebElement> elements = selenideElement2.findElements(By.className("re_left_fixed")).get(0).findElements(By.name("user_btn"));
//                            System.out.println("股东代码 " + word_link2.getText());
                                Wait().until(ExpectedConditions.elementToBeClickable(By.className("re_div_moveG")));
                                WebElement re_div_moveG2 = re_bodybg01G2.get(i2).findElement(By.className("re_div_moveG"));
                                //                System.out.println("re_div_moveG = " + re_div_moveG.getText());
                                List<WebElement> div22 = re_div_moveG2.findElements(By.tagName("div"));
                                //                System.out.println("div2 = " + div2.size());
//                            System.out.println("股东 = " + div22.get(0).getText());
//                            System.out.println("股东结果 = " + div22.get(1).getText());
//                            System.out.println("股东实货量 = " + div22.get(2).getText());
                                map = new HashMap();
                                map.put("gudong",div22.get(0).getText());
                                map.put("gudongnmame",elements.get(i2).getText());
                                map.put("gudongjieguo",div22.get(1).getText());
                                map.put("gdshl",div22.get(2).getText());
                                log.info("map:::::::::{}",map);
                                huangguan = new Huangguan();
                                k = 0;
                                huangguan.setZh(elements.get(i2).getText());
                                huangguan.setZdljg(div22.get(k++).getText());
                                huangguan.setZdlshl(div22.get(k++).getText());
                                huangguan.setZdlbfb(div22.get(k++).getText());
                                huangguan.setGd(div22.get(k++).getText());
                                huangguan.setGdzc(div22.get(k++).getText());
                                huangguan.setGdjg(div22.get(k++).getText());
                                huangguan.setGdshl(div22.get(k++).getText());

                                huangguan.setCreatedate(new Date());
                                huangguan.setAccountNumber(user.getUsername());
                                huangguan.setNumberOfLayers(2);
                                huangguan.setType("2");
                                huangguan.setStartDate(DateUtil.lastMonday());
                                huangguan.setEndDate(DateUtil.lastSunday());

                                queryWrapper =new QueryWrapper();
                                queryWrapper.eq("start_date", DateUtil.lastMonday().format(dateTimeFormatter));
                                queryWrapper.eq("end_date", DateUtil.lastSunday().format(dateTimeFormatter));
                                queryWrapper.eq("account_number",user.getUsername());
                                queryWrapper.eq("type",2);
                                queryWrapper.eq("zh",huangguan.getZh());

                                if (huangguanService.getOne(queryWrapper) == null){
                                    if (huangguan.getZh() !=null)
                                    huangguans.add(huangguan);
                                }


//                                huangguans.add(huangguan);

//                    RedisOps.set(RedisKey.HG0_DAILI+word_link.getText(), JSONObject.toJSONString(map));
//                            isTrue = false;
                            }
                            Boolean isTrue2 = Boolean.TRUE;
                            int num3 = 0;
                            while (isTrue2){
                                Wait().until(ExpectedConditions.elementToBeClickable(By.id("div_show")));
                                sleep(3000);
                                $("#footer_show").scrollIntoView(false);
                                sleep(2000);
                                $("#footer_show").scrollIntoView(false);
                                sleep(2000);
                                $("#footer_show").scrollIntoView(false);
                                sleep(2000);
                                $("#re_body").scrollIntoView(true);
                                sleep(1000);
                                SelenideElement selenideElement3 = $("#div_show");
                                List<WebElement> re_bodybg01G3 = selenideElement3.findElements(By.className("re_bodybg01G"));
//                        System.out.println("re_bodybg01G = " + re_bodybg01G.size());
                                Wait().until(ExpectedConditions.elementToBeClickable(By.className("re_left_fixed")));
                                List<WebElement> elements3 = selenideElement3.findElements(By.className("re_left_fixed")).get(0).findElements(By.name("user_btn"));
                                for (int j = 0; j < elements3.size(); j++) {
                                    num3++;
//                                for (WebElement webElement:elements3
//                                     ) {
                                    //查询数据库
                                    System.out.println(elements3.get(j).getText());
                                    QueryWrapper<User> wrapper2 = new QueryWrapper();
                                    wrapper2.eq("username",elements3.get(j).getText());
                                    wrapper2.eq("is_del",false);
                                    wrapper2.eq("type","2");
                                    User user3 = userService.getOne(wrapper2);
                                    if (user3 !=null && !arrayList.contains(elements3.get(j).getText())){
                                        arrayList.add(elements3.get(j).getText());
                                        //存在 并且没有点击过
                                        elements3.get(j).click();

                                        Wait().until(ExpectedConditions.elementToBeClickable(By.id("div_show")));
                                        sleep(2000);
                                        $("#footer_show").scrollIntoView(false);
                                        sleep(2000);
                                        $("#footer_show").scrollIntoView(false);
                                        sleep(2000);
                                        $("#footer_show").scrollIntoView(false);
                                        sleep(2000);
                                        $("#re_body").scrollIntoView(true);
                                        sleep(1000);
                                        SelenideElement selenideElement33 = $("#div_show");
                                        List<WebElement> re_bodybg01G33 = selenideElement33.findElements(By.className("re_bodybg01G"));
//                        System.out.println("re_bodybg01G = " + re_bodybg01G.size());
                                        List<WebElement> reDivBody = selenideElement33.findElements(By.className("re_div_body"));
//                                        selenideElement33.findElements(By.className("re_div_body")).get(0)
//                                        selenideElement33.findElements(By.className("re_div_body")).get(0).findElement(By.className("re_div_moveG")).findElements(By.className("re_td_w15")).get(3).getText();

                                        for (WebElement webElement:reDivBody
                                             ) {
                                            //获取代码
                                            String text = webElement.findElement(By.className("re_left_br")).findElement(By.name("user_btn")).getText();
                                            //获取字段
                                            List<WebElement> elements1 = webElement.findElement(By.className("re_div_moveG")).findElements(By.className("re_td_w15"));

//                                            webElement.findElement(By.className("re_div_moveG")).findElements(By.className("re_td_w15")).get(3).getText();


                                            huangguan = new Huangguan();
                                            k = 0;
                                            huangguan.setZh(text);
                                            huangguan.setDls(elements1.get(k++).getText());
                                            huangguan.setDlszc(elements1.get(k++).getText());
                                            huangguan.setDlsjg(elements1.get(k++).getText());
                                            huangguan.setDlsshl(elements1.get(k++).getText());
                                            huangguan.setZdlzc(elements1.get(k++).getText());
                                            huangguan.setZdljg(elements1.get(k++).getText());

                                            huangguan.setCreatedate(new Date());
                                            huangguan.setAccountNumber(user.getUsername());
                                            huangguan.setNumberOfLayers(3);
                                            huangguan.setType("2");
                                            huangguan.setStartDate(DateUtil.lastMonday());
                                            huangguan.setEndDate(DateUtil.lastSunday());

                                            queryWrapper =new QueryWrapper();
                                            queryWrapper.eq("start_date", DateUtil.lastMonday().format(dateTimeFormatter));
                                            queryWrapper.eq("end_date", DateUtil.lastSunday().format(dateTimeFormatter));
                                            queryWrapper.eq("account_number",user.getUsername());
                                            queryWrapper.eq("type",2);
                                            queryWrapper.eq("zh",huangguan.getZh());

                                            if (huangguanService.getOne(queryWrapper) == null){
                                                if (huangguan.getZh() !=null)
                                                huangguans.add(huangguan);
                                            }

                                        }
                                        Boolean isTrue3 = Boolean.TRUE;
                                        int num44 = 0;
                                        while (isTrue3){
                                            sleep(3000);
                                            Wait().until(ExpectedConditions.elementToBeClickable(By.id("div_show")));

                                            SelenideElement selenideElement44 = $("#div_show");
                                            List<WebElement> re_bodybg01G44 = selenideElement44.findElements(By.className("re_bodybg01G"));
//                        System.out.println("re_bodybg01G = " + re_bodybg01G.size());
                                            List<WebElement> reDivBody44 = selenideElement44.findElements(By.className("re_div_body"));
//                                        selenideElement33.findElements(By.className("re_div_body")).get(0)
//                                        selenideElement33.findElements(By.className("re_div_body")).get(0).findElement(By.className("re_div_moveG")).findElements(By.className("re_td_w15")).get(3).getText();
                                            for (WebElement webElement:reDivBody44
                                            ) {
                                                num44++;
                                                //获取代码
                                                String text = webElement.findElement(By.className("re_left_br")).findElement(By.name("user_btn")).getText();
                                                //获取字段
                                                List<WebElement> elements1 = webElement.findElement(By.className("re_div_moveG")).findElements(By.className("re_td_w15"));
                                                //查询数据库
                                                System.out.println(webElement.getText());
                                                QueryWrapper<User> wrapper4 = new QueryWrapper();
                                                wrapper4.eq("username",text);
                                                wrapper4.eq("is_del",false);
                                                wrapper4.eq("type","2");
                                                User user4 = userService.getOne(wrapper4);
                                                if (user4 !=null && !arrayList.contains(text)) {
                                                    arrayList.add(text);
                                                    webElement.findElement(By.className("re_left_br")).findElement(By.name("user_btn")).click();
                                                    sleep(2000);
//                                                    executeJavaScript("document.getElementById('footer_show').scrollIntoView()");
//                                                    sleep(1000);
//                                                    executeJavaScript("document.getElementById('footer_show').scrollIntoView()");
//                                                    sleep(1000);
//                                                    executeJavaScript("document.getElementById('footer_show').scrollIntoView()");
//                                                    sleep(1000);
//                                                    executeJavaScript("document.getElementById('footer_show').scrollIntoView()");
//                                                    sleep(1000);
                                                    Wait().until(ExpectedConditions.elementToBeClickable(By.id("div_show")));

                                                    SelenideElement selenideElement55 = $("#div_show");
                                                    List<WebElement> re_bodybg01G55 = selenideElement55.findElements(By.className("re_bodybg01G"));
//                        System.out.println("re_bodybg01G = " + re_bodybg01G.size());
                                                    List<WebElement> reDivBody55 = selenideElement55.findElements(By.className("re_div_body"));
                                                    for (WebElement webElement1 :
                                                            reDivBody55) {
                                                        List<WebElement> elements = webElement1.findElement(By.className("re_div_moveG")).findElements(By.tagName("div"));
                                                        String text1 = webElement1.findElement(By.className("re_left_br")).findElement(By.name("user_btn")).getText();
                                                        huangguan = new Huangguan();
                                                        k = 0;
                                                        huangguan.setZh(text1);
                                                        huangguan.setBs(elements.get(k++).getText());
                                                        huangguan.setXzje(elements.get(k++).getText());
                                                        huangguan.setYxje(elements.get(k++).getText());
                                                        huangguan.setHy(elements.get(k++).getText());
                                                        huangguan.setHybz(elements.get(k++).getText());
                                                        huangguan.setDlszc(elements.get(k++).getText());
                                                        huangguan.setDlsjg(elements.get(k++).getText());

//                                                        elements.get(num++).getText();
                                                        queryWrapper =new QueryWrapper();
                                                        queryWrapper.eq("start_date", DateUtil.lastMonday().format(dateTimeFormatter));
                                                        queryWrapper.eq("end_date", DateUtil.lastSunday().format(dateTimeFormatter));
                                                        queryWrapper.eq("account_number",user.getUsername());
                                                        queryWrapper.eq("type",2);
                                                        queryWrapper.eq("zh",huangguan.getZh());

                                                        if (huangguanService.getOne(queryWrapper) == null){
                                                            if (huangguan.getZh() !=null)
                                                            huangguans.add(huangguan);
                                                        }


                                                    }
                                                    back();
                                                    sleep(3000);
                                                    break;
                                                }

                                            }
                                            if (num44 >=reDivBody44.size()){
                                                isTrue3 =false;
                                            }

                                        }
//                                        QueryWrapper<User> wrapper4 = new QueryWrapper();
//                                        wrapper4.eq("username",elements3.get(j).getText());
//                                        wrapper4.eq("is_del",false);
//                                        wrapper4.eq("type","2");
//                                        User user4 = userService.getOne(wrapper4);

                                        back();
                                        sleep(3000);
                                        break;
                                    }
                                    if (num3 >=elements3.size()){
                                        isTrue2 =false;
                                    }

                                }


                            }
                            //1.重新爬取上层数据

                            //2.点击进入第三层

                            //3.爬取当前页面数据

                            //4爬取完毕返回




                            back();
                            sleep(2000);
                            break;
                        }

                        if (re_bodybg01G.size() <= num){
                            isTrue = false;
                        }

//                    RedisOps.set(RedisKey.HG0_DAILI+word_link.getText(), JSONObject.toJSONString(map));
//
                    }

                    //            System.out.println("selenideElement2 = " + selenideElement2.findElements());
                    //            SelenideElement selenideElement2 = selenideElement.$(".selenideElement :nth-child(2)");
                    //            System.out.println("selenideElement1 = " + selenideElement1);
                    //            System.out.println("selenideElement1 = " + selenideElement2);
                    System.out.println("========================================================================");

                }
                huangguanService.saveBatch(huangguans);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }


    }

    @Autowired
    HuangguanDaimaService huangguanDaimaService;

    public void write00()  {
        try {
            QueryWrapper<Huangguan> wrapper = new QueryWrapper();
            QueryWrapper<HuangguanDaima> wrapper2 = new QueryWrapper();
// 1.获取工作簿
            XSSFWorkbook workbook = new XSSFWorkbook("D:\\data\\000.xlsx");
//        XSSFWorkbook workbook = new XSSFWorkbook("/data/2022-12-05_2022-12-11 (1).xls");
            // 2.获取工作表
            // xlsx第一个工作簿(Sheet1)，下标从0开始，0就是第一个
            XSSFSheet sheet = workbook.getSheetAt(2);
//        HashMap<String, BigDecimal> maps = new HashMap();
            /*使用加强for循环的方式*/
            int i = 0;
            QueryWrapper wrapper1 = new QueryWrapper();
            // 3.获取行
            for (Row row : sheet) {
                int k = 0;
                // 4.获取单元格
                HashMap map = new HashMap();

                if (row.getCell(1) !=null && row.getCell(1).getCellType().getCode() == 1){
                    String trim = row.getCell(1).getStringCellValue().trim();
                    log.info("左边的代码：：：：：{}",trim);
                    wrapper1 = new QueryWrapper();
                    wrapper1.eq("zh",trim);
                    HuangguanDaima huangguanDaima = huangguanDaimaService.getOne(wrapper1);

                    wrapper = new QueryWrapper();
                    wrapper.eq("zh",trim);
                    Huangguan huangguan = huangguanService.getOne(wrapper);
                    if (huangguan !=null){
                        if (huangguanDaima !=null){

                            switch (huangguanDaima.getState()){
//                            股东
                                case 1:
                                    row.getCell(2).setCellValue(new BigDecimal(huangguan.getGdjg()).multiply(new BigDecimal("100")).doubleValue());
                                    break;
                                    //总代理
                                case 2:
                                    row.getCell(2).setCellValue(new BigDecimal(huangguan.getZdljg()).multiply(new BigDecimal("100")).doubleValue());
                                    break;
                                    //会员
                                case 3:
                                    row.getCell(2).setCellValue(new BigDecimal(huangguan.getHy()).multiply(new BigDecimal("100")).doubleValue());
                                    break;
                            }

                        }else {
                            row.getCell(2).setCellValue(new BigDecimal(huangguan.getGdjg()).multiply(new BigDecimal("100")).doubleValue());
                        }

                    }


//                    wrapper1.get
                }


                if (row.getCell(6) !=null && row.getCell(6).getCellType().getCode() == 1){
                    String trim = row.getCell(6).getStringCellValue().trim();
                    log.info("右边的代码：：：：：{}",row.getCell(6).getStringCellValue());
                    wrapper1 = new QueryWrapper();
                    wrapper1.eq("zh",trim);
                    HuangguanDaima huangguanDaima = huangguanDaimaService.getOne(wrapper1);

                    wrapper = new QueryWrapper();
                    wrapper.eq("zh",trim);
                    Huangguan huangguan = huangguanService.getOne(wrapper);
                    if (huangguan !=null){
                        if (huangguanDaima !=null){

                            switch (huangguanDaima.getState()){
//                            股东
                                case 1:
                                    row.getCell(7).setCellValue(new BigDecimal(huangguan.getGdjg()).multiply(new BigDecimal("100")).doubleValue());
                                    break;
                                //总代理
                                case 2:
                                    row.getCell(7).setCellValue(new BigDecimal(huangguan.getZdljg()).multiply(new BigDecimal("100")).doubleValue());
                                    break;
                                //会员
                                case 3:
                                    row.getCell(7).setCellValue(new BigDecimal(huangguan.getHy()).multiply(new BigDecimal("100")).doubleValue());
                                    break;
                            }

                        }else {
                            row.getCell(7).setCellValue(new BigDecimal(huangguan.getGdjg()).multiply(new BigDecimal("100")).doubleValue());
                        }

                    }

                }



            }
            FileOutputStream out = new FileOutputStream("D:\\data\\001.xlsx");

            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            formulaEvaluator.evaluateAll();
            workbook.setForceFormulaRecalculation(true);

            workbook.write(out);
            out.close();
            // 释放资源
            workbook.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        System.out.println(maps);
//        return maps;

    }


    public static void main(String[] args) {
        HuangguanBills huangguanBills = new HuangguanBills();
        huangguanBills.write00();
    }
}
