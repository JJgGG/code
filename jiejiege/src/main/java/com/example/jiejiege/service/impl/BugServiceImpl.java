package com.example.jiejiege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jiejiege.entity.Bug;
import com.example.jiejiege.mapper.BugMapper;
import com.example.jiejiege.model.BugOutputExcelModel;
import com.example.jiejiege.service.IBugService;
import com.example.jiejiege.util.ExcelUtil;
import org.apache.catalina.User;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.FunctionMapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class BugServiceImpl extends ServiceImpl<BugMapper, Bug> implements IBugService {
    @Autowired
    private BugMapper bugMapper;
    @Autowired
    private HttpServletResponse response;

    @Override
    public void outputBugByExcel() throws IOException {

//        ExcelUtil.outputExcel(response, list, BugOutputExcelModel.class, "缺陷信息");
    }
}
