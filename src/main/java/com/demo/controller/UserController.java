package com.demo.controller;

import com.demo.bean.User;
import com.demo.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Value("${uploadfilepath}")
    private String uploadfilepath;

    @RequestMapping("/user/login")
    public String login(User user, Model model, HttpSession session) {
        User login = userService.login(user);
        if (login != null) {
            session.setAttribute("loginUser", user.getUsername());
            return "main";
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.html";
    }

    @RequestMapping("/user/add")
    public String userAdd(MultipartFile[] fileupload, Model model, User user) {
        String files = "";
        if (fileupload != null) {
            for (MultipartFile file : fileupload) {
                String filename = file.getOriginalFilename();
                filename = UUID.randomUUID() + "_" + filename;
                files += filename + ";";
                File filepath = new File(uploadfilepath);
                if (!filepath.exists()) {
                    filepath.mkdir();
                }
                try {
                    file.transferTo(new File(uploadfilepath + filename));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        user.setPic(files);
        if (userService.saveUser(user) != 0) {
            model.addAttribute("msg", "注册成功");
        } else {
            model.addAttribute("msg", "注册失败");
        }
        return "index";
    }

    @RequestMapping("/userList")
    public String userList(Model model) {
        Integer id = 10;
        User users = userService.userList(id);
        model.addAttribute("users", users);
        String[] picfiles = users.getPic().split(";");
        model.addAttribute("picfiles", picfiles);
        return "/emp/userList";
    }

    @GetMapping("/fileDownload")
    public ResponseEntity<byte[]> fileDownload(String filename) {
        File file = new File(uploadfilepath + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", filename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage().getBytes(), HttpStatus.EXPECTATION_FAILED);
        }
    }
}