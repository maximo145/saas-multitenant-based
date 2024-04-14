package com.decode.saassharedschema.post.mapping;

import com.decode.saassharedschema.post.domain.entity.Post;
import com.decode.saassharedschema.post.service.dto.NewPostDto;
import com.decode.saassharedschema.post.service.dto.PostDto;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class PostMapping extends ModelMapper implements Serializable {
    public PostMapping() {
        super();
    }

    public Post model(NewPostDto newPostDto) {
        return this.map(newPostDto, Post.class);
    }

    public NewPostDto newModelDto(Post post) {
        return this.map(post, NewPostDto.class);
    }

    public PostDto modelDto(Post post) {
        return this.map(post, PostDto.class);
    }

    public List<NewPostDto> modelNewList(List<Post> posts) {
        return posts.stream().map(x -> this.map(x, NewPostDto.class)).collect(Collectors.toList());
    }

    public List<PostDto> modelList(List<Post> posts) {
        return posts.stream().map(x -> this.map(x, PostDto.class)).collect(Collectors.toList());
    }
}
