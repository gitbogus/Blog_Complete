package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.CommentRequestDto;
import com.codepresso.codepressoblog.controller.dto.CommentResponseDto;
import com.codepresso.codepressoblog.service.CommentService;
import com.codepresso.codepressoblog.service.UserSessionService;
import com.codepresso.codepressoblog.vo.Comment;
import com.codepresso.codepressoblog.vo.UserSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class CommentController {
    private CommentService commentService;
    private UserSessionService userSessionService;

    @GetMapping("/comment")
    public List<CommentResponseDto> getCommentListByPost(@RequestParam("post_id") Integer postId, @RequestParam Integer page) {
        List<Comment> comments = commentService.getCommentListByPostIdAndPage(postId, page, 3);

        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for(Comment comment : comments) {
            commentResponseDtos.add(new CommentResponseDto(comment));
        }

        return commentResponseDtos;
    }

    @PostMapping("/comment")
    public ResponseEntity<String> createComment(@RequestBody CommentRequestDto commentDto, @CookieValue("id") Integer sessionId) {
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if(userSession == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
        Integer logInUserId = userSession.getUserId();

        Comment comment = commentDto.getComment();
        comment.setUserId(logInUserId);
        commentService.saveComment(comment);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @PutMapping("/comment")
    public ResponseEntity<String> updateComment(@RequestBody CommentRequestDto commentDto, @CookieValue("id") Integer sessionId) {
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if(userSession == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
        Integer logInUserId = userSession.getUserId();

        Comment comment = commentDto.getComment();
        Boolean result = commentService.updateComment(comment, logInUserId);
        log.info("result" + result);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
    }

    @DeleteMapping("/comment")
    public ResponseEntity<String> deleteComment(@RequestParam Integer id, @CookieValue("id") Integer sessionId) {
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if(userSession == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
        Integer logInUserId = userSession.getUserId();

        Boolean result = commentService.deleteComment(id, logInUserId);

        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
    }
}
