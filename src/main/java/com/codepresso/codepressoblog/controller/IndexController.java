package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.PostResponseDto;
import com.codepresso.codepressoblog.service.PostService;
import com.codepresso.codepressoblog.vo.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private PostService postService;

    public IndexController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/")
    public String index(Model model) {
        List<Post> postList = postService.getPostListByPage(1, 3);
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for(Post post : postList) {
            postResponseDtos.add(new PostResponseDto(post));
        }

        model.addAttribute("posts", postResponseDtos);
        return "index";
    }
}
