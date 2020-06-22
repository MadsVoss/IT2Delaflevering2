import data.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;
public class EKGLoadController {
    public TextField cprField;
    public TextArea temperatureDataArea;

    public void loadData(ActionEvent actionEvent) {
        EKGDAO ekgDAO = new EKGDAOSQLImpl();
        LinkedList<EKGDTO> ekgData = ekgDAO.loadEKG(cprField.getText());
        String text = "";
        for (EKGDTO data : ekgData) {
            text += "CPR: " + data.getCpr() + ", data: " + String.format("%.1f", data.getEkg()) + " Time: " + data.getTimestamp() + "\r\n";
        }
        temperatureDataArea.setText(text);
    }

}