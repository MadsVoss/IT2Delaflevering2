import java.util.LinkedList;

// This class has a listgui, producer (adds items to listgui
// and consumber (removes items).
public class ProducerConsumer {
    Sensor sensor;
    // Create a listgui shared by producer and consumer
    // Size of listgui is 2.
    LinkedList<Integer> listgui = new LinkedList<>();
    LinkedList<Integer> listdb = new LinkedList<>();
    int capacity = 1000;

    // Function called by producer thread
    public void produce() throws InterruptedException {

        while (true) {
            synchronized (this) {
                // producer thread waits while listgui
                // is full
                while (listgui.size() == capacity)
                    wait();
                int value = sensor.getData();
                //System.out.println("Producer produced-"
                //        + value);

                // to insert the jobs in the listgui
                listgui.add(value);

                // notifies the consumer thread that
                // now it can start consuming
                notify();

                // makes the working of program easier
                // to understand
                Thread.sleep(1000);
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // consumer thread waits while listgui
                // is empty
                while (listgui.size() < 50)
                    wait();

                // to retrive the ifrst job in the listgui
                LinkedList<Integer> removedObject = listgui;
                listgui = new LinkedList<>();
                listgui.removeAll(removedObject);

                // Wake up producer thread
                notify();

                // and sleep
                Thread.sleep(1000);
            }
        }
    }

    // Function called by consumer thread
    public void consume2() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // consumer thread waits while listgui
                // is empty
                while (listgui.size() < 100)
                    wait();

                // to retrive the ifrst job in the listgui
                LinkedList<Integer> removedObject = listdb;
                listdb = new LinkedList<>();
                listdb.removeAll(removedObject);

                // Wake up producer thread
                notify();

                // and sleep
                Thread.sleep(1000);
            }
        }
    }

    public void addToQueue(int data) {
        synchronized (this){
            this.listgui.add(data);
            this.listdb.add(data);
            notify();

        }
    }
}
