package com.example.disrupter;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

public class DisrupterThread {
    public static void main(String[] args) {
        String message = "Hello Disruptor!";
        //必须是2的N次方  因为是环形图  取模运算
        int ringBufferSize = 1024;
        Disruptor<MessageEvent> disruptor = new Disruptor<MessageEvent>(new MessageEventFactory(), ringBufferSize, new MessageThreadFactory(), ProducerType.SINGLE, new BlockingWaitStrategy());
        //这里用的是单一生成者，如果是多生成者的话是另一种模式，自己的类实现WorkHandler接口，
        //然后这边调用    disruptor.handleEventsWithWorkerPool(new MessageEventHandler());
        disruptor.handleEventsWith(new MessageEvenHandler3());
        disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
        RingBuffer<MessageEvent> ringBuffer = disruptor.start();
        MessageEventProducer producer = new MessageEventProducer(ringBuffer);
        IntStream.range(0, 20).forEach(x -> {
            producer.onData(x + message);
        });
    }
}

class MessageEvent<T> {
    private T message;

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}

class MessageEventFactory implements EventFactory<MessageEvent> {

    @Override
    public MessageEvent newInstance() {
        return new MessageEvent();
    }
}

class MessageEvenHandler3 implements EventHandler<MessageEvent> {
    @Override
    public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
        System.out.println("----------------" + messageEvent.getMessage());

    }
}

class MessageEventProducer {

    private RingBuffer<MessageEvent> ringBuffer;

    public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(String message) {
        EventTranslatorOneArg<MessageEvent, String> translator = new MessageEventTranslator();
        ringBuffer.publishEvent(translator, message);
    }
}

class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent, String> {

    @Override
    public void translateTo(MessageEvent messageEvent, long l, String o2) {
        messageEvent.setMessage(o2);
    }
}

class MessageExceptionHandler implements ExceptionHandler {

    @Override
    public void handleEventException(Throwable throwable, long l, Object o) {
        throwable.printStackTrace();
    }

    @Override
    public void handleOnStartException(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {
        throwable.printStackTrace();
    }
}

class MessageThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "Simple Disruptor Test Thread");
    }
}

class MessageEventHandler implements WorkHandler<MessageEvent> {

    @Override
    public void onEvent(MessageEvent messageEvent) throws Exception {
        System.out.println(System.currentTimeMillis() + "------我是1号消费者----------" + messageEvent.getMessage());
    }
}