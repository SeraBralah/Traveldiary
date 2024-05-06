package carnetdevoyage.carnet.pages;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe contenant les liens des images de la page
 */
public class ImageDestination {
    private HashMap<Integer,String> cheminsImages;
    private int id;

    /**
     * Constructeur de la classe des images de la destination
     */
    public ImageDestination(){
        this.cheminsImages = new HashMap<>();
        this.id = 0;
    }

    /**
     * Ajoute une image à la collection
     * @param chemin
     */
    public void ajouterUneImage(String chemin){
        //VOir si on rajoute des limites de nombre d'image :

        this.cheminsImages.put(id,chemin);
        this.id++;
    }

    /**
     * Récupère le chemin d'une image en fonction de son identifiant
     * @param id
     * @return
     */
    public String getChemin(int id){
        return this.cheminsImages.get(id);
    }
}
