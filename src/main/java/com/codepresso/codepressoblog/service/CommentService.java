package com.codepresso.codepressoblog.service;

import com.codepresso.codepressoblog.mapper.CommentMapper;
import com.codepresso.codepressoblog.vo.Comment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CommentService {
    private CommentMapper commentMapper;

    public List<Comment> getCommentListByPostIdAndPage(Integer postId, Integer page, Integer size) {
        List<Comment> comments =  commentMapper.findByPostIdAndPage(postId, size, (page-1) * size);
        return comments;
    }

    public Integer getCommentCountByPostId(Integer postId) {
        return commentMapper.count(postId);
    }

    public Boolean saveComment(Comment comment) {
        Integer result = commentMapper.save(comment);
        return result == 1;
    }

    public Boolean updateComment(Comment comment, Integer logInUserId) {
        Comment original = commentMapper.findOne(comment.getId());
        if(!original.getUserId().equals(logInUserId)) {
            return false;
        }

        Integer result = commentMapper.update(comment);
        return result == 1;
    }

    public Boolean deleteComment(Integer id, Integer logInUserId) {
        Comment original = commentMapper.findOne(id);
        if(!original.getUserId().equals(logInUserId)) {
            return false;
        }

        Integer result = commentMapper.delete(id);
        return result == 1;
    }
}
