import data.EKGDAO;
import data.EKGDAOSQLImpl;
import data.EKGDTO;
import data.EKGListener;
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
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class EKGController implements EKGListener {
    public LineChart<String, Double> lineChart;
    public XYChart.Series<String, Double> stringDoubleData = new XYChart.Series<>();
    public TextField idField;
    private boolean record;
    private final EKGDAO ekgDAO = new EKGDAOSQLImpl();

    final int WINDOW_SIZE = 20;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void notifyEKG(LinkedList<EKGDTO> ekgData) {
        if (this.record) {
            for (int i = 0; i <ekgData.size() ; i++) {
                ekgData.get(i).setCpr(idField.getText());
            }
            ekgDAO.saveEkg(ekgData);

        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < ekgData.size(); i++) {
                    stringDoubleData.getData().add(new XYChart.Data<String, Double>(ekgData.getTimestamp().toString(), ekgData.getEkg()));
                }

                if (stringDoubleData.getData().size() > WINDOW_SIZE)
                    stringDoubleData.getData().remove(0);
            }
        });
    }

    public void startEKG(ActionEvent actionEvent) throws InterruptedException{
        ProducerConsumer pc = new ProducerConsumer();
        new Thread(pc).start();//SEPARAT Thread klasse
        pc.register(this);
        lineChart.getData().add(stringDoubleData);
        lineChart.setCreateSymbols(false);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRecordingEKG(ActionEvent actionEvent) {
        this.record = !this.record;
    }

    public void loadEKGPage(ActionEvent actionEvent) {
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
