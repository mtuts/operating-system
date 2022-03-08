package producer_consumer_concept;

public class Demo {
    public static void main(String[] args) {
        Queue q = new Queue(5);
        Producer p1 = new Producer(q);
        Producer p2 = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        p1.start();
        p2.start();
        c1.start();
        c2.start();

    }
}
