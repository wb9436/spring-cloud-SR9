package com.ivan.question2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: WB
 * @version: v1.0
 */
public class TreeTest {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.push(1);
        tree.push(2);
        tree.push(3);
        tree.push(4);
        tree.push(5);
        tree.push(6);
        tree.push(7);

        tree.remove(3);

        SortQueue<Integer> sortQueue = tree.getSortQueue();
        for (int i = 0; i < 4; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < (1 << i); j++) {
                Integer pop = sortQueue.pop();
                if (pop != null) {
                    list.add(pop);
                }
            }
            System.out.println(Arrays.toString(list.toArray()));
        }
    }


}
