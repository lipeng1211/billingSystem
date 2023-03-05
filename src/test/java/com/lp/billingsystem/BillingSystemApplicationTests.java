package com.lp.billingsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.billingsystem.config.redis.RedisCache;
import com.lp.billingsystem.domain.HuangguanDaima;
import com.lp.billingsystem.domain.HuangguanYuetui;
import com.lp.billingsystem.domain.LpUsername;
import com.lp.billingsystem.domain.LpUsernameDaima;
import com.lp.billingsystem.service.HuangguanDaimaService;
import com.lp.billingsystem.service.HuangguanYuetuiService;
import com.lp.billingsystem.service.LpUsernameDaimaService;
import com.lp.billingsystem.service.LpUsernameService;
import com.lp.billingsystem.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest
@Log4j2
class BillingSystemApplicationTests {


    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisCache redisCache;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    HuangguanYuetuiService huangguanYuetuiService;
    @Autowired
    HuangguanDaimaService huangguanDaimaService;

    @Autowired
    LpUsernameDaimaService lpUsernameDaimaService;

    @Autowired
    LpUsernameService lpUsernameService;



    @Test
    void contextLoads() throws IOException {
//        readYs81lc();
//        readYs301b();

//        generateAPersonalWeeklyAccount();
//        Collection<String> keys = redisCache.keys("*-*-*");
        Collection<String> keys = redisCache.keys("redis:red:packet:41*");
        System.out.println("开始删除"+keys.size());
        redisCache.deleteObject(keys);
        System.out.println("删除完毕");
//        Iterator<String> iterator = keys.iterator();
//        System.out.println(keys.size());
        int i = 0;
//        while (iterator.hasNext()){
//            String next = iterator.next();
////            System.out.println(next);
//            redisCache.deleteObject(next);
//            System.out.println(i++);
//        }
//        System.out.println(keys.size());
    }



    /**
    * 生成个人周帐
    * @Author lipeng
    * @Date 2023/3/2 0:21
    * @param
    * @return void
    */
    public void generateAPersonalWeeklyAccount() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook("D:\\data\\tengda\\000.xlsx");
//        XSSFWorkbook workbook = new XSSFWorkbook("/data/2022-12-05_2022-12-11 (1).xls");
        // 2.获取工作表
        // xlsx第一个工作簿(Sheet1)，下标从0开始，0就是第一个
        XSSFSheet sheet = workbook.getSheetAt(4);
//        HashMap<String, BigDecimal> maps = new HashMap();
        /*使用加强for循环的方式*/
        //总行数
        int i = 0;
        int k = 0;
        //分段行数
        int rowNum = 0;
        int rowNumSize = 1;
        //分段列数
        int columnNum = 1;
        int columnNumSize = 0;
        sheet.setColumnWidth(0, 20*256);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id",1,2,3,4,5,6);

        XSSFRow row = null;
            CellStyle style1=workbook.createCellStyle();
        style1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
//        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style1.setBorderBottom(BorderStyle.THIN);//下边框
        style1.setBorderLeft(BorderStyle.THIN);//左边框
        style1.setBorderTop(BorderStyle.THIN);//上边框
        style1.setBorderRight(BorderStyle.THIN);//右边框
        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle style2=workbook.createCellStyle();
        style2.setFillForegroundColor(IndexedColors.RED.getIndex());
//        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);//下边框
        style2.setBorderLeft(BorderStyle.THIN);//左边框
        style2.setBorderTop(BorderStyle.THIN);//上边框
        style2.setBorderRight(BorderStyle.THIN);//右边框
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle style3=workbook.createCellStyle();
        style3.setFillForegroundColor(IndexedColors.BLUE.getIndex());
//        style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style3.setBorderBottom(BorderStyle.THIN);//下边框
        style3.setBorderLeft(BorderStyle.THIN);//左边框
        style3.setBorderTop(BorderStyle.THIN);//上边框
        style3.setBorderRight(BorderStyle.THIN);//右边框
        style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle style4=workbook.createCellStyle();
        style2.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        style4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style4.setBorderBottom(BorderStyle.THIN);//下边框
        style4.setBorderLeft(BorderStyle.THIN);//左边框
        style4.setBorderTop(BorderStyle.THIN);//上边框
        style4.setBorderRight(BorderStyle.THIN);//右边框


        List<LpUsername> lpUsernameList = lpUsernameService.list();
        for (LpUsername lpUsername:
             lpUsernameList) {
            //创建行


            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("username_id",lpUsername.getId());
            wrapper.eq("is_del",false);
            List<LpUsernameDaima> list = lpUsernameDaimaService.list(wrapper);
            if (list.size() > 0){
                if (columnNum > 5){
                    columnNum =1;
                   rowNum = i += rowNumSize;
                    rowNumSize = 0;
                }else {
                    i = rowNum;

                }

                row = sheet.getRow(i) == null?sheet.createRow(i):sheet.getRow(i);

                //创建表头
                XSSFCell cell1 = row.createCell(columnNum * 6 - 6);
                cell1.setCellStyle(style1);
                cell1.setCellValue(lpUsername.getUsername());

                XSSFCell cell2 = row.createCell(columnNum * 6 - 5);
                cell2.setCellValue("代码");
                cell2.setCellStyle(style2);

                XSSFCell cell3 = row.createCell(columnNum * 6 - 4);
                cell3.setCellValue("周帐");
                cell3.setCellStyle(style3);

                XSSFCell cell4 = row.createCell(columnNum * 6 - 3);
                cell4.setCellStyle(style4);
                cell4.setCellValue("月退1");
                row.createCell(columnNum * 6 - 2).setCellValue("退水分红");
                row.createCell(columnNum * 6 - 1).setCellValue("总计");
                i++;
                //                执行完毕 分段加1

                if (list.size() > rowNumSize){
                    rowNumSize = list.size();
                }

            }
            i = rowNum;
            for (LpUsernameDaima lpUsernameDaima:
                 list) {
                i++;
                row = sheet.getRow(i) == null?sheet.createRow(i):sheet.getRow(i);
                row.createCell(columnNum * 6 - 6).setCellValue(lpUsernameDaima.getType());
                row.createCell(columnNum * 6 - 5).setCellValue(lpUsernameDaima.getDaima());
                row.createCell(columnNum * 6 - 4).setCellValue(99);
                row.createCell(columnNum * 6 - 3).setCellValue(88);
                row.createCell(columnNum * 6 - 2).setCellValue(77);
                row.createCell(columnNum * 6 - 1).setCellValue("总计");


//                if ()
            }
            columnNum++;

        }
//        for (int j = 0; j < lpUsernameList.size(); j++) {
//
//
//            for (int l = 1; l < 6; l++) {
//
//                QueryWrapper wrapper = new QueryWrapper();
//                wrapper.eq("username_id",lpUsernameList.get(j).getId());
//                wrapper.eq("is_del",false);
//                List<LpUsernameDaima> list = lpUsernameDaimaService.list(wrapper);

//            }
//
//            if (list.size() > 0){
//                rownum = 1;
//                k = 0;
//                //有值  先写表头
//
//                i++;
//                k = 0;
//                //写入内容
//                for (int l = 0; l < list.size(); l++) {
//                    List<String> str = new ArrayList<>();
//
//                    //写入类型
//                    sheet.getRow(i).getCell(rownum * k++).setCellValue(list.get(l).getType());
//                    sheet.getRow(i).getCell(rownum * k++).setCellValue(list.get(l).getDaima());
//                    //周帐
//                    sheet.getRow(i).getCell(rownum * k++).setCellValue(100);
//                    str.add(sheet.getRow(i).getCell(rownum * k++).getAddress().toString());
//
//                    //月退1
//                    sheet.getRow(i).getCell(rownum * k++).setCellValue(99);
//
//                    //退水分红
//                    sheet.getRow(i).getCell(rownum * k++).setCellValue(88);
//
////                    sheet.getRow(i).getCell(rownum * k++).setCellValue();
//                }

//                for (int l = 0; l < list.size(); l++) {
//                    sheet.getRow(i).getCell(j * 6 - 4).setCellValue(list.get(l).getDaima());
//                    List<String> str = new ArrayList<>();
//                    //有周帐
//                    if (1==1){
//                        sheet.getRow(i).getCell(j * 6 - 3).setCellValue(100D);
//
//                        str.add(sheet.getRow(i).getCell(j * 6 - 3).getAddress().toString());
//                    }
//                    //有十退一
//                    if (1==1){
//                        sheet.getRow(i).getCell(j * 6 - 2).setCellValue(88D);
//                        str.add(sheet.getRow(i).getCell(j * 6 - 2).getAddress().toString());
//                    }
//                    //退水分红
//                    if (1==1){
//                        sheet.getRow(i).getCell(j * 6 - 1).setCellValue(77D);
//                        str.add(sheet.getRow(i).getCell(j * 6 - 1).getAddress().toString());
//                    }
//
//                    sheet.getRow(i).getCell(j * 6).setCellFormula("SUM("+ StringUtils.join(str,",") +")");
//
//                }


//            }

//        }

        // 3.获取行
//        for (Row row : sheet) {
//        for (int k = 0; k < sheet.getLastRowNum()+1; k++) {
//
//            if (sheet.getRow(k).getCell(0) !=null && sheet.getRow(k).getCell(0).getCellType().getCode() == 0){
//
//                for (int j = 1; j < 6; j++) {
//
//                    if (sheet.getRow(k).getCell(j * 6 - 4) !=null && sheet.getRow(k).getCell(j * 6 - 4).getCellType().getCode() ==0){
//                        Double numericCellValue = sheet.getRow(k).getCell(j * 6 - 4).getNumericCellValue();
//                        QueryWrapper wrapper = new QueryWrapper();
//                        wrapper.eq("username_id",numericCellValue.intValue());
//                        wrapper.eq("is_del",false);
//                        List<LpUsernameDaima> list = lpUsernameDaimaService.list(wrapper);
//                        for (int l = 0; l < list.size(); l++) {
//                            sheet.getRow(k+l+1).getCell(j * 6 - 4).setCellValue(list.get(l).getDaima());
//                            List<String> str = new ArrayList<>();
//                            //有周帐
//                            if (1==1){
//                                sheet.getRow(k+l+1).getCell(j * 6 - 3).setCellValue(100D);
//
//                                str.add(sheet.getRow(k+l+1).getCell(j * 6 - 3).getAddress().toString());
//                            }
//                            //有十退一
//                            if (1==1){
//                                sheet.getRow(k+l+1).getCell(j * 6 - 2).setCellValue(88D);
//                                str.add(sheet.getRow(k+l+1).getCell(j * 6 - 2).getAddress().toString());
//                            }
//                            //退水分红
//                            if (1==1){
//                                sheet.getRow(k+l+1).getCell(j * 6 - 1).setCellValue(77D);
//                                str.add(sheet.getRow(k+l+1).getCell(j * 6 - 1).getAddress().toString());
//                            }
//
//                            sheet.getRow(k+l+1).getCell(j * 6).setCellFormula("SUM("+ StringUtils.join(str,",") +")");
//
//                        }
//                        System.out.println(list);
//                    }
//
//                }
////                Cell cell1 = sheet.getRow(k).getCell(2);
////                Cell cell2 = row.getCell(5);
////                Cell cell3 = row.getCell(8);
////                Cell cell4 = row.getCell(11);
////                Cell cell5 = row.getCell(14);
//            }
//
//
//
//
//        }

        FileOutputStream out = new FileOutputStream("D:\\data\\tengda\\003.xlsx");

        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        formulaEvaluator.evaluateAll();
        workbook.setForceFormulaRecalculation(true);

        workbook.write(out);
        out.close();
        // 释放资源
        workbook.close();

    }

    public void readYs81lc() throws IOException {
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.likeLeft("zh","xb27777");
//        HuangguanDaima one = huangguanDaimaService.getOne(wrapper);
//        System.out.println(one);
        HuangguanYuetui huangguanYuetui = new HuangguanYuetui();
        //获取工作簿
//        XSSFWorkbook workbook = new XSSFWorkbook("D:\\data\\tengda\\月退\\ys81lc.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook("D:\\data\\tengda\\月退\\2023.02\\ys81lc.xlsx");
        //获取工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        int num = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            int k = 0;
            if (i == 1){
                huangguanYuetui = new HuangguanYuetui();
                huangguanYuetui.setZh("ys81lc");
                k++;
                k++;
                huangguanYuetui.setZdljg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setZdlshl(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setZdlbfb(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGd(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdzc(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdjg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdshl(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdbfb(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setBs(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setXzje(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setYxje(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setHy(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setDls(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setDlsjg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setCreatedate(new Date());
                huangguanYuetui.setAccountNumber("ys81lc");
                huangguanYuetui.setNumberOfLayers(2);
                huangguanYuetui.setType("2");
                huangguanYuetui.setStartDate(DateUtil.startDate(num));
                huangguanYuetui.setEndDate(DateUtil.endDate(num));

                QueryWrapper queryWrapper =new QueryWrapper();
                queryWrapper.eq("start_date", DateUtil.startDate(num).format(dateTimeFormatter));
                queryWrapper.eq("end_date", DateUtil.endDate(num).format(dateTimeFormatter));
                queryWrapper.eq("account_number","ys81lc");
                queryWrapper.eq("type","2");
                queryWrapper.eq("zh",huangguanYuetui.getZh());

                if (huangguanYuetuiService.getOne(queryWrapper) == null){
                    huangguanYuetuiService.save(huangguanYuetui);
                }


                System.out.println(huangguanYuetui);

            }
            if (i>1){
                huangguanYuetui = new HuangguanYuetui();
                huangguanYuetui.setZh(sheet.getRow(i).getCell(k++).getStringCellValue());
                k++;
                huangguanYuetui.setZdljg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setZdlshl(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setZdlbfb(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGd(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdzc(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdjg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdshl(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdbfb(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setBs(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setXzje(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setYxje(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setHy(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setDls(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setDlsjg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setCreatedate(new Date());
                huangguanYuetui.setAccountNumber("ys81lc");
                huangguanYuetui.setNumberOfLayers(2);
                huangguanYuetui.setType("2");
                huangguanYuetui.setStartDate(DateUtil.startDate(num));
                huangguanYuetui.setEndDate(DateUtil.endDate(num));
                System.out.println(huangguanYuetui);

                QueryWrapper queryWrapper =new QueryWrapper();
                queryWrapper.eq("start_date", DateUtil.startDate(num).format(dateTimeFormatter));
                queryWrapper.eq("end_date", DateUtil.endDate(num).format(dateTimeFormatter));
                queryWrapper.eq("account_number","ys81lc");
                queryWrapper.eq("type","2");
                queryWrapper.eq("zh",huangguanYuetui.getZh());

                if (huangguanYuetuiService.getOne(queryWrapper) == null){
                    huangguanYuetuiService.save(huangguanYuetui);
                }

            }


        }
    }



    public void readYs301b() throws IOException {
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.likeLeft("zh","xb27777");
//        HuangguanDaima one = huangguanDaimaService.getOne(wrapper);
//        System.out.println(one);
        HuangguanYuetui huangguanYuetui = new HuangguanYuetui();
        //获取工作簿
//        XSSFWorkbook workbook = new XSSFWorkbook("D:\\data\\tengda\\月退\\ys81lc.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook("D:\\data\\tengda\\月退\\2023.01\\ys301b.xlsx");
        //获取工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        int num = -1;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            int k = 0;
            if (i == 1){
                huangguanYuetui = new HuangguanYuetui();
                huangguanYuetui.setZh("ys301b");
                k++;
                k++;
                huangguanYuetui.setZdljg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setZdlshl(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setZdlbfb(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGd(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdzc(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdjg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdshl(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdbfb(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setBs(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setXzje(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setYxje(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setHy(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setDls(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setDlsjg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setCreatedate(new Date());
                huangguanYuetui.setAccountNumber("ys301b");
                huangguanYuetui.setNumberOfLayers(1);
                huangguanYuetui.setType("2");
                huangguanYuetui.setStartDate(DateUtil.startDate(num));
                huangguanYuetui.setEndDate(DateUtil.endDate(num));

                QueryWrapper queryWrapper =new QueryWrapper();
                queryWrapper.eq("start_date", DateUtil.startDate(num).format(dateTimeFormatter));
                queryWrapper.eq("end_date", DateUtil.endDate(num).format(dateTimeFormatter));
                queryWrapper.eq("account_number","ys301b");
                queryWrapper.eq("type","2");
                queryWrapper.eq("zh",huangguanYuetui.getZh());

                if (huangguanYuetuiService.getOne(queryWrapper) == null){
                    huangguanYuetuiService.save(huangguanYuetui);
                }


                System.out.println(huangguanYuetui);

            }
            if (i>1){
                huangguanYuetui = new HuangguanYuetui();
                huangguanYuetui.setZh(sheet.getRow(i).getCell(k++).getStringCellValue());
                k++;
                huangguanYuetui.setZdljg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setZdlshl(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setZdlbfb(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGd(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdzc(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdjg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdshl(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setGdbfb(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setBs(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setXzje(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setYxje(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setHy(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setDls(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setDlsjg(sheet.getRow(i).getCell(k++).getNumericCellValue()+"");
                huangguanYuetui.setCreatedate(new Date());
                huangguanYuetui.setAccountNumber("ys301b");
                huangguanYuetui.setNumberOfLayers(1);
                huangguanYuetui.setType("2");
                huangguanYuetui.setStartDate(DateUtil.startDate(num));
                huangguanYuetui.setEndDate(DateUtil.endDate(num));
                System.out.println(huangguanYuetui);

                QueryWrapper queryWrapper =new QueryWrapper();
                queryWrapper.eq("start_date", DateUtil.startDate(num).format(dateTimeFormatter));
                queryWrapper.eq("end_date", DateUtil.endDate(num).format(dateTimeFormatter));
                queryWrapper.eq("account_number","ys301b");
                queryWrapper.eq("type","2");
                queryWrapper.eq("zh",huangguanYuetui.getZh());

                if (huangguanYuetuiService.getOne(queryWrapper) == null){
                    huangguanYuetuiService.save(huangguanYuetui);
                }

            }


        }
    }

}
