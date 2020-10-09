package com.codeup.blog.repositories;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);
    Post findFirstByTitle(String title);

    @Query("from Post p where p.title like %:term%")
    List<Post> searchByTitleLike(@Param("term") String term);
}