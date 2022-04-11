package com.ivan.question2;

import java.util.Iterator;

/**
 * @author: WB
 * @version: v1.0
 */
public class TestQueue {

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.push(1);
        queue.push(3);
        queue.push(2);
        queue.push(4);
        queue.push(5);

//        boolean success = queue.remove(1);
//        boolean success = queue.remove(5);
//        boolean success = queue.remove(3);
//        boolean success = queue.remove(7);
//        System.out.println(success);

//        Integer pop = queue.pop();
//        while (pop != null) {
//            System.out.println(pop);
//
//            pop = queue.pop();
//        }

        print(queue);
    }

    public static void print(Queue<Integer> queue) {
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
