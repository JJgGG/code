package com.example.jiejiege.controller;


import com.example.jiejiege.service.IBugService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/bug")
public class BugController {
    @Autowired
    private IBugService bugService;

    @ApiOperation("bug管理-导出全部数据excel")
    @GetMapping("/outputBugByExcel")
    public void outputBugByExcel() throws IOException {
        bugService.outputBugByExcel();
    }
}
