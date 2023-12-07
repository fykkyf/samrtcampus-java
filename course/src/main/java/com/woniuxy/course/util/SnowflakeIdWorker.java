package com.woniuxy.course.util;

/**
 * @author Nostalgia丶小冯
 * @date 2023/11/28 19:27
 */

public class SnowflakeIdWorker {
    /**
     * 开始时间：2020-01-01 00:00:00
     */
    private final long beginTs = 1577808000000L;

    //定义了一个常量workerIdBits，表示工作ID所占用的位数，值为10。
    private final long workerIdBits = 10;

    //计算了最大工作ID的值，通过将-1左移workerIdBits位后与-1进行按位异或运算得到。
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    //定义了一个常量sequenceBits，表示序列号所占用的位数，值为12。
    private final long sequenceBits = 12;

    //计算了最大序列号的值，通过将-1左移sequenceBits位后与-1进行按位异或运算得到。
    private final long maxSequence = -1L ^ (-1L << sequenceBits);

    //计算了时间戳左移的位数，等于工作ID和序列号所占用的位数之和。
    private final long timestampLeftOffset = workerIdBits + sequenceBits;

    // 计算了工作ID左移的位数，等于序列号所占用的位数。
    private final long workerIdLeftOffset = sequenceBits;

    //定义了一个私有变量workerId，表示当前实例的工作ID。
    private long workerId;

    //定义了一个私有变量sequence，表示当前实例的序列号
    private long sequence = 0L;

    //定义了一个私有变量lastTimestamp，表示上一次生成ID的时间戳。
    private long lastTimestamp = -1L;

    //构造函数，接收一个参数workerId，用于初始化当前实例的工作ID。如果传入的workerId超出了允许的范围，会抛出IllegalArgumentException异常。
    public SnowflakeIdWorker(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("WorkerId必须大于或等于0且小于或等于%d", maxWorkerId));
        }

        this.workerId = workerId;
    }

    //同步方法nextId，用于生成下一个Snowflake ID。首先获取当前系统时间戳ts，
    // 然后根据时间戳和序列号的关系生成新的ID。如果当前时间戳小于上一次生成ID的时间戳，
    // 说明系统时钟回退了，会抛出RuntimeException异常。最后返回生成的ID。
    public synchronized long nextId() {
        long ts = System.currentTimeMillis();
        if (ts < lastTimestamp) {
            throw new RuntimeException(String.format("系统时钟回退了%d毫秒", (lastTimestamp - ts)));
        }

        // 同一时间内，则计算序列号
        if (ts == lastTimestamp) {
            // 序列号溢出
            if (++sequence > maxSequence) {
                ts = tilNextMillis(lastTimestamp);
                sequence = 0L;
            }
        } else {
            // 时间戳改变，重置序列号
            sequence = 0L;
        }

        lastTimestamp = ts;

        // 0 - 00000000 00000000 00000000 00000000 00000000 0 - 00000000 00 - 00000000 0000
        // 左移后，低位补0，进行按位或运算相当于二进制拼接
        // 本来高位还有个0<<63，0与任何数字按位或都是本身，所以写不写效果一样
        return (ts - beginTs) << timestampLeftOffset | workerId << workerIdLeftOffset | sequence;
    }

    /**
     * 阻塞到下一个毫秒
     *
     * @param lastTimestamp
     * @return
     */
    //私有方法tilNextMillis，用于阻塞到下一个毫秒。
    // 通过不断获取当前系统时间戳ts，直到ts大于上一次生成ID的时间戳为止，然后返回ts作为新的毫秒时间戳。
    private long tilNextMillis(long lastTimestamp) {
        long ts = System.currentTimeMillis();
        while (ts <= lastTimestamp) {
            ts = System.currentTimeMillis();
        }

        return ts;
    }

}
