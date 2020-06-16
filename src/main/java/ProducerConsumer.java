/** @author {Mads Voss, Mikkel Bech, Dalia Pireh, Sali Azou, Beant Sandhu}*/
import data.EKGListener;
import data.EKGObservable;
import java.util.LinkedList;

public class ProducerConsumer implements EKGObservable {
    Sensor sensor;
    LinkedList<Integer> listGUI = new LinkedList<>();
    LinkedList<Integer> listDB = new LinkedList<>();
    int capacity = 1000;
    private EKGListener listenerGUI;
    private EKGListener listenerDB;

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (listGUI.size() == capacity)
                    wait();
                int value = sensor.getData();
                //System.out.println("Producer produced-"+ value);
                listGUI.add(value);
                listDB.add(value);
                notify();
                Thread.sleep(20);
            }
        }
    }
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (listGUI.size() < 50)
                    wait();
                LinkedList<Integer> removedObject = listGUI;
                listGUI = new LinkedList<>();
                if(listenerGUI != null) {
                    listenerGUI.notifyEKG(removedObject);
                }
                notify();
                Thread.sleep(20);
            }
        }
    }
    public void consume2() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (listDB.size() < 100)
                    wait();
                LinkedList<Integer> removedObject = listDB;
                listDB = new LinkedList<>();
                if(listenerDB != null) {
                    listenerDB.notifyEKG(removedObject);
                }
                notify();
                Thread.sleep(20);
            }
        }
    }
    public void addToQueue(int data) {
        synchronized (this){
            this.listGUI.add(data);
            this.listDB.add(data);
            notify();

        }
    }

    @Override
    public void registerGUI(EKGListener listenerGUI) {
        this.listenerGUI = listenerGUI;
    }

    @Override
    public void registerDB(EKGListener listenerDB) {
        this.listenerDB = listenerDB;
    }
}
