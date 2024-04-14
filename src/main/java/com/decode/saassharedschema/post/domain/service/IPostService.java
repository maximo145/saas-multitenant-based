package com.decode.saassharedschema.post.domain.service;

import com.decode.saassharedschema.post.service.dto.NewPostDto;
import com.decode.saassharedschema.post.service.response.PostListResponse;
import com.decode.saassharedschema.post.service.response.PostResponse;
import com.decode.saassharedschema.util.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface IPostService {
    ResponseDto<PostListResponse> listPosts(Long tenantId) throws Exception;
    ResponseDto<PostResponse> retrievePost(long tenantId, Long id) throws Exception;
    ResponseDto<PostResponse> registerPost(long tenantId, NewPostDto newPostDto) throws Exception;
    ResponseDto<PostResponse> updatePost(long tenantId, NewPostDto newPostDto) throws Exception;
    ResponseDto deletePost(long tenantId, Long id) throws Exception;
}
