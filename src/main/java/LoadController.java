import data.TempMeasure;
import data.TemperatureDAO;
import data.TemperatureDAOSQLImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.List;

public class LoadController {
    public TextField cprField;
    public TextArea temperatureDataArea;

    public void LoadData(ActionEvent actionEvent) {
        TemperatureDAO temperatureDAO = new TemperatureDAOSQLImpl();
        List<TempMeasure> temperatureData = temperatureDAO.load(cprField.getText());
        String text = "";
        for (TempMeasure data: temperatureData) {
            text += "CPR: " + data.getCpr() + ", Temperature: " + String.format("%.1f", data.getMeasurement()) + "°C, Time: " + data.getTime() + "\r\n";
        }
        temperatureDataArea.setText(text);
    }
}
