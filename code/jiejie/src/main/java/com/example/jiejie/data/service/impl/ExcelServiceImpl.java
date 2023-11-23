package com.example.jiejie.data.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.jiejie.data.entity.UserInfoDTO;
import com.example.jiejie.data.entity.student;
import com.example.jiejie.data.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    ExcelService excelService;
    @Override
    public List<UserInfoDTO> filterUserInfo(String fileId, UserInfoDTO userInfoDTO) {
        student byId = excelService.getById(fileId);

        List<UserInfoDTO> filteredUserInfoList = new ArrayList<>();
//        for (UserInfoDTO userInfo : userInfoList) {
            // 在这里根据条件筛选用户信息
            if (byId.getSname().equals(userInfoDTO.getSname()) && byId.getSsex().equals(userInfoDTO.getSsex())) {
//                filteredUserInfoList.add(userInfo);
                filteredUserInfoList.add(userInfoDTO);
            }
//        }

        return filteredUserInfoList;
    }

    @Override
    public boolean saveBatch(Collection<student> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<student> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<student> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(student entity) {
        return false;
    }

    @Override
    public student getOne(Wrapper<student> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<student> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<student> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<student> getBaseMapper() {
        return null;
    }

    @Override
    public Class<student> getEntityClass() {
        return null;
    }
}

