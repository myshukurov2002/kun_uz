package com.example.entity;import jakarta.persistence.*;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import java.time.LocalDateTime;@Entity@Table(name = "region")@Data@AllArgsConstructor@NoArgsConstructorpublic class RegionEntity extends BaseEntity{    @Column(name = "order_number",            unique = true)    private Integer orderNumber;    @Column(name = "name_uz")    private String nameUZ;    @Column(name = "name_ru")    private String nameRU;    @Column(name = "name_en")    private String nameEN;    @Column    private Long prtId;    public enum Language {        uz, ru, en    }}