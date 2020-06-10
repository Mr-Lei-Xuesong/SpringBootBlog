package com.demo.controller;

import com.demo.bean.Article;
import com.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/art")
    public String toAdd() {
        return "/emp/add";
    }

    @ResponseBody
    @PostMapping("/addart")
    public String addArticle(Article article){
        try {
            if (articleService.addArticle(article)!=0){
                return "ok";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "error";
    }

    @RequestMapping("/query")
    public String QueryArticle(Model model){
        List<Article> articles = articleService.QueryArticle();
        model.addAttribute("querys",articles);
        return "/emp/query";
    }
}
