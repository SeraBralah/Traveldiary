package carnetdevoyage.carnet;

import carnetdevoyage.carnet.pages.ImageDestination;
import carnetdevoyage.carnet.pages.PageDestination;
import carnetdevoyage.carnet.presentation.PagePresentation;
import carnetdevoyage.vues.PageAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Carnet extends SujetObserve implements Iterable<Pages> {
    private PagePresentation pagePresentation;
    @Expose
    private ArrayList<Pages> pages;
    @Expose
    private int pageCourante;
    @Expose
    private int nbPage;

    /**
     * Constructeur du carnet
     */
    public Carnet() {
        pages = new ArrayList<>();
        this.pageCourante = 0;
        this.nbPage = 0;
    }

    /**
     * Crée la page de présentation du carnet
     *
     * @param pagePresentation
     */
    public void ajouterPagePresentation(PagePresentation pagePresentation) {
        this.pagePresentation = pagePresentation;
        this.pages.add(pagePresentation);
        this.nbPage++;
        this.notifierObservateurs();
    }

    /**
     * Crée la page de destination du carnet
     *
     * @param pageDestination
     */
    public void ajouterPageDestination(PageDestination pageDestination) {
        pageDestination.setImageDestination(new ImageDestination());
        this.pages.add(pageDestination);
        this.nbPage++;
        this.notifierObservateurs();
    }

    public void supprimerPageDestination(int numPage) {
        if (this.getPageCourante().estDestination()) {
            if (this.nbPage != 2) {
                this.pages.remove(numPage);
                this.nbPage--;
                this.pageCourante--;
            }
        }
    }


    /**
     * On se déplace d'une page
     */
    public void avancerPage() {
        if (this.nbPage > this.pageCourante) {
            this.pageCourante += 1;
        }
    }

    /**
     * On recule d'une page
     */
    public void reculerPage() {
        if (this.pageCourante != 0) this.pageCourante -= 1;
    }


    @Override
    public Iterator<Pages> iterator() {
        return pages.iterator();
    }

    public int getNbPage() {
        return nbPage;
    }

    @Override
    public String toString() {
        return "Carnet :" + pages +
                ", pageCourante : " + pageCourante +
                ", nbPage : " + nbPage;
    }

    public Pages getPageCourante() {
        return pages.get(pageCourante);
    }

    public int getNumPageCourante() {
        return pageCourante;
    }

    public PagePresentation getPagePresentation() {
        return pagePresentation;
    }

    public void sauvegarde(File emplacement) {
        Gson sauvegarde = new Gson();
        try {
            FileWriter fileWriter = new FileWriter(emplacement);

            // Sauvegarde des autres pages
            for (Pages p : this.pages) {
                JsonObject jsonPage = new JsonObject();
                if (p.estPresentation()) {
                    jsonPage.addProperty("type", "presentation");
                } else if (p.estDestination()) {
                    jsonPage.addProperty("type", "destination");
                }
                jsonPage.add("data", sauvegarde.toJsonTree(p));
                fileWriter.write(sauvegarde.toJson(jsonPage));
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Problème lors de la sauvegarde");
            alert.showAndWait();
        }
    }

    public void chargement(File emplacement) {
        FileReader flot;
        try {
            pages = new ArrayList<>();
            pagePresentation = null;
            pageCourante = 0;
            nbPage = 0;
            flot = new FileReader(emplacement);
            Gson chargement = new GsonBuilder()
                    .registerTypeAdapter(Pages.class, new PageAdapter())
                    .create();
            BufferedReader flotFiltre = new BufferedReader(flot);
            String ligne;
            while ((ligne = flotFiltre.readLine()) != null) {
                JsonObject jsonPage = chargement.fromJson(ligne, JsonObject.class);
                String type = jsonPage.get("type").getAsString();
                JsonElement jsonData = jsonPage.get("data");
                if (type.equals("presentation")) {
                    ajouterPagePresentation(chargement.fromJson(jsonData, PagePresentation.class));
                } else if (type.equals("destination")) {
                    ajouterPageDestination(chargement.fromJson(jsonData, PageDestination.class));
                }
            }
            this.nbPage=this.pages.size();
            notifierObservateurs();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Problème lors de la sauvegarde");
            alert.showAndWait();        }
    }
}
