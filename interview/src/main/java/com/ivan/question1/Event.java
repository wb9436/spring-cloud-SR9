package com.ivan.question1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: WB
 * @version: v1.0
 */
public class Event {

    private final List<Processor> processors = new ArrayList<>(); // 声音处理器
    private int evenType; //声音频段

    public int getEvenType() {
        return evenType;
    }

    public void setSound(int sound) {
        this.evenType = Sensor.handlerSound(sound);
        notifyProcessors();
    }

    public void add(Processor processor){
        processors.add(processor);
    }

    public void notifyProcessors() {
        for (Processor processor : processors) {
            processor.handler();
        }
    }


}
