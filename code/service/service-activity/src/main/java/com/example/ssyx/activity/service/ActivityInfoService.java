package com.example.ssyx.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ssyx.model.activity.ActivityInfo;

import java.util.Map;

/**
 * <p>
 * 活动表 服务类
 * </p>
 *
 * @author example
 * @since 2023-09-19
 */
public interface ActivityInfoService extends IService<ActivityInfo> {

    IPage<ActivityInfo> selectPage(Page<ActivityInfo> pageParam);

    Map<String, Object> findActivityRuleList(Long id);
}
