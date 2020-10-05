package com.codeup.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String indexPage(Model viewModel) {
        viewModel.addAttribute("posts");
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(Model viewModel) {
        viewModel.addAttribute("post");
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewForm(Model model) {
        model.addAttribute("post");
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(Model model) {
        model.addAttribute("post");
        return "posts/create";
    }

}