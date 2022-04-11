package com.ivan.question1;

/**
 * @author: WB
 * @version: v1.0
 */
public abstract class Processor {
    protected Event event;
    protected int eventType; //表示当前处理器支持的波段类型

    public abstract void handler(); // 处理声音的方法

}
