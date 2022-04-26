package com.ivan.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author: WB
 * @version: v1.0
 */
@Data
@Slf4j
public class Table implements Serializable {

    private String tableId;
    private User[] users;

    public Table(String tableId) {
        this.tableId = tableId;
        users = new User[3];
    }

    public synchronized boolean addUser(User user) {
        int remainPos = -1;
        for (int pos = 0; pos < users.length; pos++) {
            User posUser = users[pos];
            if (posUser == null) {
                if (remainPos == -1) {
                    remainPos = pos;
                }
            } else if (Objects.equals(posUser.getUserId(), user.getUserId())) {
                log.info("{} 已经加入牌桌，不需要重新加入！", user.getUserId());
                return true;
            }
        }
        if (remainPos != -1) {
            user.setPosition(remainPos);
            users[remainPos] = user;
            return true;
        }
        return false;
    }

}
