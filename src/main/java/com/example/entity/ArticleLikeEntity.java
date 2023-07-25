package com.example.entity;import jakarta.persistence.*;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import java.time.LocalDateTime;import java.util.UUID;@Entity@Table(name = "article_like")@Data@NoArgsConstructor@AllArgsConstructorpublic class ArticleLikeEntity extends BaseStringEntity{    @ManyToOne(fetch = FetchType.LAZY)    @JoinColumn(name = "profile_id",            nullable = false)    private ProfileEntity profileEntity;    @ManyToOne(fetch = FetchType.LAZY)    @JoinColumn(name = "article_id",            nullable = false)    private ArticleEntity articleEntity;    @Enumerated(EnumType.STRING)    private ProfileEntity.Status status;}