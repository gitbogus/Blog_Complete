package com.codepresso.codepressoblog.controller.dto;

import com.codepresso.codepressoblog.vo.Comment;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CommentResponseDto {
    Integer id;
    Integer userId;
    String content;
    String email;
    String name;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.content = comment.getContent();
        this.email = comment.getUser().getEmail();
        this.name = comment.getUser().getName();
    }
}
