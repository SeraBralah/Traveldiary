package carnetdevoyage.vues;

import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.presentation.AuteurCarnet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControlleurAccueil {

        @FXML
        private Button newc;

        @FXML
        private Button loadc;

        @FXML
        private Button exitc;

        @FXML
        void ChargerCarnet(ActionEvent event) {

        }
        @FXML
        void NouveauCarnet(ActionEvent event) throws IOException {
                Carnet carnet = new Carnet();
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
                stage.show();

        }

        @FXML
        void Quitter(ActionEvent event) {

        }

    }


