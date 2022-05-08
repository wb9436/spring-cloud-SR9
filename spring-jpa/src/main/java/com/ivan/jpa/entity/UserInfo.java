package com.ivan.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户表对象
 *
 * @author WuBing
 * @date 2022-05-08 17:25
 */
@Data
@Entity(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer userId;
    private String username;
    private Integer age;
    private Date loginTime;
    private Date registerTime;
}
