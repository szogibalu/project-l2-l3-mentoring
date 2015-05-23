package com.szogibalu.mentoring.multithreading;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class BlockingQueueExercise {


    private static class BlockingQueue<T> {

        private final LinkedList<T> queue;
        private final int size;

        public BlockingQueue(int size) {
            this.queue = new LinkedList<>();
            this.size = size;
        }

        public void put(T item) throws InterruptedException {
            synchronized (queue) {
                while (queue.size() == size) {
                    queue.wait();
                }
                queue.add(item);
                queue.notifyAll();
            }
        }

        public T take() throws InterruptedException {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    queue.wait();
                }
                final T first =  queue.remove();
                queue.notifyAll();
                return first;
            }
        }
    }

    private static class Consumer implements Runnable {

        private final BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (!currentThread().isInterrupted()) {
                    System.out.println(currentThread().getId() + ":" + queue.take());
                }
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
    }

    private static class Producer implements Runnable {

        private final BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (Integer i = 1; i <= 100; i++) {
                    queue.put(i);
                }
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new BlockingQueue<>(10);
        ExecutorService executor = newFixedThreadPool(10);

        for (Integer i = 1; i <= 5; i++) {
            executor.execute(new Consumer(queue));
        }

        executor.execute(new Producer(queue));

        sleep(5 * 1000);
        executor.shutdownNow();
    }

}
