package com.example.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ssyx.acl.mapper.AdminMapper;
import com.example.ssyx.acl.service.AdminService;
import com.example.ssyx.model.acl.Admin;
import com.example.ssyx.model.acl.Role;
import com.example.ssyx.vo.acl.AdminQueryVo;
import com.example.ssyx.vo.acl.RoleQueryVo;
import jodd.util.StringUtil;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo adminQueryVo) {
        //获取条件值
        String username = adminQueryVo.getUsername();
        String name = adminQueryVo.getName();
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            lambdaQueryWrapper.like(Admin::getName, username);
        }
        if (!StringUtils.isEmpty(name)) {
            lambdaQueryWrapper.like(Admin::getName, name);
        }
        Page<Admin> adminPage = baseMapper.selectPage(pageParam, lambdaQueryWrapper);
        return adminPage;
    }
}
