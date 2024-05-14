package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class QueueTest {

    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

        Executor executor = Executors.newFixedThreadPool(1);
        queue.put(10);

        executor.execute(() -> {
            try {
                queue.put(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread.sleep(1000);
        System.out.println(queue.size()); // 결과값: 1
        System.out.println(queue.take()); // 결과값: 10
        Thread.sleep(1000);
        System.out.println(queue.size());  // 결과값: 1
        System.out.println(queue.take());  // 결과값: 15
    }
}
