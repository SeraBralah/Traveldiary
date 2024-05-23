import carnetdevoyage.carnet.presentation.PresentationCarnet;
import carnetdevoyage.exceptions.DateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentationCarnetTest {




    @Test
    void setDatedebutValide() throws DateException {
        PresentationCarnet carnet = new PresentationCarnet("test");
        carnet.setDatedebut("02/02/2024");
        assertSame(carnet.getDatedebut(),"02/02/2024");
    }

    @Test
    void setDatedebutNonValide() throws DateException {
        PresentationCarnet carnet = new PresentationCarnet("Test");
        assertThrows(DateException.class,()-> carnet.setDatedebut("30/02/2024"));
        assertThrows(DateException.class,()-> carnet.setDatedebut("29/02/2023"));
        assertThrows(DateException.class,()-> carnet.setDatedebut("29/15/2023"));
        assertThrows(DateException.class,()-> carnet.setDatedebut("58/15/2023"));
        assertThrows(DateException.class,()-> carnet.setDatedebut("17/02/2029"));
    }

    @Test
    void dateValide() throws DateException {
        PresentationCarnet carnet = new PresentationCarnet("Test");
        carnet.setDatedebut("02/04/2023");
        carnet.setDatefin("05/04/2023");
        assertTrue(carnet.dateValide(carnet.getDatedebut(),carnet.getDatefin()));
    }

    @Test
    void dateNonValide() throws DateException {
        PresentationCarnet carnet = new PresentationCarnet("Test");
        carnet.setDatedebut("10/04/2023");
        carnet.setDatefin("01/03/2023");
        assertFalse(carnet.dateValide(carnet.getDatedebut(),carnet.getDatefin()));
    }

    @Test
    void nbJour() throws DateException {
        PresentationCarnet carnet = new PresentationCarnet("Test");
        carnet.setDatedebut("02/04/2022");
        carnet.setDatefin("02/04/2023");
        assertEquals(carnet.nbJourDuVoyage(carnet.getDatedebut(),carnet.getDatefin()),365);
    }

}