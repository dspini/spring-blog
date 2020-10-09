package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import com.codeup.blog.repositories.PostRepository;
import com.codeup.blog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public PostController(PostRepository posts, UserRepository users) {

        this.postRepo = posts;
        this.userRepo = users;
    }

    @GetMapping("/posts")
    public String showPosts(Model model){

        model.addAttribute("posts", postRepo.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showPost(@PathVariable long id, Model model) {
        Post post = postRepo.getOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@RequestParam String title, @RequestParam String body){
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        newPost.setAuthor(userRepo.getOne(1L));
        postRepo.save(newPost);
        return "new post created";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post post = postRepo.getOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String editPost(@RequestParam String title, @RequestParam String body, @RequestParam String id) {
        Post post = postRepo.getOne(Long.valueOf(id));
        post.setTitle(title);
        post.setBody(body);
        postRepo.save(post);
        return "post modified";
    }

    private void init() {

        Post post = new Post();
        post.setTitle("first post");
        post.setBody("post body text");
        post.setId(4L);
        postRepo.save(post);
        Post post2 = new Post();
        post2.setTitle("second post");
        post2.setBody("post body text");
        post2.setId(44L);
        postRepo.save(post2);

    }
}

