package com.demo.service;

import com.demo.bean.Count;
import com.demo.mapper.CountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountService {
    @Autowired
    CountMapper countMapper;

    public List<Count> getAll() {
        return countMapper.getAll();
    }
}
