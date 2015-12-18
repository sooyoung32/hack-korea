package com.example.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @JsonIgnore
    private Long internalId;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String confirmPassword;

    private Gender gender;
}
