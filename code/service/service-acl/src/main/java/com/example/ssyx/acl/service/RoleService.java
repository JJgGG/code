package com.example.ssyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ssyx.model.acl.Role;
import com.example.ssyx.vo.acl.RoleQueryVo;


public interface RoleService extends IService<Role> {
    IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo);


}
