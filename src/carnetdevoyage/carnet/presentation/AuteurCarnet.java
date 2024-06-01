package carnetdevoyage.carnet.presentation;

import javafx.scene.image.Image;

/**
 * Classe AuteurCarnet
 */
public class AuteurCarnet {
    private String auteur;
    private String infos;
    private String photoAuteur;
    private String infossupp;

    /**
     * Constructeur de la classe AuteurCarnet, définit le nom de l'auteur
     * @param auteur
     */
    public AuteurCarnet(String auteur){
        this.auteur = auteur;
        this.infos = "informations manquantes";
        //qthis.photoAuteur="";
        this.infossupp=" ";
    }

    /**
     * Set le nom de l'auteur
     * @param auteur
     */
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    /**
     * Set les informations sur l'auteur du carnet
     * @param infos
     */
    public void setInfos(String infos) {
        this.infos = infos;
    }

    /**
     *
     * @return nom de l'auteur
     */
    public String getAuteur() {
        return auteur;
    }

    /**
     *
     * @return les informations de l'auteur
     */
    public String getInfos() {
        return infos;
    }

    /**
     * Set la photo de l'auteur du carnet
     * @param imageAuteur
     */
    public void setImageAuteur(String imageAuteur) {
        this.photoAuteur = imageAuteur;
    }

    public String getImageAuteur() {
        return photoAuteur;
    }

    public void setInfossupp(String infossupp) {
        this.infossupp = infossupp;
    }

    public String getInfossupp() {
        return infossupp;
    }

    @Override
    public String toString() {
        return "\n Auteur :" +
                  auteur +
                ", infos :'" + infos +
                ", photo :" + photoAuteur + "\n"
                ;
    }
}


