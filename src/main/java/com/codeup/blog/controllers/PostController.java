package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String indexPage() {
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewPost() {
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String viewForm() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "posts/create";
    }

}