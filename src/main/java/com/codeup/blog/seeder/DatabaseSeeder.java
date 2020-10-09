//package com.codeup.blog.seeder;
//
//import com.codeup.blog.models.Post;
//import com.codeup.blog.repositories.PostRepository;
//import com.codeup.blog.repositories.UserRepository;
//import org.apache.catalina.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//@Component
//public class DatabaseSeeder implements CommandLineRunner {
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    private final PostRepository postDao;
//    private final UserRepository userDao;
//
//    @Value("${app.env}")
//    private String environment;
//
//    public DatabaseSeeder(PostRepository postDao, UserRepository userDao) {
//        this.postDao = postDao;
//        this.userDao = userDao;
//    }
//
//    private List<User> seedUsers() {
//        List<User> users = Arrays.asList(
//                new User(),
//                new User(),
//                new User(),
//                new User(),
//                new User()
//        );
//        userDao.save(users);
//        return users;
//    }
//
//    private void seedPosts(List<User> users) {
//        Post longPost = new Post(
//        );
//        List<Post> posts = Arrays.asList(
//                new Post(),
//                new Post(),
//                new Post(),
//                new Post(),
//                new Post(),
//                new Post(),
//                longPost
//        );
//        Random r = new Random();
//        for (Post p : posts) {
//            User randomUser = users.get(r.nextInt(users.size()));
//            p.setUser(randomUser);
//        }
//        postDao.save(posts);
//    }
//
//    @Override
//    public void run(String... strings) throws Exception {
//        if (! environment.equals("development")) {
//            log.info("app.env is not development, doing nothing.");
//            return;
//        }
//        log.info("Deleting posts...");
//        postDao.deleteAll();
//        log.info("Deleting users...");
//        userDao.deleteAll();
//        log.info("Seeding users...");
//        List<User> users = seedUsers();
//        log.info("Seeding posts...");
//        seedPosts(users);
//        log.info("Finished running seeders!");
//    }
//}