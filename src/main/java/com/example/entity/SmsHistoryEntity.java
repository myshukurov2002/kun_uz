package com.example.entity;import jakarta.persistence.*;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;@Entity@Table(name = "sms_history")@Data@NoArgsConstructor@AllArgsConstructorpublic class SmsHistoryEntity {    @Id    @GeneratedValue(strategy = GenerationType.AUTO)    private Long id;    @Column(name = "phone")    private String phone;    @Column(name = "message",            columnDefinition = "TEXT")    private String message;    @Enumerated(EnumType.STRING)    private Status status;    public enum Status {        NEW, USED    }}