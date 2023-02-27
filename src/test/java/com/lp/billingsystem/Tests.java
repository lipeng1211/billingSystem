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
        LocalDate now = LocalDate.now();
        LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
//        System.out.println(firstDayOfMonth);
//        System.out.println(lastDayOfMonth);

        WeekFields weekFields= WeekFields.ISO;

        LocalDate mondayDate1 = firstDayOfMonth.minusWeeks(1).with(weekFields.dayOfWeek(), 7L);
        LocalDate mondayDate2 = lastDayOfMonth.with(weekFields.getFirstDayOfWeek());
        System.out.println(mondayDate1);
        System.out.println(mondayDate2);
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalDateTime monday = LocalDateTime.of(mondayDate1, time);
        System.out.println(monday);

    }
}
