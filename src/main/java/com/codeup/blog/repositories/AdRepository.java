package com.codeup.blog.repositories;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdRepository extends JpaRepository<Post.Ad, Long> {
        // The following method is equivalent to the built in `getOne` method, there's no need to create this example
        @Query("from Ad a where a.id like ?1")
        Post.Ad getAdById(long id);

        // The following method shows you how to use named parameters in a HQL custom query:
        @Query("from Ad a where a.title like %:term%")
        List<Post.Ad> searchByTitleLike(@Param("term") String term);
    }
