package com.codepresso.codepressoblog.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserSession {
    Integer id;
    Integer userId;
    String name;
    Date createdAt;

    public UserSession(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
