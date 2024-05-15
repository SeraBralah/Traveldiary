package carnetdevoyage.carnet.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe contenant les liens des images de la page
 */
public class ImageDestination implements Iterable<String>{
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
     * Supprime une image de la collection
     * @param id
     */
    public void supprimerUneImage(int id){
        this.cheminsImages.remove(id);
    }


    /**
     * Récupère le chemin d'une image en fonction de son identifiant
     * @param id
     * @return
     */
    public String getChemin(int id){
        return this.cheminsImages.get(id);
    }

    @Override
    public Iterator<String> iterator() {
        return cheminsImages.values().iterator();
    }
}
