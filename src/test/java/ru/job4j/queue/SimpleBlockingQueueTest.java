package ru.job4j.queue;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class SimpleBlockingQueueTest {

    @Test
    public void whenProducerAddsThenConsumerGets() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

        Thread producer = new Thread(() -> {
            try {
                queue.offer(1);
                queue.offer(2);
                queue.offer(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        Thread consumer = new Thread(() -> {
            try {
                assertThat(queue.poll()).isEqualTo(1);
                assertThat(queue.poll()).isEqualTo(2);
                assertThat(queue.poll()).isEqualTo(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        consumer.start();
        producer.join();
        consumer.join();
    }
}