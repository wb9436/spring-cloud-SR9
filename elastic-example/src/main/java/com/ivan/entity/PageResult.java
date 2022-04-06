package com.ivan.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
@NoArgsConstructor
public class PageResult {
    private Long total;
    private List<HotelDoc> hotels;

    public PageResult(Long total, List<HotelDoc> hotels) {
        this.total = total;
        this.hotels = hotels;
    }
}
