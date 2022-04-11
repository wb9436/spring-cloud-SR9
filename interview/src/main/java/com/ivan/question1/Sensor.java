package com.ivan.question1;

/**
 * 声音传感器
 *
 * @author: WB
 * @version: v1.0
 */
public class Sensor {

    /**
     * 解析声音频段
     *
     * @param sound 声音
     * @return
     */
    public static int handlerSound(int sound) {
        if (sound >= 1000) {
            return EventType.EvenH;
        } else if (sound > 500) {
            return EventType.EventM;
        } else {
            return EventType.EventL;
        }
    }


}
