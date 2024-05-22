package carnetdevoyage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainCarnet extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/page_presentation.fxml"));
        primaryStage.setTitle("Application JavaFX Simple");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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