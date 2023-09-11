package com.codepresso.codepressoblog.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Post {
    Integer id;
    Integer userId;
    String title;
    String content;
    Date createdAt;
    User user;

    public Post(Integer id, Integer userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public Post(Integer id, Integer userId, String title, String content, String email, String name) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.user = new User(email, name);
    }
}
