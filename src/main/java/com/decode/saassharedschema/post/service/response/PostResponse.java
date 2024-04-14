package com.decode.saassharedschema.post.service.response;

import com.decode.saassharedschema.post.service.dto.NewPostDto;
import com.decode.saassharedschema.post.service.dto.PostDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostResponse {
    private NewPostDto newPostDto = null;
    private PostDto postDto = null;

    public PostResponse newPost(NewPostDto newPostDto){
        this.newPostDto = newPostDto;
        return this;
    }

    public PostResponse post(PostDto postDto){
        this.postDto = postDto;
        return this;
    }
}
