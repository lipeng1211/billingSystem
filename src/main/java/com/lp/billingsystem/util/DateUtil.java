package com.lp.billingsystem.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

public class DateUtil {


    final  static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    final  static WeekFields weekFields= WeekFields.ISO;
    final  static LocalDate now = LocalDate.now();
    
    /**
    * 获取本周一
    * @Author lipeng
    * @Date 2023/2/16 20:24
    * @param 
    * @return java.lang.String 格式化的日期
    */
    public static LocalDateTime getMonday(){

        LocalDate mondayDate = now.with(weekFields.dayOfWeek(), 1L);
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalDateTime monday = LocalDateTime.of(mondayDate, time);
//        String format = monday.format(dateTimeFormatter);
        return monday;

    }

    /**
    * 获取本周日
    * @Author lipeng
    * @Date 2023/2/16 20:25
    * @param
    * @return java.lang.String 格式化的日期
    */
    public static LocalDateTime getSunday(){


        LocalDate sundayDate = now.with(weekFields.dayOfWeek(), 7L);
        LocalTime time = LocalTime.of(23, 59, 59);
        LocalDateTime.of(sundayDate, time);
        LocalDateTime sunday = LocalDateTime.of(sundayDate, time);
//        String format = sunday.format(dateTimeFormatter);
        return sunday;

    }

    /**
    * 获取上周一
    * @Author lipeng
    * @Date 2023/2/16 20:26
    * @param
    * @return java.lang.String 格式化的日期
    */
    public static LocalDateTime lastMonday(){


        LocalDate mondayDate = now.minusWeeks(1).with(weekFields.dayOfWeek(), 1L);
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalDateTime monday = LocalDateTime.of(mondayDate, time);
//        String format = monday.format(dateTimeFormatter);
        return monday;

    }

    /**
    * 获取上周日
    * @Author lipeng
    * @Date 2023/2/16 20:26
    * @param
    * @return java.lang.String 格式化的日期
     *
    */

    public static LocalDateTime lastSunday(){
        LocalDate sundayDate = now.minusWeeks(1).with(weekFields.dayOfWeek(), 7L);
        LocalTime time = LocalTime.of(23, 59, 59);
        LocalDateTime.of(sundayDate, time);
        LocalDateTime sunday = LocalDateTime.of(sundayDate, time);
//        String format = sunday.format(dateTimeFormatter);
        return sunday;

    }

    /**
    * 获取上个月的上周一
    * @Author lipeng
    * @Date 2023/2/16 20:26
    * @param
    * @return java.lang.String 格式化的日期
    */
    public static LocalDateTime lastSundayLastMonth(){


        LocalDate mondayDate = now.minusWeeks(1).with(weekFields.dayOfWeek(), 1L);
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalDateTime monday = LocalDateTime.of(mondayDate, time);
//        String format = monday.format(dateTimeFormatter);
        return monday;

    }

    /**
    * 获取上个月的上周日
    * @Author lipeng
    * @Date 2023/2/16 20:26
    * @param
    * @return java.lang.String 格式化的日期
     *
    */

    public static LocalDateTime lastMondayOfLastMonth(){
        LocalDate sundayDate = now.minusWeeks(1).with(weekFields.dayOfWeek(), 7L);
        LocalTime time = LocalTime.of(23, 59, 59);
        LocalDateTime.of(sundayDate, time);
        LocalDateTime sunday = LocalDateTime.of(sundayDate, time);
//        String format = sunday.format(dateTimeFormatter);
        return sunday;

    }

}
