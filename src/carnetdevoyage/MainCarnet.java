package carnetdevoyage;

import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.presentation.AuteurCarnet;
import carnetdevoyage.vues.ControlleurMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainCarnet extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setResizable(false);
        Carnet carnet = new Carnet();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/page_accueil.fxml"));
        loader.setControllerFactory(ic -> {
            if (ic == ControlleurMenu.class) {
                return new ControlleurMenu(carnet);
            } else {
                try {
                    return ic.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Parent root = loader.load();
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