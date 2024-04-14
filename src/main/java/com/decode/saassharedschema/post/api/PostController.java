package com.decode.saassharedschema.post.api;

import com.decode.saassharedschema.post.domain.service.IPostService;
import com.decode.saassharedschema.post.service.dto.NewPostDto;
import com.decode.saassharedschema.post.service.response.PostListResponse;
import com.decode.saassharedschema.post.service.response.PostResponse;
import com.decode.saassharedschema.security.tenants.TenantId;
import com.decode.saassharedschema.util.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private IPostService postService;

    public PostController(IPostService postService){
        this.postService = postService;
    }

    @GetMapping
    public ResponseDto<PostListResponse> listPosts(@TenantId Long tenantId) throws Exception {
        return postService.listPosts(tenantId);
    }

    @GetMapping("{id}")
    public ResponseDto<PostResponse> retrievePost(@TenantId long tenantId, @PathVariable(name = "id") Long id) throws Exception {
        return postService.retrievePost(tenantId, id);
    }

    @PostMapping
    public ResponseDto<PostResponse> registerPost(@TenantId long tenantId, @RequestBody NewPostDto newPostDto) throws Exception {
        return postService.registerPost(tenantId, newPostDto);
    }

    @PutMapping
    public ResponseDto<PostResponse> updatePost(@TenantId long tenantId, @RequestBody NewPostDto newPostDto) throws Exception {
        return postService.updatePost(tenantId, newPostDto);
    }

    @DeleteMapping("{id}")
    public ResponseDto deletePost(@TenantId long tenantId, @PathVariable(name = "id") Long id) throws Exception {
        return postService.deletePost(tenantId, id);
    }

}
