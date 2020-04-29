import data.TempMeasure;
import data.TemperaturDAO;
import data.TemperaturDAOSQLImpl;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.List;

public class LoadController {
    public TextField cprField;
    public TextArea temperaturDataArea;

    public void HentData(ActionEvent actionEvent) {
        TemperaturDAO temperaturDAO = new TemperaturDAOSQLImpl();
        List<TempMeasure> temperaturData = temperaturDAO.load(cprField.getText());
        String text = "";
        for (TempMeasure data: temperaturData) {
            text += "CPR: " + data.getCpr() + ", Temperatur: " + data.getMeasurement() + ", Time: " + data.getTime() + "\r\n";
        }
        temperaturDataArea.setText(text);
    }
}

