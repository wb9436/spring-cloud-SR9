package com.ivan.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 测试全部信息：可以比数据库字段少但是不能多
 *
 * @author WuBing
 * @date 2022-05-13 9:54
 */
@Data
@Entity
@Table(name = "full_info")
public class FullInfo implements Serializable {
    private static final long serialVersionUID = -2433920234964385918L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId; //
    private String username; //
    private String nickname; //
    private String address; //
    private int balance; //
//    private Date registerTime; //
    private Date loginTime; //

}
