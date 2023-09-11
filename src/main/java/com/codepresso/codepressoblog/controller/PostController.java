package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.PostRequestDto;
import com.codepresso.codepressoblog.controller.dto.PostResponseDto;
import com.codepresso.codepressoblog.service.PostService;
import com.codepresso.codepressoblog.service.UserSessionService;
import com.codepresso.codepressoblog.vo.Post;
import com.codepresso.codepressoblog.vo.UserSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class PostController {
    private PostService postService;
    private UserSessionService userSessionService;

    @GetMapping("/post")
    public List<PostResponseDto> getPostList(@RequestParam Integer page) {
        List<Post> postList = postService.getPostListByPage(page, 3);

        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for(Post post : postList) {
            postResponseDtos.add(new PostResponseDto(post));
        }

        return postResponseDtos;
    }

    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody @Validated PostRequestDto postDto, @CookieValue("id") Integer sessionId) {
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if(userSession == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
        Integer logInUserId = userSession.getUserId();

        Post post = postDto.getPost();
        post.setUserId(logInUserId);
        postService.savePost(post);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @PutMapping("/post")
    public ResponseEntity<String> updatePost(@RequestBody PostRequestDto postDto, @CookieValue("id") Integer sessionId) {
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        if(userSession == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
        Integer logInUserId = userSession.getUserId();

        Post post = postDto.getPost();
        Boolean result = postService.updatePost(post, logInUserId);

        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
    }

    @DeleteMapping("/post")
    public ResponseEntity<String> deletePost(@RequestParam Integer id, @CookieValue("id") Integer sessionId){
        UserSession userSession = userSessionService.getUserSessionById(sessionId);
        Integer logInUserId = userSession.getUserId();

        Boolean result = postService.deletePost(id, logInUserId);

        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
    }
}
