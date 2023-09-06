package com.example.ssyx.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ssyx.acl.service.AdminRoleService;
import com.example.ssyx.acl.service.RoleService;
import com.example.ssyx.acl.service.AdminService;
import com.example.ssyx.common.result.Result;
import com.example.ssyx.model.acl.Admin;
import com.example.ssyx.common.utils.MD5;
import com.example.ssyx.model.acl.AdminRole;
import com.example.ssyx.model.acl.Role;
import com.example.ssyx.vo.acl.AdminQueryVo;
import com.example.ssyx.vo.acl.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/admin/acl/user")
@Api(tags = "用户管理")
@CrossOrigin //跨域
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminRoleService adminRoleService;

    @ApiOperation("根据用户分配角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long adminId,@RequestParam Long[] roleId) {
        roleService.saveUserRoleRelationShip(adminId,roleId);
        return Result.ok(null);
    }

    @ApiOperation("根据用户获取角色数据")
    @GetMapping("toAssign/{adminId}")
    public Result toAssign(@PathVariable Long adminId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(adminId);
        return Result.ok(roleMap);
    }

    @ApiOperation("获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "AdminQueryVo", value = "查询对象", required = false)
            AdminQueryVo adminQueryVo) {
        Page<Admin> pageParam = new Page<>(page, limit);
        IPage<Admin> pageModel = adminService.selectPage(pageParam, adminQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "获取管理角色")
    @PostMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return Result.ok(admin);
    }

    @ApiOperation(value = "新增管理角色")
    @PostMapping("save")
    public Result save(@RequestBody Admin user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        return Result.ok(adminService.save(user));
    }


    @ApiOperation(value = "修改管理用户")
    @PutMapping("update")
    public Result updateById(@RequestBody Admin user) {
        return Result.ok(adminService.updateById(user));
    }

    @ApiOperation(value = "删除管理角色")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        return Result.ok(adminService.removeById(id));
    }

    @ApiOperation(value = "根据id列表删除管理角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        return Result.ok(adminService.removeByIds(idList));
    }
}
