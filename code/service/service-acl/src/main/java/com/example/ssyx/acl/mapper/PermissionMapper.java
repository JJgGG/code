package com.example.ssyx.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ssyx.model.acl.Permission;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
}
