package com.ivan.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
@NoArgsConstructor
public class HotelDoc {

    private Long id;        //酒店id
    private String name;    //酒店名称
    private String address; //酒店地址
    private Integer price;  //酒店价格
    private Integer score;  //酒店评分
    private String brand;   //酒店品牌
    private String city;    //所在城市
    private String starName;//酒店星级，1星到5星，1钻到5钻
    private String business;//商圈
    private String location;//地理位置（经纬度: 纬度, 经度）
    private String pic;     //酒店图片
    private Object distance; //距酒店的距离
    private Boolean isAD;   //是否是广告（是广告则加权）

    public HotelDoc(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.price = hotel.getPrice();
        this.score = hotel.getScore();
        this.brand = hotel.getBrand();
        this.city = hotel.getCity();
        this.starName = hotel.getStarName();
        this.business = hotel.getBusiness();
        this.location = hotel.getLatitude() + ", " + hotel.getLongitude();
        this.pic = hotel.getPic();
    }
}
