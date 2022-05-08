package com.ivan.jpa.vo;

import lombok.Data;

/**
 * 用户表对象
 *
 * @author WuBing
 * @date 2022-05-08 17:25
 */
@Data
public class UserInfoVo2 {

    private long id;
    private int userId;
    private String username;
    private int age;

}
