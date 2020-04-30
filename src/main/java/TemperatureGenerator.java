import data.TempListener;
import data.TempMeasure;
import data.TempObservable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

public class TemperatureGenerator implements Runnable, TempObservable {

    private TempListener listener;
    private DateFormat klokkeFormat;

    @Override
    public void run() {

        while(true) {
            //klokkeFormat   = DateFormat.getTimeInstance(DateFormat.MEDIUM);
            //Date tid = new Date();
            //System.out.println(klokkeFormat.format(tid));

            //Henter temperaturen fra getTemp
            double temp = getTemp();
            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());
            TempMeasure tempMeasure = new TempMeasure();
            tempMeasure.setMeasurement(temp);
            tempMeasure.setTime(time);
            //tempMeasure.setTime(new Timestamp(System.currentTimeMillis()));


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
    //Simulere en temperatur vha. Math.random()
    public static double getTemp() {
        double max = 47;
        double min = 33;
        double temperature = (Math.random() *((max-min) +1) + min);
        return temperature;
    }

    @Override
    public void register(TempListener listener) {
        this.listener = listener;
    }
}

