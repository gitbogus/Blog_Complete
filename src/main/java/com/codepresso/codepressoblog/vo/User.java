package com.codepresso.codepressoblog.vo;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class User {
    Integer id;
    String email;
    String name;
    String password;
    Date createAt;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
