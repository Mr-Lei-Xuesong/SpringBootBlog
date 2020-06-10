package com.demo.controller;

import com.demo.bean.Count;
import com.demo.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CountController {
    @Autowired
    CountService countService;

    @RequestMapping("/toCount")
    public String toCount() {
        return "/emp/count";
    }

    @RequestMapping("/count")
    @ResponseBody
    public List<Count> count() {
        return countService.getAll();
    }

}
