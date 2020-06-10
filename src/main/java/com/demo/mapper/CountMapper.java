package com.demo.mapper;

import com.demo.bean.Count;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CountMapper {
    List<Count> getAll();
}
