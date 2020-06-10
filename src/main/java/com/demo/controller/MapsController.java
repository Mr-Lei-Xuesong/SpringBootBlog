package com.demo.controller;

import com.demo.bean.Maps;
import com.demo.service.MapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapsController {
    @Autowired
    MapsService mapsService;

    @RequestMapping("/toMap")
    public String toMap() {
        return "map";
    }

    @RequestMapping("/maps")
    public String getMaps(String name, Model model) {
        Maps maps = mapsService.byNum(name);
        String[] nums = maps.getNum().split(",");
        String city = maps.getName();
        String x = nums[0];
        String y = nums[1];
        model.addAttribute("city", city);
        model.addAttribute("x",x);
        model.addAttribute("y",y);
        return "map";
    }
}
