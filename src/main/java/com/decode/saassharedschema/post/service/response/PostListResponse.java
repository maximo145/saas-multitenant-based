package com.decode.saassharedschema.post.service.response;

import com.decode.saassharedschema.post.service.dto.NewPostDto;
import com.decode.saassharedschema.post.service.dto.PostDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostListResponse {
    private List<NewPostDto> newPostList = null;
    private List<PostDto> postList = null;
    public PostListResponse newPostListResponse(List<NewPostDto> newPostList) {
        this.newPostList = newPostList;
        return this;
    }
    public PostListResponse postListResponse(List<PostDto> postList) {
        this.postList = postList;
        return this;
    }

}
