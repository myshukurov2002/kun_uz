package com.example.entity;import jakarta.persistence.Entity;import jakarta.persistence.Table;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;@Entity@Table(name = "email_address")@NoArgsConstructor@Datapublic class EmailAddressEntity extends BaseEntity{    private String email;    private String password;    public EmailAddressEntity(String email, String password) {        this.email = email;        this.password = password;    }}