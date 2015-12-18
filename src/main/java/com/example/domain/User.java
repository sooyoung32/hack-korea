package com.example.domain;

import com.example.vo.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long internalId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", unique = false, nullable = true)
    private String password;

    @Column(name = "name", unique = false, nullable = true)
    private String name;

    @Column(name = "gender", unique = false, nullable = true)
    private Gender gender;

}
