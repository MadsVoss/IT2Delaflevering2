/** @author {Mads Voss, Mikkel Bech, Dalia Pireh, Sali Azou, Beant Sandhu}*/
import data.TempListener;
import data.TempMeasure;
import data.TemperatureDAO;
import data.TemperatureDAOSQLImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TemperatureController implements TempListener {

    public LineChart<String, Double> lineChart;
    public XYChart.Series<String, Double> stringDoubleData = new XYChart.Series<>();
    public TextField idField;
    private boolean record;
    private final TemperatureDAO temperatureDAO = new TemperatureDAOSQLImpl();

    final int WINDOW_SIZE = 20;
    private ScheduledExecutorService scheduledExecutorService;

    public void startTemperature(ActionEvent actionEvent) throws InterruptedException {
        TemperatureGenerator temperatureGenerator = new TemperatureGenerator();
        new Thread(temperatureGenerator).start();
        temperatureGenerator.register(this);
        lineChart.getData().add(stringDoubleData);
        lineChart.setCreateSymbols(false);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    }
    @Override
    public void notifyTemp(final TempMeasure temp) {
        if (this.record) {
            temp.setCpr(idField.getText());
            temperatureDAO.saveTemp(temp);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stringDoubleData.getData().add(new XYChart.Data<String, Double>(temp.getTime().toString(), temp.getMeasurement()));
                if (stringDoubleData.getData().size() > WINDOW_SIZE)
                    stringDoubleData.getData().remove(0);
            }
        });
    }

    public void loadDataPage(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LoadGUI.fxml"));
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            Stage loadStage = new Stage();
            loadStage.setScene(new Scene(anchorPane));
            loadStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startRecording(ActionEvent actionEvent) {
        this.record = !this.record;
    }
}





