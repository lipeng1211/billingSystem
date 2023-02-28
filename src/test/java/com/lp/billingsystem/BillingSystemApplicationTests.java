package com.lp.billingsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lp.billingsystem.config.redis.RedisCache;
import com.lp.billingsystem.domain.HuangguanDaima;
import com.lp.billingsystem.domain.HuangguanYuetui;
import com.lp.billingsystem.service.HuangguanDaimaService;
import com.lp.billingsystem.service.HuangguanYuetuiService;
import com.lp.billingsystem.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

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
    @Test
    void contextLoads() throws IOException {
//        readYs81lc();
        readYs301b();
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
