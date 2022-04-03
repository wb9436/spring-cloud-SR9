package com.ivan.feign.retryer;

import feign.RetryableException;
import feign.Retryer;

import java.util.concurrent.TimeUnit;

/**
 * 自定义Feign重试策略
 *
 * @author: WB
 * @version: v1.0
 */
public class MyFeignRetryer implements Retryer {

    /**
     * 最大尝试尝试次数
     */
    private final int maxAttempts;
    /**
     * 时间间隔
     */
    private final long period;
    /**
     * 最大时间间隔
     */
    private final long maxPeriod;
    /**
     * 当前是进行第几次重试
     */
    int attempt;
    long sleptForMillis;

    public MyFeignRetryer() {
        this(100, TimeUnit.SECONDS.toMillis(1), 1);
    }

    public MyFeignRetryer(long period, long maxPeriod, int maxAttempts) {
        this.period = period;
        this.maxPeriod = maxPeriod;
        this.maxAttempts = maxAttempts;
        this.attempt = 1;
    }

    private long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public void continueOrPropagate(RetryableException e) {
        if (attempt++ >= maxAttempts) {
            throw e;
        }
        long interval;
        if (e.retryAfter() != null) {
            interval = e.retryAfter().getTime() - currentTimeMillis();
            if (interval > maxPeriod) {
                interval = maxPeriod;
            }
            if (interval < 0) {
                return;
            }
        } else {
            interval = nextMaxInterval();
        }
        try {
            Thread.sleep(interval);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
            throw e;
        }
        sleptForMillis += interval;
    }

    long nextMaxInterval() {
        long interval = (long) (period * Math.pow(1.5, attempt - 1));
        return Math.min(interval, maxPeriod);
    }

    @Override
    public Retryer clone() {
        return new MyFeignRetryer(period, maxPeriod, maxAttempts);
    }
}
