package com.module.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.demo.model.Route;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteMapper extends BaseMapper<Route> {

    List<Route> selectRouteListBySort();

}
