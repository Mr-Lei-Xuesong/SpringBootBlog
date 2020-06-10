package com.demo.mapper;

import com.demo.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ArticleMapper {

    int addArticle(Article article);

    List<Article> QueryArticle();
}
