package com.example.dto;import com.example.entity.CommentEntity;import com.example.entity.CommentLikeEntity;import com.example.entity.ProfileEntity;import com.fasterxml.jackson.annotation.JsonInclude;import lombok.Data;@Data@JsonInclude(JsonInclude.Include.NON_NULL)public class CommentLikeDTO extends BaseStringDTO {    private Long profileId;    private ProfileEntity profileEntity;    private String commentId;    private CommentEntity commentEntity;    private CommentLikeEntity.Status status;}