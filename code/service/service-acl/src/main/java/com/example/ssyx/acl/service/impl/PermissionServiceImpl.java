package com.example.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ssyx.acl.mapper.PermissionMapper;
import com.example.ssyx.acl.service.PermissionService;
import com.example.ssyx.acl.service.RolePermissionService;
import com.example.ssyx.acl.utils.PermissionHelper;
import com.example.ssyx.model.acl.Permission;
import com.example.ssyx.model.acl.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Permission> queryAllPermission() {
        //1 查询所有菜单
        List<Permission> allPermissionList = baseMapper.selectList(null);

        //2 转换要求数据格式
        List<Permission> result = PermissionHelper.buildPermission(allPermissionList);
        return result;
    }

    @Override
    public void removeChildById(Long id) {
        //删除id值为id的菜单以及子菜单
        List<Long> idList = new ArrayList<>();
        this.getAllPermissionId(id, idList);
        //不要忘记加入本菜单id
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    /**
     * 根据角色id获取对应已经分配的菜单
     *
     * @param roleId
     * @return
     */
    @Override
    public Map<String, Object> findPermisionByRoleId(Long roleId) {
        //查询所有的菜单
        List<Permission> allPermissionList = baseMapper.selectList(null);
        //根据角色id查询已经分配的菜单
        //角色菜单关系表role_permission查询角色分配菜单列表
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        List<RolePermission> rolePermissionList = rolePermissionService.list(wrapper);
        List<Long> permissionIds = rolePermissionList.stream().map(item -> item.getPermissionId()).collect(Collectors.toList());
        //创建新的list集合，用户存储角色配置菜单
        List<Permission> assignPermissionList = new ArrayList<>();
        //遍历所有菜单列表allPermissionList,获取每个菜单
        //判断所有的菜单里面是否包含已经分配的菜单id，封装到assignPermissionList集合
        for (Permission permission : allPermissionList) {
            if (permissionIds.contains(permission.getId())) {
                assignPermissionList.add(permission);
            }
        }
        //封装到map，然后返回
        Map<String, Object> result = new HashMap<>();
        result.put("allPermissionList", allPermissionList);
        result.put("assignPermissions", assignPermissionList);
        return result;
    }

    /**
     * 给用户分配菜单
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void saveUserPermissionRelationShip(Long roleId, Long[] permissionIds) {
        //删除之前角色的菜单
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        rolePermissionService.remove(wrapper);
        //给用户分配角色
        List<RolePermission> rolePermissionList = new ArrayList<>();
        for (Long permissionId : permissionIds) {
            if(StringUtils.isEmpty(permissionId)){
                continue;
            }
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        rolePermissionService.saveBatch(rolePermissionList);

    }

    private void getAllPermissionId(Long id, List<Long> idList) {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPid, id);
        List<Permission> childList = baseMapper.selectList(wrapper);
        childList.stream().forEach(item -> {
            //封装菜单id到idList集合里面
            idList.add(item.getId());
            //递归
            this.getAllPermissionId(item.getId(), idList);
        });
    }


}
