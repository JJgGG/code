package com.example.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ssyx.acl.mapper.RolePermissionMapper;
import com.example.ssyx.acl.service.RolePermissionService;
import com.example.ssyx.model.acl.RolePermission;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
