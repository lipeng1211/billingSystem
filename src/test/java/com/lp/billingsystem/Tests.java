package com.lp.billingsystem;

import com.google.j2objc.annotations.Weak;
import com.lp.billingsystem.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = now.minusMonths(1);
        String startDate = localDateTime.format(dateTimeFormatter);
        String endDate = now.format(dateTimeFormatter);
        System.out.println(startDate);
        System.out.println(endDate);

    }
}
