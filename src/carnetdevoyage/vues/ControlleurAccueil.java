package carnetdevoyage.vues;

import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.presentation.AuteurCarnet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ControlleurAccueil {

    @FXML
    private Button newc;

    private Carnet carnet;

    @FXML
    void Quitter(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private Button loadc;

    @FXML
    private Button exitc;

    public void initialize() {
        this.carnet = new Carnet();
    }

    @FXML
    void ChargerCarnet(ActionEvent event) throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle("Chargement d'une sauvegarde");
        FileChooser choixfichier = new FileChooser();
        choixfichier.setTitle("Quel fichier charger ?");
        choixfichier.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("json", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        File selectedFile = choixfichier.showOpenDialog(dialog);
        if (selectedFile != null) {
            carnet.chargement(selectedFile);
System.out.println(carnet);
                if (carnet.getNbPage() != 0) {
                    afficherCarnet(carnet, event);

                }
        }
    }


    @FXML
    void NouveauCarnet(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/parametres_page_presentation.fxml"));
        loader.setControllerFactory(ic -> {
            if (ic == ControlleurCreationCarnet.class) {
                return new ControlleurCreationCarnet(carnet, new AuteurCarnet(""));
            } else {
                try {
                    return ic.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setOnHidden(windowEvent -> {
            if (carnet.getNbPage() != 0) {
                afficherCarnet(carnet, event);
            }
        });
        stage.show();
    }

    public void afficherCarnet(Carnet carnet, ActionEvent event) {
        try {
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/page_presentation.fxml"));
            menuLoader.setControllerFactory(ic -> {
                if (ic == ControlleurMenu.class) {
                    return new ControlleurMenu(carnet);
                } else if (ic == ControlleurPagePresentation.class) {
                    return new ControlleurPagePresentation(carnet);
                } else if (ic == ControlleurNavigation.class) {
                    return new ControlleurNavigation(carnet);
                } else {
                    try {
                        return ic.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Parent menuRoot = menuLoader.load();
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(menuRoot));
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


