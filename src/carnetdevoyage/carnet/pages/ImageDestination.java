package carnetdevoyage.carnet.pages;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe contenant les liens des images de la page
 */
public class ImageDestination implements Iterable<Image>{
    private Image[] tabImage;
    private int id;


    /**
     * Constructeur de la classe des images de la destination
     */
    public ImageDestination(){
        this.id = 0;
        this.tabImage = new Image[3];
    }



    public void ajouterImage(Image image){
        if(this.id!=3){
            this.tabImage[this.id] = image;
            this.id++;
        } else{
            this.id=0;
            this.tabImage[this.id] = image;
            this.id++;
        }
    }

    public void supprimerImage(int id){
        this.tabImage[id] = null;
        this.id--;
    }
    public Image getImage(int id){
        return this.tabImage[id];
    }




    @Override
    public String toString() {
        return "\n Image Destination " +
                tabImage +
                " \n";
    }

    @Override
    public Iterator<Image> iterator() {
        return Arrays.stream(tabImage).iterator();
    }
}
