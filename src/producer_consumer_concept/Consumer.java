package producer_consumer_concept;

public class Consumer extends Thread {
    
    private Queue queue;
    
    public Consumer(Queue q) {
        queue = q;
    }

    @Override
    public void run() {
        while (true) {
            int n = queue.dequeue();
            System.out.println("Consums " + n);
        }
    }
}
