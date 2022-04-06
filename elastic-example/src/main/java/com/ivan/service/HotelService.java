package com.ivan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ivan.entity.Hotel;
import com.ivan.entity.PageResult;
import com.ivan.entity.RequestParams;

/**
 * @author: WB
 * @version: v1.0
 */
public interface HotelService extends IService<Hotel> {
    PageResult search(RequestParams params);
}
