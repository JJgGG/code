package com.example.ssyx.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ssyx.acl.service.RoleService;
import com.example.ssyx.common.result.Result;
import com.example.ssyx.model.acl.Role;
import com.example.ssyx.vo.acl.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/acl/role")
@Api(tags = "用户管理")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
            RoleQueryVo roleQueryVo) {
        Page<Role> pageParam = new Page<>(page, limit);
        IPage<Role> pageModel = roleService.selectRolePage(pageParam, roleQueryVo);
        return Result.ok(pageModel);
    }

}