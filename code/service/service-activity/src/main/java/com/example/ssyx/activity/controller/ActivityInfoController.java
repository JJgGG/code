package com.example.ssyx.activity.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ssyx.activity.service.ActivityInfoService;
import com.example.ssyx.common.result.Result;
import com.example.ssyx.model.activity.ActivityInfo;
import com.example.ssyx.model.product.SkuInfo;
import com.example.ssyx.vo.activity.ActivityRuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 活动表 前端控制器
 * </p>
 *
 * @author example
 * @since 2023-09-19
 */
@RestController
@RequestMapping("/admin/activity/activityInfo")
//@CrossOrigin
public class ActivityInfoController {
    @Autowired
    private ActivityInfoService activityInfoService;

    //列表
//    url: `${api_name}/${page}/${limit}`,
//    method: 'get'
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit) {
        Page<ActivityInfo> pageParam = new Page<>(page, limit);
        IPage<ActivityInfo> pageModel = activityInfoService.selectPage(pageParam);
        return Result.ok(pageModel);
    }

    /**
     * 添加
     *
     * @param activityInfo
     * @return
     */
    @PostMapping("save")
    public Result save(@RequestBody ActivityInfo activityInfo) {
        activityInfoService.save(activityInfo);
        return Result.ok(null);
    }

    //营销活动规则相关接口
    //1 根据活动id获取活动规则数据
//    url: `${api_name}/findActivityRuleList/${id}`,
//    method: 'get'
    @GetMapping("findActivityRuleList/{id}")
    public Result findActivityRuleList(@PathVariable Long id) {
        Map<String, Object> activityRuleMap =
                activityInfoService.findActivityRuleList(id);
        return Result.ok(activityRuleMap);
    }

    /**
     * 在活动里面添加规则数据
     * @param activityRuleVo
     * @return
     */
    @PostMapping("saveActivityRule")
    public Result saveActivityRule(@RequestBody ActivityRuleVo activityRuleVo) {
        activityInfoService.saveActivityRule(activityRuleVo);
        return Result.ok(null);
    }

//    findSkuInfoByKeyword(keyword) {
//        return request({
//                url: `${api_name}/findSkuInfoByKeyword/${keyword}`,
//        method: 'get'
//    })

    /**
     * 根据关键字查询匹配sku信息
     * @param keyword
     * @return
     */
    @GetMapping("findSkuInfoByKeyword/{keyword}")
    public Result findSkuInfoByKeyword(@PathVariable("keyword") String keyword){
        List<SkuInfo> list = activityInfoService.findSkuInfoByKeyword(keyword);
        return Result.ok(list);
    }

    //    url: `${api_name}/get/${id}`,
//    method: 'get'
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        ActivityInfo activityInfo = activityInfoService.getById(id);
        activityInfo.setActivityTypeString(activityInfo.getActivityType().getComment());
        return Result.ok(activityInfo);
    }

}

