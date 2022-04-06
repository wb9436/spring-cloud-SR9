package com.ivan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
@TableName("tb_hotel")
public class Hotel {

    private Long id;        //酒店id
    private String name;    //酒店名称
    private String address; //酒店地址
    private Integer price;  //酒店价格
    private Integer score;  //酒店评分
    private String brand;   //酒店品牌
    private String city;    //所在城市
    private String starName;//酒店星级，1星到5星，1钻到5钻
    private String business;//商圈
    private String latitude;//纬度
    private String longitude;//经度
    private String pic;     //酒店图片

}
