package carnetdevoyage.carnet.pages;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
public class ImageDestination {
    private String[] tabChemin;
    private int id;

    /**
     * Constructeur de la classe Image Destination
     */
    public ImageDestination() {
        this.id = 0;
        this.tabChemin = new String[3];
    }

    public void ajouterImage(String chemin) {
        Image image = new Image(chemin);
        if (this.id != 3) {
            this.tabChemin[this.id] = chemin;
            this.id++;
        } else {
            this.id = 0;
            this.tabChemin[this.id] = chemin;
            this.id++;
        }
    }

    public void supprimerImage(int id) {
        this.tabChemin[id] = null;
        this.id--;
    }

    public String getImage(int id) {
        return this.tabChemin[id];
    }

    public String[] getTabChemin() {
        return tabChemin;
    }

    public String[] getTabImage() {
        return tabChemin;
    }

    @Override
    public String toString() {
        return "\n Image Destination " +
                Arrays.toString(tabChemin) +
                " \n";
    }

}
