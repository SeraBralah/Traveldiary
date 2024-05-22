package carnetdevoyage.carnet;

import carnetdevoyage.carnet.pages.PageDestination;
import carnetdevoyage.carnet.presentation.PagePresentation;

import java.util.ArrayList;
import java.util.Iterator;

public class Carnet implements Iterable<Pages>{
    private PagePresentation pagePresentation;
    private ArrayList<Pages>  pages;
    private ArrayList<PageDestination> pageDestinations;
    private int pageCourante;
    private int nbPage;
    /**
     * Constructeur du carnet
     */
    public Carnet(){
        pages = new ArrayList<>();
        pageDestinations = new ArrayList<>();
        this.pageCourante=0;
        this.nbPage=0;
        this.pagePresentation = new PagePresentation();
        pages.add(pagePresentation);
        PageDestination pageDestination = new PageDestination();
        pages.add(pageDestination);
        pageDestinations.add(pageDestination);
    }

    /**
     * Crée la page de présentation du carnet
     * @param pagePresentation
     */
    public void ajouterPagePresentation(PagePresentation pagePresentation) {
        this.pagePresentation = pagePresentation;
        this.pages.add(pagePresentation);
        this.nbPage++;
    }

    /**
     * Crée la page de destination du carnet
     * @param pageDestination
     */
    public void ajouterPageDestination(PageDestination pageDestination) {
        this.pageDestinations.add(pageDestination);
        this.pages.add(pageDestination);
        this.nbPage++;
    }

    public void supprimerPageDestination(int numPage){
        this.pages.remove(numPage);
    }

    /**
     * Indique le nombre de page dans le carnet
     * @return
     */
    public int nbPage(){
        return this.pages.size();
    }

    /**
     * On se déplace d'une page
     */
    public void avancerPage(){
        if(this.nbPage>this.pageCourante) this.pageCourante+=1;
    }

    /**
     * On recule d'une page
     */
    public void reculerPage(){
        if(this.pageCourante!=0) this.pageCourante-=1;
    }

    @Override
    public Iterator<Pages> iterator() {
        return pages.iterator();
    }

    @Override
    public String toString() {
        return "Carnet :" + pages +
                ", pageCourante : " + pageCourante +
                ", nbPage : " + nbPage;
    }

    public Pages getPageCourante(){
        return pages.get(pageCourante);
    }

    public int getNumPageCourante() {
        return pageCourante;
    }
}
