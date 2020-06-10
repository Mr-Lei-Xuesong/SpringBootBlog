package com.demo.service;

import com.demo.bean.Maps;
import com.demo.mapper.MapsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapsService {
    @Autowired
    MapsMapper mapsMapper;

    public Maps byNum(String name) {
        return mapsMapper.byNum(name);
    }
}
