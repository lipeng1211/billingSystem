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

public class Tests {

    @Test
    public void test(){
        LocalDateTime monday = DateUtil.getMonday();
        LocalDateTime sunday = DateUtil.getSunday();
        System.out.println(monday);
        System.out.println(sunday);

        LocalDateTime localDateTime = DateUtil.lastMonday();
        LocalDateTime localDateTime1 = DateUtil.lastSunday();
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
    }
}
