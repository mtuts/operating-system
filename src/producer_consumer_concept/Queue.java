package producer_consumer_concept;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Queue {
    
    private int[] queue;
    private int current;
    private int head;
    private ReentrantLock mutex;
    private Condition empty_cond;
    private Condition full_cond;

    public Queue(int size) {
        queue = new int[size + 1];
        current = 0;
        head = 0;
        mutex = new ReentrantLock();
        empty_cond = mutex.newCondition();
        full_cond = mutex.newCondition();
    }

    public boolean isEmpty() {
        return current == head;
    }

    public boolean isFull() {
        return (current + 1) % queue.length == head;
    }

    public void add(int item) {
      mutex.lock();
      while (isFull()) {
        try {
          full_cond.await();
        } catch (InterruptedException e) {}
      }
      queue[current] = item;
      current = (current + 1) % queue.length;
      empty_cond.signal();
      mutex.unlock();
    }

    public int dequeue() {
      mutex.lock();
      while (isEmpty()) {
        try {
          empty_cond.await();
        } catch (InterruptedException e) {}
      }
      int item = queue[head];
      head = (head + 1) % queue.length;

      full_cond.signal();
      mutex.unlock();
      return item;
    }

    public void printInfo() {
        System.out.printf("head: %10d, current: %10d\n", head, current);
    }


}
