package com.example.entity;import jakarta.persistence.*;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import java.time.LocalDateTime;@Entity@Table(name = "saved_article")@Data@NoArgsConstructor@AllArgsConstructorpublic class SavedArticleEntity {    @Id    @GeneratedValue(strategy = GenerationType.AUTO)    private Long id;    @ManyToOne(fetch = FetchType.LAZY)    @JoinColumn(name = "profile_id")    private ProfileEntity profileEntity;    @ManyToOne(fetch = FetchType.EAGER)    @JoinColumn(name = "article_id")    private ArticleEntity articleEntity;    @Column(name = "created_date")    private LocalDateTime createdDate;}