package com.ivan.service;

import com.ivan.common.R;
import com.ivan.common.ResultCode;
import com.ivan.domain.Table;
import com.ivan.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: WB
 * @version: v1.0
 */
@Service
public class TableService {
    private static final Map<String, Table> TABLE_INFO = new ConcurrentHashMap<>();

    @PostConstruct
    public void postConstruct() {
        //初始化牌桌数据
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("E:\\logs\\data\\table_info.txt"));
            List<Table> tables = (List<Table>) ois.readObject();
            if (tables != null) {
                for (Table table : tables) {
                    TABLE_INFO.put(table.getTableId(), table);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void preDestroy() {
        ObjectOutputStream oos = null;
        try {
            //持久化牌桌数据
            List<Table> tables = new ArrayList<>(TABLE_INFO.values());
            oos = new ObjectOutputStream(new FileOutputStream("E:\\logs\\data\\table_info.txt"));
            oos.writeObject(tables);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public synchronized R createTable(String tableId, Integer userId) {
        Table table = TABLE_INFO.get(tableId);
        if (table != null) {
            return R.error(ResultCode.CREATE_TABLE_ERROR);
        }
        table = new Table(tableId);
        User user = new User(userId);
        boolean result = table.addUser(user);
        if (!result) {
            return R.error(ResultCode.CREATE_TABLE_ERROR);
        }
        TABLE_INFO.put(tableId, table);
        return R.result(ResultCode.CREATE_TABLE_OK);
    }

    public R joinTable(String tableId, Integer userId) {
        Table table = TABLE_INFO.get(tableId);
        if (table == null) {
            return R.error(ResultCode.JOIN_TABLE_ERROR);
        }
        User user = new User(userId);
        boolean result = table.addUser(user);
        if (!result) {
            return R.error(ResultCode.JOIN_TABLE_ERROR);
        }
        return R.result(ResultCode.JOIN_TABLE_ERROR);
    }

    public R getAllTable() {
        List<Table> tables = new ArrayList<>(TABLE_INFO.values());
        return R.ok().data("tables", tables);
    }
}
