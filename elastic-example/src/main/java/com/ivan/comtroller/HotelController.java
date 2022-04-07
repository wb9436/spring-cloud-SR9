package com.ivan.comtroller;

import com.alibaba.fastjson.JSONObject;
import com.ivan.entity.Hotel;
import com.ivan.entity.PageResult;
import com.ivan.entity.RequestParams;
import com.ivan.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: WB
 * @version: v1.0
 */
@Slf4j
@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping("/find/{id}")
    public Hotel findById(@PathVariable("id") Long id) {
        return hotelService.getById(id);
    }

    @PostMapping("/list")
    public PageResult search(@RequestBody RequestParams params) {
        log.info("查询酒店信息：params={}", JSONObject.toJSONString(params));
        return hotelService.search(params);
    }

    @PostMapping("/filters")
    public Map<String, List<String>> getFilters(@RequestBody RequestParams params) {
        return hotelService.getFilters(params);
    }

    @GetMapping("/suggestion")
    public List<String> getSuggestion(@RequestParam("key") String prefix) {
        return hotelService.getSuggestion(prefix);
    }
}
