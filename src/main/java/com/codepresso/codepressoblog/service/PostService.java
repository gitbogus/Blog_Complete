package com.codepresso.codepressoblog.service;

import com.codepresso.codepressoblog.mapper.PostMapper;
import com.codepresso.codepressoblog.vo.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PostService {
    private PostMapper postMapper;

    public List<Post> getPostListByPage(Integer page, Integer size) {
        return postMapper.findByPage(size, (page-1) * size);
    }

    public Post getPostById(Integer id) {
        return postMapper.findOne(id);
    }

    public Boolean savePost(Post post) {
        Integer result = postMapper.save(post);
        return result == 1;
    }

    public Boolean updatePost(Post post, Integer logInUserId) {
        Post originalPost = postMapper.findOne(post.getId());
        if(!originalPost.getUserId().equals(logInUserId)) {
            return false;
        }

        Integer result = postMapper.update(post);
        return result == 1;
    }

    public Boolean deletePost(Integer id, Integer logInUserId) {
        Post originalPost = postMapper.findOne(id);
        if(!originalPost.getUserId().equals(logInUserId)) {
            return false;
        }

        Integer result = postMapper.delete(id);
        return result == 1;
    }
}
