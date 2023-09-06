package com.example.ssyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ssyx.model.acl.Admin;
import com.example.ssyx.model.acl.Role;
import com.example.ssyx.vo.acl.AdminQueryVo;
import com.example.ssyx.vo.acl.RoleQueryVo;

public interface AdminService extends IService<Admin> {
    IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo adminQueryVo);
}
