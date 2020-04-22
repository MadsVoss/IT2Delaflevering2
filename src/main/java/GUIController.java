import com.sun.org.apache.xpath.internal.objects.XNumber;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

import java.util.Date;

public class GUIController {

    public LineChart lineChart;

    public void startTemperatur(MouseEvent mouseEvent) throws InterruptedException {
        /*
            System.out.println("Temperaturen er: " + String.format("%.1f", getTemp()));
            Thread.sleep(1000);


         */

        int date = 1;

        for (int i = 0; i < 10 ; i++) {

            String nyDate = Integer.toString(date);
            //lineChart.getData().clear();
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
            series.getData().add(new XYChart.Data<String, Number>(nyDate, getTemp()));
            lineChart.getData().add(series);
            date ++;
            Thread.sleep(1000);

        }

        }



    public static double getTemp() {
        double max = 47;
        double min = 33;
        double temperatur = (Math.random() *((max-min) +1) + min);
        return temperatur;
    }
}

