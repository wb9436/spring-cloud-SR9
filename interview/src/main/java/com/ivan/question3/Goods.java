package com.ivan.question3;

/**
 * 比如当前商品类只针对烤鸭熟食类商品
 * <p>
 * 同一品牌可认为是重复数据，只显示价格低的
 *
 * @author: WB
 * @version: v1.0
 */
public class Goods {
    private int bid; //商家ID
    private int brand; //品牌ID
    private int price; //商品价格

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getBrand() {
        return brand;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
