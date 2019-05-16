package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.model.ShiroFilter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterMapper extends BaseMapper<ShiroFilter> {

    List<ShiroFilter> selectFilterListBySort();

}
