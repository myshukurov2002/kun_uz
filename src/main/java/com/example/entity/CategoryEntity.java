package com.example.entity;import jakarta.persistence.*;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import java.time.LocalDateTime;@Entity@Table(name = "category")@Data@NoArgsConstructor@AllArgsConstructorpublic class CategoryEntity {    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Integer id;    @Column(name = "order_number",            unique = true)    private Integer orderNumber;    @Column(name = "name_uz")    private String nameUZ;    @Column(name = "name_ru")    private String nameRU;    @Column(name = "name_en")    private String nameEN;    @Column(name = "visibility")    private Boolean visibility;    @Column(name = "created_date")    private LocalDateTime createdDate = LocalDateTime.now();    public enum Language {        uz, ru, en    }}