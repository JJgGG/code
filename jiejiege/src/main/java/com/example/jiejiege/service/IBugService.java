package com.example.jiejiege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.jiejiege.entity.Bug;

import java.io.IOException;

public interface IBugService extends IService<Bug> {
    void outputBugByExcel() throws IOException;
}
