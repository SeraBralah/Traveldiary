package carnet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainCarnet extends Application {
    @Override
    public void start(Stage stage) throws Exception {
    stage.setTitle("Test");
        BorderPane root = new BorderPane();
        stage.setScene(new Scene(root,900,900));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*
--module-path
/home/clara/Documents/S4/java/ressource/javafx-sdk-17.0.10/lib/
--add-modules
javafx.controls,javafx.fxml
 */