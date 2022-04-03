package com.ivan.entity;

/**
 * @author: WB
 * @version: v1.0
 */
public class RequestEntity {

    private Integer userId;
    private String name;

    public RequestEntity() {
    }

    public RequestEntity(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
