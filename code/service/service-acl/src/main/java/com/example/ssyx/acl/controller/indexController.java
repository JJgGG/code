package com.example.ssyx.acl.controller;

import com.example.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
public class indexController {
    //1.登录接口
    @PostMapping("login")
    public Result login() {
        //返回token值
        Map<String, String> map = new HashMap<>();
        map.put("token", "token-admin");
        return Result.ok(map);
    }
    //2.信息接口
    @GetMapping("info")
    public Result info() {
        Map<String,String> map = new HashMap<>();
        map.put("name","admin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }
    //退出
    @PostMapping("logout")
    public Result logOut() {
        return Result.ok(null);
    }
}
