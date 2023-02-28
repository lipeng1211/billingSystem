package com.lp.billingsystem.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
    public static LocalDateTime startDate(){
        LocalDate startDate =null;
        LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        if (firstDayOfMonth.getDayOfWeek().getValue() == 1){
            startDate = firstDayOfMonth;
        }else {
            startDate = firstDayOfMonth.plusWeeks(0).with(weekFields.getFirstDayOfWeek());
        }

        LocalTime time = LocalTime.of(0, 0, 0);
//        String format = monday.format(dateTimeFormatter);
        return LocalDateTime.of(startDate, time);

    }

    /**
    * 获取上个月的上周日
    * @Author lipeng
    * @Date 2023/2/16 20:26
    * @param
    * @return java.lang.String 格式化的日期
     *
    */

    public static LocalDateTime endDate(){

        LocalTime time = LocalTime.of(23, 59, 59);
        LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate endDate =LocalDate.now();
        if (lastDayOfMonth.getDayOfWeek().getValue() == 7){
            endDate = lastDayOfMonth;
        }else {
            endDate = lastDayOfMonth.minusWeeks(1).with(weekFields.dayOfWeek(),  7L);
        }


//        String format = sunday.format(dateTimeFormatter);
        return LocalDateTime.of(endDate, time);

    }
    /**
    * 获取上个月的上周一
    * @Author lipeng
    * @Date 2023/2/16 20:26
    * @param
    * @return java.lang.String 格式化的日期
    */
    public static LocalDateTime startDate(int num){
        LocalDate startDate =null;
        LocalDate firstDayOfMonth = now.plusMonths(num).with(TemporalAdjusters.firstDayOfMonth());
        if (firstDayOfMonth.getDayOfWeek().getValue() == 1){
            startDate = firstDayOfMonth;
        }else {
            startDate = firstDayOfMonth.plusWeeks(0).with(weekFields.getFirstDayOfWeek());
        }

        LocalTime time = LocalTime.of(0, 0, 0);
//        String format = monday.format(dateTimeFormatter);
        return LocalDateTime.of(startDate, time);

    }

    /**
    * 获取上个月的上周日
    * @Author lipeng
    * @Date 2023/2/16 20:26
    * @param
    * @return java.lang.String 格式化的日期
     *
    */

    public static LocalDateTime endDate(int num){

        LocalTime time = LocalTime.of(23, 59, 59);
        LocalDate lastDayOfMonth = now.plusMonths(num).with(TemporalAdjusters.lastDayOfMonth());
        LocalDate endDate =null;
        if (lastDayOfMonth.getDayOfWeek().getValue() == 7){
            endDate = lastDayOfMonth;
        }else {
            endDate = lastDayOfMonth.minusWeeks(1).with(weekFields.dayOfWeek(),  7L);
        }


//        String format = sunday.format(dateTimeFormatter);
        return LocalDateTime.of(endDate, time);

    }


}
