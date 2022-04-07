package com.ivan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ivan.entity.Hotel;
import com.ivan.entity.PageResult;
import com.ivan.entity.RequestParams;

import java.util.List;
import java.util.Map;

/**
 * @author: WB
 * @version: v1.0
 */
public interface HotelService extends IService<Hotel> {
    PageResult search(RequestParams params);

    Map<String, List<String>> getFilters(RequestParams params);

    List<String> getSuggestion(String prefix);
}
