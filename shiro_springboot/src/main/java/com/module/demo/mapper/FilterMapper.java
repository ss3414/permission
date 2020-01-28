package com.module.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.demo.model.Filter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterMapper extends BaseMapper<Filter> {

    List<Filter> selectFilterListBySort();

}
