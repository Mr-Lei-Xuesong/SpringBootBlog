package com.demo;

import com.demo.bean.*;
import com.demo.service.*;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.ListIterator;

@SpringBootTest
class SpringBootBlogApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;
    @Autowired
    CountService countService;
    @Autowired
    MapsService mapsService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("12345");
        User login = userService.login(user);
        System.out.println(login);
    }

    @Test
    void test() {
        PageInfo<Comment> all = commentService.getAll(1, 5);
        System.out.println(all);
    }

    @Test
    void test1() {
        Comment comment = new Comment();
        comment.setContent("增加测试内容");
        comment.setAuthor("增加测试作者");
        int i = commentService.addComment(comment);
        if (i > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    @Test
    void test2() {
        Article article = new Article();
        article.setTitle("测试标题");
        article.setContent("测试内容");
        int i = articleService.addArticle(article);
        if (i > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    @Test
    void test3() {
        List<Article> articles = articleService.QueryArticle();
        System.out.println(articles);
    }

    @Test
    void test4() {
        Comment comment = new Comment();
//        comment.setId(18);
//        comment.setContent("文章");
//        comment.setAuthor("雪");
        List<Comment> byCondition = commentService.findByCondition(comment);
        System.out.println(byCondition);
    }

    @Test
    void test5() {
        User user = new User();
        user.setUsername("测试");
        user.setPassword("123456");
        user.setPic("测试");
        int i = userService.saveUser(user);
        if (i > 0) {
            System.out.println("保存成功");
        } else {
            System.out.println("保存失败");
        }
    }

    @Test
    void test6() {
        User users = userService.userList(8);
        String[] split = users.getPic().split(";");
        for (String s : split) {
            System.out.println(s);
        }
    }

    @Test
    void test7() {
        List<Count> counts = countService.getAll();
        System.out.println(counts);
    }

    @Test
    void test8(){
        Maps num = mapsService.byNum("遂宁");
        System.out.println(num);
    }
}
