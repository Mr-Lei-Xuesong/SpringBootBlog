package com.demo.controller;

import com.demo.bean.Comment;
import com.demo.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("/list")
    public String getAll(Model model, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        PageInfo<Comment> pageInfo = commentService.getAll(pageNo, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "emp/list";
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delComment(int id) {
        if (commentService.delComment(id) != 0) {
            return "ok";
        } else {
            return "error";
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteBatch(@RequestParam("checklist[]") String[] checklist) {
        if (commentService.delBatch(checklist) == 1) {
            return "ok";
        } else {
            return "error";
        }
    }

    @ResponseBody
    @PostMapping("/getEditComment")
    public Comment getEditComment(Integer id) {
        try {
            return commentService.getById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @ResponseBody
    @PostMapping("/update")
    public String updateComment(Comment comment) {
        try {
            if (commentService.updComment(comment) != 0) {
                return "ok";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "error";
    }

    @ResponseBody
    @RequestMapping("/addComment")
    public String addComment(Comment comment) {
        try {
            if (commentService.addComment(comment) != 0) {
                return "ok";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "error";
    }

    @RequestMapping("/findBy")
    public String findByCondition(Model model,Comment comment,@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "5") int pageSize){
        try {
            PageHelper.startPage(pageNo,pageSize);
            List<Comment> commentList = commentService.findByCondition(comment);
            PageInfo<Comment> pageInfo = new PageInfo<>(commentList);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("comment",comment);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "emp/list";
    }
}
