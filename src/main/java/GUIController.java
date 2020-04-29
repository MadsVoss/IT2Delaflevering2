import com.sun.org.apache.xpath.internal.objects.XNumber;
import data.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class GUIController implements TempListener {
    public LineChart<String, Double> lineChart;
    public XYChart.Series<String, Double> stringDoubleData = new XYChart.Series<>();
    public TextField idField;


    private boolean record;
    private TemperaturDAO temperaturDAO = new TemperaturDAOSQLImpl();


    public void startTemperatur(ActionEvent actionEvent) throws InterruptedException {
        TemperaturGenerator temperaturGenerator = new TemperaturGenerator();
        new Thread(temperaturGenerator).start();
        temperaturGenerator.register(this);
        lineChart.getData().add(stringDoubleData);
        lineChart.setCreateSymbols(false);

    }

    @Override
    public void notifyTemp(final TempMeasure temp) {
        //Gem data
        if (this.record){
            temp.setCpr(idField.getText());
            temperaturDAO.save(temp);
        }
        //Vis data
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stringDoubleData.getData().add(new XYChart.Data<String, Double>(temp.getTime().toString(), temp.getMeasurement()));
            }
        });
    }

    public void startOptagning(ActionEvent actionEvent) {
        this.record = !this.record;

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
}


