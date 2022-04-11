package com.ivan.question1;

/**
 * 处理器具体实现：高频
 *
 * @author: WB
 * @version: v1.0
 */
public class H1 extends Processor {

    public H1(Event event) {
        this.event = event;
        this.eventType = EventType.EvenH;
        this.event.add(this);
    }

    @Override
    public void handler() {
        if (this.eventType == this.event.getEvenType()) {
            System.out.println("H1 处理器处理声音");
        }
    }
}
