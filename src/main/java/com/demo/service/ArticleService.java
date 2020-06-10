package com.demo.service;

import com.demo.bean.Article;
import com.demo.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Transactional
    public int addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    public List<Article> QueryArticle(){
        return articleMapper.QueryArticle();
    }
}
