package com.example.entity;import jakarta.persistence.*;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import java.math.BigInteger;import java.time.LocalDateTime;@Entity@Table(name = "article")@Data@AllArgsConstructor@NoArgsConstructorpublic class ArticleEntity extends BaseStringEntity {    @Column(name = "title")    private String title;    @Column(name = "description")    private String description;    @Column(name = "content", columnDefinition = "text")    private String content;    @Column(name = "shared_count")    private Integer sharedCount;    @Column(name = "attach_id")    private String attachId;    @OneToOne()    @JoinColumn(name = "attach_id", insertable = false, updatable = false)    private AttachEntity attachEntity;    @Column(name = "region_id")    private Long regionId;    @ManyToOne    @JoinColumn(name = "region_id", insertable = false, updatable = false)    private RegionEntity regionEntity;    @Column(name = "category_id")    private Long categoryId;    @ManyToOne()    @JoinColumn(name = "category_id", insertable = false, updatable = false)    private CategoryEntity categoryEntity;    @Column(name = "moderator_id")    private Long moderatorId;    @OneToOne    @JoinColumn(name = "moderator_id", insertable = false, updatable = false)    private ProfileEntity moderator;    @Column(name = "publisher_id")    private Long publisherId;    @OneToOne    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)    private ProfileEntity publisher;    @Enumerated(EnumType.STRING)    private Status status = Status.NOT_PUBLISHED;    @Column(name = "published_date")    private LocalDateTime publishedDate;    @Column(name = "view_count")    private Integer viewCount;    @Column(name = "like_count")    private BigInteger likeCount = BigInteger.valueOf(0);    @Column(name = "dis_like_count")    private BigInteger disLikeCount = BigInteger.valueOf(0);;    public enum Status {        PUBLISHED, NOT_PUBLISHED, BLOCKED    }}