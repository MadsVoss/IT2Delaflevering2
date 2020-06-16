/** @author {Mads Voss, Mikkel Bech, Dalia Pireh, Sali Azou, Beant Sandhu}*/
import data.TempListener;
import data.TempMeasure;
import data.TempObservable;
import java.sql.Timestamp;
import java.util.Date;

public class TemperatureGenerator implements Runnable, TempObservable {

    private TempListener listener;

    @Override
    public void run() {

        while (true) {
            double temp = getTemp();
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            TempMeasure tempMeasure = new TempMeasure();
            tempMeasure.setMeasurement(temp);
            tempMeasure.setTime(time);
            if (listener != null) {
                listener.notifyTemp(tempMeasure);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static double getTemp() {
        double max = 44;
        double min = 33;
        double temperature = (Math.random() * ((max - min) + 1) + min);
        return temperature;
    }
    @Override
    public void register(TempListener listener) {
        this.listener = listener;
    }
}

