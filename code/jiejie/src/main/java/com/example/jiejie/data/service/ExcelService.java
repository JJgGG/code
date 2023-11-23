package com.example.jiejie.data.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.jiejie.data.entity.UserInfoDTO;
import com.example.jiejie.data.entity.student;

import java.util.List;

public interface ExcelService extends IService<student> {
    public List<UserInfoDTO> filterUserInfo(String fileId, UserInfoDTO userInfoDTO);
}
