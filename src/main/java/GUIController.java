import data.*;
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

public class GUIController implements TempListener {
    public LineChart<String, Double> lineChart;
    public XYChart.Series<String, Double> stringDoubleData = new XYChart.Series<>();
    public TextField idField;


    private boolean record;
    private TemperatureDAO temperatureDAO = new TemperatureDAOSQLImpl();


    public void startTemperature(ActionEvent actionEvent) throws InterruptedException {
        TemperatureGenerator temperatureGenerator = new TemperatureGenerator();
        new Thread(temperatureGenerator).start();
        temperatureGenerator.register(this);
        lineChart.getData().add(stringDoubleData);
        lineChart.setCreateSymbols(false);

    }

    @Override
    public void notifyTemp(final TempMeasure temp) {
        //Gem data
        if (this.record){
            temp.setCpr(idField.getText());
            temperatureDAO.save(temp);
        }
        //Vis data
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stringDoubleData.getData().add(new XYChart.Data<String, Double>(temp.getTime().toString(), temp.getMeasurement()));
            }
        });
    }


    public void hentData(ActionEvent actionEvent) {
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


