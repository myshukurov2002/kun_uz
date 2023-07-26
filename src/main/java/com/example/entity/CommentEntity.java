package com.example.entity;import jakarta.persistence.*;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import java.time.LocalDateTime;import java.util.List;@Entity@Table(name = "comment")@Data@NoArgsConstructor@AllArgsConstructorpublic class CommentEntity extends BaseStringEntity {    @Column(name = "updated_date")    private LocalDateTime updatedDate;    @Column(name = "profile_id")    private Long profileId;    @ManyToOne(fetch = FetchType.EAGER)    @JoinColumn(name = "profile_id",            nullable = false,            insertable = false, updatable = false)    private ProfileEntity profileEntity;    @Column(name = "content",            columnDefinition = "TEXT")    private String content;    @Column(name = "article_id")    private String articleId;    @ManyToOne(fetch = FetchType.EAGER)    @JoinColumn(name = "article_id",            nullable = false,            insertable = false, updatable = false)    private ArticleEntity articleEntity;    @Column(name = "reply_comment_id")    private String replyCommentId;    @ManyToOne(fetch = FetchType.EAGER)    @JoinColumn(name = "reply_comment_id",            insertable = false, updatable = false)    private CommentEntity replyCommentEntity;}