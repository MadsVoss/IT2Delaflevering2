import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GUI extends Application {
    public static Stage stage;

    @Override
    public void start(final Stage stage) throws Exception {
        //Bruger FXMLLoader til at loade vores fxml fil Temperatur og viser det når programmet starter
        GUI.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Temperature.fxml"));
        AnchorPane anchorPane = loader.load();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
}