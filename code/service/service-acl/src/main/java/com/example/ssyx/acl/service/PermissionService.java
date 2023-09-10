package com.example.ssyx.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ssyx.model.acl.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService extends IService<Permission> {
    List<Permission> queryAllPermission();

    void removeChildById(Long id);

    Map<String, Object> findPermisionByRoleId(Long roleId);

    void saveUserPermissionRelationShip(Long roleId, Long[] permissionId);
}
