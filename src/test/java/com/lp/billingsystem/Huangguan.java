package com.lp.billingsystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lp.billingsystem.domain.HuangguanYuetui;
import com.lp.billingsystem.util.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class Huangguan {

    @Test
    public void read81lc() throws IOException {


//        退税分红股东实货量

        HuangguanYuetui huangguanYuetui = new HuangguanYuetui();
        //获取工作簿
        XSSFWorkbook workbook = new XSSFWorkbook("D:\\data\\tengda\\月退\\2023.01\\ys81lc.xlsx");
        //获取工作表
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            int k = 0;
            if (i == 1){
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
                huangguanYuetui.setAccountNumber("ys81ls");
                huangguanYuetui.setNumberOfLayers(2);
                huangguanYuetui.setType("2");
                huangguanYuetui.setStartDate(DateUtil.startDate());
                huangguanYuetui.setEndDate(DateUtil.endDate());

                System.out.println(huangguanYuetui);

            }
            if (i>1){
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
                huangguanYuetui.setAccountNumber("ys81ls");
                huangguanYuetui.setNumberOfLayers(2);
                huangguanYuetui.setType("2");
                huangguanYuetui.setStartDate(DateUtil.startDate());
                huangguanYuetui.setEndDate(DateUtil.endDate());
                System.out.println(huangguanYuetui);
            }


        }
//            int i = 0;
//            System.out.println(row.getCell(3).getCellType().getCode());
//            System.out.println(row.getCell(3));
//
        short lastCellNum = sheet.getRow(1).getLastCellNum();
//总代理结果
        //股东


//        出现正数没有10退一
        System.out.println("row = " + sheet.getRow(1).getLastCellNum());


    }


}
