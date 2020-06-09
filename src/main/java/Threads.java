public class Threads {
    public static void main(String[] args)
            throws InterruptedException {
        // Object of a class that has both produce()
        // and consume() methods
        final ProducerConsumer producerConsumer = new ProducerConsumer();

        // Create producer thread
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create consumer thread
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create consumer2 thread
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.consume2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();
        t3.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
        t3.join();
    }
}