package com.example.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ssyx.acl.mapper.RoleMapper;
import com.example.ssyx.acl.service.RoleService;
import com.example.ssyx.model.acl.Role;
import com.example.ssyx.vo.acl.RoleQueryVo;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        //获取条件值
        String roleName = roleQueryVo.getRoleName();
        //创建mp条件对象
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        //根据条件进行查询
        if(!StringUtils.isEmpty(roleName)){
            wrapper.like(Role :: getRoleName,roleName);
        }
        //进行分页查询
        IPage<Role> rolePage = baseMapper.selectPage(pageParam, wrapper);
        return rolePage;
    }
}
