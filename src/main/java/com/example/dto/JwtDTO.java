package com.example.dto;import com.example.entity.ProfileEntity;import lombok.AllArgsConstructor;import lombok.Data;@Data@AllArgsConstructorpublic class JwtDTO {    private Long id;    private ProfileEntity.Role role;}