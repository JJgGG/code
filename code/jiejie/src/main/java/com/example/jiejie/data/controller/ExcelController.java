package com.example.jiejie.data.controller;

import com.alibaba.excel.EasyExcel;
import com.example.jiejie.data.entity.UserInfoDTO;
import com.example.jiejie.data.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController("/haha")
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/export/{fileId}")
    public void exportExcel(@PathVariable String fileId, UserInfoDTO userInfoDTO, HttpServletResponse response) throws IOException {
        List<UserInfoDTO> filteredUserInfoList = excelService.filterUserInfo(fileId, userInfoDTO);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=user_info.xlsx");

        EasyExcel.write(response.getOutputStream(), UserInfoDTO.class)
                .sheet("用户信息")
                .doWrite(filteredUserInfoList);
    }
}
