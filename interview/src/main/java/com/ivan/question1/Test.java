package com.ivan.question1;

/**
 * @author: WB
 * @version: v1.0
 */
public class Test {
    public static void main(String[] args) {
        Event event = new Event();

        new H1(event);
        new M1(event);
        new L1(event);

        event.setSound(1200); //假设：> 1000高频
        event.setSound(600); //假设：500-100中频
        event.setSound(100); //假设：< 500低频
    }
}
