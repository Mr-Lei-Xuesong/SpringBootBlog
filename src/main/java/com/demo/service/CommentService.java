package com.demo.service;

import com.demo.bean.Comment;
import com.demo.mapper.CommentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    public PageInfo<Comment> getAll(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Comment> list = commentMapper.getAll();
        return new PageInfo<>(list);
    }

    @Transactional
    public int delComment(int id) {
        return commentMapper.delComment(id);
    }

    @Transactional
    public int delBatch(String[] listId) {
        try {
            for (String s : listId) {
                int id = Integer.parseInt(s);
                commentMapper.delComment(id);
            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public Comment getById(int id) {
        return commentMapper.getById(id);
    }

    @Transactional
    public int updComment(Comment comment) {
        return commentMapper.updComment(comment);
    }

    @Transactional
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    public List<Comment> findByCondition(Comment comment){
        return commentMapper.findByCondition(comment);
    }
}