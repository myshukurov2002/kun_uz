package com.example.repository;import com.example.entity.CommentLikeEntity;import jakarta.transaction.Transactional;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.Modifying;import org.springframework.data.jpa.repository.Query;import org.springframework.data.repository.query.Param;import java.util.Optional;public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, String> {    Boolean existsByProfileId(Long id);    Optional<CommentLikeEntity> findByProfileIdAndCommentId(Long id, String commentId);    @Modifying    @Transactional    @Query(value = "UPDATE comment_like" +            " SET status = :status" +            " WHERE id = :id", nativeQuery = true)    Integer updateStatusByProfileId(@Param("id") Long id, @Param("status") CommentLikeEntity.Status status);}