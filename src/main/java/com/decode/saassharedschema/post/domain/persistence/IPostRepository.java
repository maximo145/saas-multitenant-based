package com.decode.saassharedschema.post.domain.persistence;

import com.decode.saassharedschema.post.domain.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT post FROM Post post INNER JOIN FETCH post.author WHERE post.tenant.id = :tenantId")
    List<Post> findByTenantIdWithAuthor(long tenantId);
    @Query("SELECT post FROM Post post INNER JOIN FETCH post.author")
    List<Post> findByAdmin();

    Optional<Post> findByIdAndTenantId(long postId, long tenantId);
}
