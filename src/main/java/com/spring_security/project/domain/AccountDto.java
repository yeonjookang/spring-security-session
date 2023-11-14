package com.spring_security.project.domain;

import lombok.Data;

@Data
public class AccountDto {
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;


    public Account toEntity() {
        return new Account(null,
                this.username,
                this.password,
                this.email,
                this.age,
                this.role);
    }
}
