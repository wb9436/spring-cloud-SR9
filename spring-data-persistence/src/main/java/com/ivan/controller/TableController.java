package com.ivan.controller;

import com.ivan.common.R;
import com.ivan.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: WB
 * @version: v1.0
 */
@RestController
public class TableController {

    @Autowired
    private TableService tableService;

    @RequestMapping("/get")
    public R getTable() {
        return tableService.getAllTable();
    }

    @RequestMapping("/table/{tableId}/{userId}")
    public R createTable(@PathVariable("tableId")String tableId, Integer userId) {
        return tableService.createTable(tableId, userId);
    }

    @RequestMapping("/user/{tableId}/{userId}")
    public R joinTable(@PathVariable("tableId")String tableId, Integer userId) {
        return tableService.joinTable(tableId, userId);
    }


}
