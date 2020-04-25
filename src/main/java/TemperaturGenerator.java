import data.TempListener;
import data.TempMeasure;
import data.TempObservable;
import javafx.scene.chart.XYChart;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

public class TemperaturGenerator implements Runnable, TempObservable {


    private TempListener listener;

    @Override
    public void run() {

        while(true) {
            double temp = getTemp();
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            TempMeasure tempMeasure = new TempMeasure();
            tempMeasure.setMeasurement(temp);
            tempMeasure.setTime(time);
            if(listener != null) {
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
        double max = 47;
        double min = 33;
        double temperatur = (Math.random() *((max-min) +1) + min);
        return temperatur;
    }

    @Override
    public void register(TempListener listener) {
        this.listener = listener;
    }
}

