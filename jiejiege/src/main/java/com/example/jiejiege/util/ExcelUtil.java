package com.example.jiejiege.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtil<T> {

    public static void outputExcel(HttpServletResponse response, List list, Class cla, String sheetName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String fileName = URLEncoder.encode(sheetName, "UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet sheet = EasyExcel.writerSheet(0, sheetName).head(cla).build();
        excelWriter.write(list, sheet);
        excelWriter.finish();
    }
}