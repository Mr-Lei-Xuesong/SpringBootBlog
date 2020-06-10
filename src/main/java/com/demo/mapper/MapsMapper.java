package com.demo.mapper;

import com.demo.bean.Maps;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MapsMapper {
    Maps byNum(String name);
}
