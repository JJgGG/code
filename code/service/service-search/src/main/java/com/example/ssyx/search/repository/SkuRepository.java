package com.example.ssyx.search.repository;


import com.example.ssyx.vo.search.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuRepository extends ElasticsearchRepository<SkuEs,Long> {


}
