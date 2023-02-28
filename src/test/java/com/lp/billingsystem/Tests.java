package com.lp.billingsystem;

import com.google.j2objc.annotations.Weak;
import com.lp.billingsystem.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tests {

    @Test
    public void test(){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+");
        Matcher matcher = pattern.matcher("cXxb277777 (人民币)");
        String str = "";
        while (matcher.find()){
            str += matcher.group();
        }
        System.out.println(str);
//        System.out.println(group);

    }
    @Test
    public void test1(){
        LocalDateTime localDateTime = DateUtil.startDate(1);
        LocalDateTime localDateTime1 = DateUtil.endDate(1);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);


    }
}
