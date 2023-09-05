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
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiOperation(value = "获取角色")
    @PostMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Role role = roleService.getById(id);
        return Result.ok(role);
    }
    @ApiOperation(value = "新增角色")
    @PostMapping("update")
    public Result updateById(@RequestBody Role role){
        boolean b = roleService.updateById(role);
        return Result.ok(b);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        return Result.ok(roleService.removeById(id));
    }

    @ApiOperation(value = "根据id列表删除角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        return Result.ok(roleService.removeByIds(idList));
    }
}
