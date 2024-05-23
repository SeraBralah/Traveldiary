package carnetdevoyage.carnet.presentation;

/**
 * Classe AuteurCarnet
 */
public class AuteurCarnet {
    private String auteur;
    private String infos;
    private String photoAuteur;

    /**
     * Constructeur de la classe AuteurCarnet, définit le nom de l'auteur
     * @param auteur
     */
    public AuteurCarnet(String auteur){
        this.auteur = auteur;
        this.infos = "informations manquantes";
        this.photoAuteur="";
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
     * Set la photo de l'auteur du carnet
     * @param photoAuteur
     */
    public void setPhotoAuteur(String photoAuteur) {
        this.photoAuteur = photoAuteur;
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
     *
     * @return la photo de l'auteur
     */
    public String getPhotoAuteur() {
        return photoAuteur;
    }

    @Override
    public String toString() {
        return "\n Auteur :" +
                  auteur +
                ", infos :'" + infos +
                ", photo :" + photoAuteur + '\n'
                ;
    }
}


