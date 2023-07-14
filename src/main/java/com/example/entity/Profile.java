package com.example.entity;import jakarta.persistence.*;import lombok.AllArgsConstructor;import lombok.Data;import lombok.NoArgsConstructor;import java.time.LocalDateTime;@Entity@Table(name = "profile")@Data@AllArgsConstructor@NoArgsConstructorpublic class Profile {    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Integer id;    @Column(name = "name",            nullable = false)    private String name;    @Column(name = "surname",            nullable = false)    private String surname;    @Column(name = "email",            nullable = false,            unique = true)    private String email;    @Column(name = "phone",            nullable = false,            unique = true,            length = 13)    private String phone;    @Column(name = "password",            nullable = false)    private String password;    @Column(name = "status",            nullable = false)    @Enumerated(EnumType.STRING)    private Status status;    @Column(name = "role",            nullable = false)    @Enumerated(EnumType.STRING)    private Role role;    @Column(name = "visibility",            nullable = false)    private Boolean visibility;    @Column(name = "created_date",            nullable = false)    private LocalDateTime createdDate;    @OneToOne(fetch = FetchType.EAGER)    @JoinColumn(name = "image_id")    private Attach image;    public enum Status {        ACTIVE, INACTIVE    }    public enum Role {        ADMIN, USER, PUBLISHER, MODERATOR    }}