package com.example.ssyx.client.product;


import com.example.ssyx.model.product.Category;
import com.example.ssyx.model.product.SkuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "service-product")
public interface ProductFeignClient {

    @GetMapping("/api/product/inner/getCategory/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId);

    @GetMapping("/api/product/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable("skuId") Long skuId);

    //根据skuId列表得到sku信息列表
    @PostMapping("/api/product/inner/findSkuInfoList")
    public List<SkuInfo> findSkuInfoList(@RequestBody List<Long> skuIdList);
}