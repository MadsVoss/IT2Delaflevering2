/** @author {Mads Voss, Mikkel Bech, Dalia Pireh, Sali Azou, Beant Sandhu}*/
import data.EKGDTO;
import data.EKGListener;
import data.EKGObservable;
import java.util.LinkedList;
import java.util.List;

public class ProducerConsumer implements EKGObservable {
    Sensor sensor;
    LinkedList<EKGDTO> listGUI = new LinkedList<>();
    LinkedList<EKGDTO> listDB = new LinkedList<>();
    int capacity = 1000;
    private EKGListener listenerGUI;
    private EKGListener listenerDB;

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (listGUI.size() == capacity)
                    wait();
                List<EKGDTO> value = sensor.getData();
                //System.out.println("Producer produced-"+ value);
                for (EKGDTO i: value) {
                    listGUI.add(i);
                    listDB.add(i);
                    System.out.println("producer produce: "+ i);
                }
                notify();

            }
            Thread.sleep(1);
        }
    }
    public void consume() throws InterruptedException {
        while (true) {
            LinkedList<EKGDTO> consumeListGUI;
            synchronized (this) {
                while (listGUI.size() < 15)
                    wait();
                consumeListGUI = listGUI;
                if(listenerGUI != null) {
                    listenerGUI.notifyEKG(consumeListGUI);
                }

            }
        }
    }
    public void consume2() throws InterruptedException {
        while (true) {
            LinkedList<EKGDTO> consumeListDB;
            synchronized (this) {
                while (listDB.size() < 10)
                    wait();
                consumeListDB = listDB;
                if (listenerDB != null) {
                    listenerDB.notifyEKG(consumeListDB);
                    System.out.println("consumer consume: " + consumeListDB);
                }
            }
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
