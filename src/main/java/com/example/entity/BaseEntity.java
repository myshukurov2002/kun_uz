package com.example.entity;import jakarta.persistence.*;import lombok.Getter;import lombok.Setter;import java.io.StringReader;import java.time.LocalDateTime;import static java.lang.Boolean.TRUE;@Setter@Getter@MappedSuperclasspublic class BaseEntity {    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Long id;    @Column(name = "visibility")    private Boolean visibility = true;    @Column(name = "created_date")    private LocalDateTime createdDate = LocalDateTime.now();}