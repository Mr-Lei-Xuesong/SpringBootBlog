package com.demo.mapper;

import com.demo.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    List<Comment> getAll();

    int delComment(int id);

    int updComment(Comment comment);

    Comment getById(int id);

    int addComment(Comment comment);

    List<Comment> findByCondition(Comment comment);
}
