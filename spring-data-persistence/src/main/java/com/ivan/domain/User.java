package com.ivan.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
public class User implements Serializable {
    private Integer userId;
    private String nickname;
    private Integer position;

    public User(Integer userId) {
        this.userId = userId;
        this.nickname = "游客" + userId;
    }
}
