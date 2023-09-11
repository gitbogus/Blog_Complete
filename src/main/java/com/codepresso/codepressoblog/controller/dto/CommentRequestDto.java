package com.codepresso.codepressoblog.controller.dto;

import com.codepresso.codepressoblog.vo.Comment;
import lombok.Setter;

@Setter
public class CommentRequestDto {
    Integer id;
    Integer userId;
    Integer postId;
    String content;

    public Comment getComment() {
        return new Comment(this.id, this.userId, this.postId, this.content);
    }
}
