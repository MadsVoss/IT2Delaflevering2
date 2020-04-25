import com.sun.org.apache.xpath.internal.objects.XNumber;
import data.TempListener;
import data.TempMeasure;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;

import java.text.DateFormat;
import java.util.Date;

public class GUIController implements TempListener {
    public LineChart<String, Double> lineChart;
    public XYChart.Series<String, Double> stringDoubleData = new XYChart.Series<>();



    public void startTemperatur(MouseEvent mouseEvent) throws InterruptedException {
        TemperaturGenerator temperaturGenerator = new TemperaturGenerator();
        new Thread(temperaturGenerator).start();
        temperaturGenerator.register(this);
        lineChart.getData().add(stringDoubleData);
        lineChart.setCreateSymbols(false);

    }

    @Override
    public void notifyTemp(final TempMeasure temp) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stringDoubleData.getData().add(new XYChart.Data<String, Double>(temp.getTime().toString(), temp.getMeasurement()));
            }
        });
    }
}


