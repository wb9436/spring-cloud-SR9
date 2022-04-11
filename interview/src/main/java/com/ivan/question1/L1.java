package com.ivan.question1;

/**
 * 处理器具体实现：高频
 *
 * @author: WB
 * @version: v1.0
 */
public class L1 extends Processor {

    public L1(Event event) {
        this.event = event;
        this.eventType = EventType.EventL;
        this.event.add(this);
    }

    @Override
    public void handler() {
        if (this.eventType == this.event.getEvenType()) {
            System.out.println("L1 处理器处理声音");
        }
    }
}
