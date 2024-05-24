import carnetdevoyage.carnet.Carnet;
import carnetdevoyage.carnet.pages.DescriptionDestination;
import carnetdevoyage.carnet.pages.ImageDestination;
import carnetdevoyage.carnet.pages.LocalisationDestination;
import carnetdevoyage.carnet.pages.PageDestination;
import carnetdevoyage.carnet.presentation.*;
import carnetdevoyage.exceptions.DateException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CarnetTest {



    /**
     * Carnet fonctionnel pour les tests
     */
    Carnet constructeurComplet() throws DateException {

        // PAGE 1 :
        DescriptionDestination descriptionDestination = new DescriptionDestination("La chambre");
        ImageDestination imageDestination = new ImageDestination();
        LocalisationDestination localisationDestination = new LocalisationDestination();
        localisationDestination.setLocalisation("Mon appartement");
        PageDestination pageDestination = new PageDestination();


        pageDestination.setDescriptionDestination(descriptionDestination);
        pageDestination.setImageDestination(imageDestination);
        pageDestination.setLocalisationDestination(localisationDestination);

        //PAGE 2:
        DescriptionDestination descriptionDestination2 = new DescriptionDestination("Les toilettes");
        ImageDestination imageDestination2 = new ImageDestination();
        LocalisationDestination localisationDestination2 = new LocalisationDestination();
        localisationDestination2.setLocalisation("Deuxième étage");
        PageDestination pageDestination2 = new PageDestination();


        pageDestination2.setDescriptionDestination(descriptionDestination2);
        pageDestination2.setImageDestination(imageDestination2);
        pageDestination2.setLocalisationDestination(localisationDestination2);

        //PAGE PRESENTATION :
        AuteurCarnet auteurCarnet = new AuteurCarnet("Moi");
        Participant p0 = new Participant("Toi");
        Participant p1 = new Participant("Eux");
        Participant p2 = new Participant("Nous");

        GestionnaireParticipants gestionnaireParticipants = new GestionnaireParticipants();
        gestionnaireParticipants.ajouterParticipants(p0, p1, p2);

        PresentationCarnet presentationCarnet = new PresentationCarnet("Mon carnet génial");
        presentationCarnet.setDatedebut("01/01/2024");
        presentationCarnet.setDatefin("09/05/2024");

        PagePresentation pagePresentation = new PagePresentation();
        pagePresentation.setPresentationCarnet(presentationCarnet);
        pagePresentation.setAuteur(auteurCarnet);
        pagePresentation.setGestionnaire(gestionnaireParticipants);

        Carnet carnet = new Carnet();
        carnet.ajouterPagePresentation(pagePresentation);
        carnet.ajouterPageDestination(pageDestination);
        carnet.ajouterPageDestination(pageDestination2);


        return carnet;

    }

    @Test
    void ajouterPagePresentation() {
    }

    @Test
    void ajouterPageDestination() {
    }

    @Test
    void nbPage() throws DateException {
        Carnet c = constructeurComplet();
        assertEquals(c.nbPage(),3);
    }

    @Test
    void iterator() {
    }

    @Test
    void avancerPage() throws DateException {
        Carnet c = constructeurComplet();
        c.avancerPage();
        assertEquals(c.getNumPageCourante(),1);



    }

    @Test
    void reculerPage() throws DateException {
        Carnet c = constructeurComplet();
        c.reculerPage();
        assertEquals(c.getNumPageCourante(),0);
        c.avancerPage();
        c.avancerPage();
        c.reculerPage();
        assertEquals(c.getNumPageCourante(),1);


    }
}