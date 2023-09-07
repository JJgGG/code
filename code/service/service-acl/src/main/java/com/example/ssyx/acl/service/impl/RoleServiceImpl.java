package com.example.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ssyx.acl.mapper.RoleMapper;
import com.example.ssyx.acl.service.AdminRoleService;

import com.example.ssyx.acl.service.RoleService;
import com.example.ssyx.model.acl.AdminRole;
import com.example.ssyx.model.acl.Role;
import com.example.ssyx.vo.acl.RoleQueryVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private AdminRoleService adminRoleService;

    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        //获取条件值
        String roleName = roleQueryVo.getRoleName();
        //创建mp条件对象
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        //根据条件进行查询
        if (!StringUtils.isEmpty(roleName)) {
            wrapper.like(Role::getRoleName, roleName);
        }
        //进行分页查询
        IPage<Role> rolePage = baseMapper.selectPage(pageParam, wrapper);
        return rolePage;
    }

    /**
     * 根据用户获取用户角色
     *
     * @param adminId
     * @return
     */
    @Override
    public Map<String, Object> findRoleByUserId(Long adminId) {
        //查询所有的角色
        List<Role> allRolesList = baseMapper.selectList(null);
        //根据用户id查询用户分配角色列表
        //根据用户id查询  用户关系表 admin_role 查询用户分配角色id列表
        //list<AdminRole>
        LambdaQueryWrapper<AdminRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AdminRole::getAdminId, adminId);
        List<AdminRole> adminRoleList = adminRoleService.list(lambdaQueryWrapper);
        List<Long> roleIdsList = adminRoleList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        //创建新的list集合，用于存储用户配置角色
        List<Role> assignRoleList = new ArrayList<>();
        //遍历所有角色列表allRoleList，得到每个角色
        //判断所有的角色里面是否包含已经分配角色id，封装到assignRoleList集合
        for (Role role : allRolesList) {
            if (roleIdsList.contains(role.getId())) {
                assignRoleList.add(role);
            }
        }
        //封装到map，然后返回
        Map<String, Object> result = new HashMap<>();
        result.put("allRoleList", allRolesList);
        result.put("assignRoles", assignRoleList);
        return result;
    }

    @Override
    public void saveUserRoleRelationShip(Long adminId, Long[] roleIds) {
        //删除之前用户的角色
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId, adminId);
        adminRoleService.remove(wrapper);
        //给用户分配角色
        List<AdminRole> userRoleList = new ArrayList<>();
        for (Long roleId : roleIds) {
            if(StringUtils.isEmpty(roleId)){
                continue;
            }
            AdminRole userRole = new AdminRole();
            userRole.setAdminId(adminId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        adminRoleService.saveBatch(userRoleList);
    }
}
