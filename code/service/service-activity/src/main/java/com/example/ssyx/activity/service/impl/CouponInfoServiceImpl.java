package com.example.ssyx.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.ssyx.activity.mapper.CouponInfoMapper;
import com.example.ssyx.activity.mapper.CouponRangeMapper;
import com.example.ssyx.activity.service.CouponInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ssyx.client.product.ProductFeignClient;
import com.example.ssyx.enums.CouponRangeType;
import com.example.ssyx.model.activity.CouponInfo;
import com.example.ssyx.model.activity.CouponRange;
import com.example.ssyx.model.product.Category;
import com.example.ssyx.model.product.SkuInfo;
import com.example.ssyx.vo.activity.CouponRuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author example
 * @since 2023-09-19
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    @Autowired
    private CouponRangeMapper couponRangeMapper;
    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * 分页进行查询
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public IPage<CouponInfo> selectPageCouponInfo(Long page, Long limit) {
        Page<CouponInfo> page1 = new Page<>(page, limit);
        IPage<CouponInfo> couponInfoPage = baseMapper.selectPage(page1, null);
        List<CouponInfo> couponInfoList = couponInfoPage.getRecords();
        couponInfoList.stream().forEach(item -> {
            item.setCouponTypeString(item.getCouponType().getComment());
            CouponRangeType rangeType = item.getRangeType();
            if (rangeType != null) {
                item.setRangeTypeString(rangeType.getComment());
            }
        });
        return couponInfoPage;
    }

    /**
     * 根据id查询优惠卷
     *
     * @param id
     * @return
     */
    @Override
    public CouponInfo getCouponInfo(Long id) {
        CouponInfo couponInfo = baseMapper.selectById(id);
        couponInfo.setCouponTypeString(couponInfo.getCouponType().getComment());
        CouponRangeType rangeType = couponInfo.getRangeType();
        if (rangeType != null) {
            couponInfo.setRangeTypeString(couponInfo.getRangeType().getComment());
        }
        return null;
    }

    /**
     * 根据优惠卷id查询规则数据
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findCouponRuleList(Long id) {
        //第一步 根据优惠卷id查询优惠卷基本信息  coupon_info表
        CouponInfo couponInfo = baseMapper.selectById(id);
        //第二步 根据优惠卷id查询coupon_range 查询里面对应range_id
        List<CouponRange> couponRangeList = couponRangeMapper.selectList(new LambdaQueryWrapper<CouponRange>().eq(CouponRange::getCouponId, id));
        //couponRangeList获取所有range_id
        List<Long> rangeIdList = couponRangeList.stream().map(CouponRange::getRangeId).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<>();
        //// 如果规则类型 SKU      range_id就是skuId值
        //// 如果规则类型 CATEGORY range_id就是分类Id值
        if (!CollectionUtils.isEmpty(rangeIdList)) {
            if (couponInfo.getRangeType() == CouponRangeType.SKU) {
                //// 如果规则类型是SKU ，得到skuId，
                // 远程调用根据多个skuId值获取对应sku信息
                List<SkuInfo> skuInfoList = productFeignClient.findSkuInfoList(rangeIdList);
                result.put("skuInfoList", skuInfoList);

            } else if (couponInfo.getRangeType() == CouponRangeType.CATEGORY) {
                //// 如果规则类型是分类，得到分类Id，远程调用根据多个分类Id值获取对应分类信息
                List<Category> categoryList = productFeignClient.findCategoryList(rangeIdList);
                result.put("categoryList", categoryList);
            }
        }
        return result;
    }

    /**
     * 添加优惠卷规则数据
     *
     * @param couponRuleVo
     */
    @Override
    public void saveCouponRule(CouponRuleVo couponRuleVo) {
        //根据优惠卷id删除规则数据
        couponRangeMapper.delete(new LambdaQueryWrapper<CouponRange>().eq(CouponRange::getCouponId, couponRuleVo.getCouponId()));

        //更新优惠卷基本信息
        CouponInfo couponInfo = baseMapper.selectById(couponRuleVo.getCouponId());
        couponInfo.setRangeType(couponRuleVo.getRangeType());
        couponInfo.setConditionAmount(couponRuleVo.getConditionAmount());
        couponInfo.setAmount(couponRuleVo.getAmount());
        couponInfo.setConditionAmount(couponRuleVo.getConditionAmount());
        couponInfo.setRangeDesc(couponRuleVo.getRangeDesc());

        baseMapper.updateById(couponInfo);

        //添加优惠卷新规则数据
        List<CouponRange> couponRangeList = couponRuleVo.getCouponRangeList();
        for (CouponRange couponRange : couponRangeList) {
            //设置优惠卷id
            couponRange.setCouponId(couponRuleVo.getCouponId());
            //添加
            couponRangeMapper.insert(couponRange);
        }
    }
}
