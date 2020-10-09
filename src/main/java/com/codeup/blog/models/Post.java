package com.codeup.blog.models;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    @Column(nullable = false)
    private String title;
    @Column
    private String body;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User writer;

    @Id
    @GeneratedValue
    private long id;

    public Post(long id, String title, String body) {

    }

    public Post(String title, String body) {

    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return writer;
    }

    public void setAuthor(User author) {
        this.writer = author;
    }
}
