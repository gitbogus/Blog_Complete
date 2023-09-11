package com.codepresso.codepressoblog.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class Comment {
    Integer id;
    Integer userId;
    Integer postId;
    String content;
    User user;
    Date createdAt;

    public Comment(Integer id, Integer userId, Integer postId, String content) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }

    public Comment(Integer id, Integer userId,String content, String email, String name) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.user = new User(email, name);
    }
}
