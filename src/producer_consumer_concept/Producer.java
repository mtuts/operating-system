package producer_consumer_concept;

public class Producer extends Thread {
    private Queue queue;
    
    public Producer(Queue q) {
        queue = q;
    }

    @Override
    public void run() {
        while (true) {
            int n = (int)(Math.random() * 10000);
            System.out.println("Produce " + n);
            queue.add(n);
        }
    }
}