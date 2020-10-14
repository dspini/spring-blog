package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.repositories.PostRepository;
import com.codeup.blog.repositories.UserRepository;
import com.codeup.blog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private EmailService emailService;

    public PostController(PostRepository postRepo, UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String showAllPosts(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showOnePost(@PathVariable long id, Model model) {
        Post post = postRepo.getOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "posts/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String createPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body,
                             Model model) {
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        postRepo.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id, Model model) {
        Post post = postRepo.getOne(id);
        if (post != null) {
            postRepo.delete(post);
        }
        return "redirect:/posts";
    }


    @GetMapping("/posts/edit/{id}")
    public String showEditPost(@PathVariable long id, Model model) {
        Post post = postRepo.getOne(id);
        if (post == null) {
            return "redirect:/posts/index";
        }
        model.addAttribute("post", post);
        return "posts/edit";
    }


    @PostMapping("/posts/edit")
    public String updatePost(@RequestParam(name = "id") long id,
                             @RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body,
                             Model model) {
        Post post = postRepo.getOne(id);
        if (post == null) {
            return "redirect:/posts/index";
        }
        post.setTitle(title);
        post.setBody(body);
        postRepo.save(post);
        emailService.prepareAndSend(post,"You have created a new post",
                "Your post \""+post.getTitle()+
                        "\" was successfully created.\nYou can see it at http://localhost:8080/posts/"+post.getId()+"\nThank you.");
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("posts/hardcoded/create")
    public String createHardcodedAd() {
        Post post = new Post();
        post.setTitle("Love letter to my beloved");
        post.setBody("Sometimes Furby... I think you are the only one who understands me... Thanks for being there for me.");
        post.setUser(userRepo.getOne(1L));
        postRepo.save(post);
        return "redirect:/posts";
    }

//    @GetMapping("/posts/editcreate")
//    public String editCreatePost(Model model) {
//        Post post = new Post();
//        model.addAttribute("post", post);
//        return "/posts/edit";
//    }

}