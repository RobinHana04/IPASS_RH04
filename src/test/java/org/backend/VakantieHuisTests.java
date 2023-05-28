package org.backend;

import org.backend.model.Vakantiehuis;
import org.backend.model.Verhuurder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class VakantieHuisTests {
    Verhuurder v1 = Verhuurder.getDeVerhuurder();
    Verhuurder v2 = Verhuurder.getDeVerhuurder();
    Verhuurder v3 = Verhuurder.getDeVerhuurder();
    Vakantiehuis vh1 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh2 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh3 = Vakantiehuis.getHetVakantiehuis();

    @BeforeEach
    public void setup() {
        v1 = new Verhuurder("RobinHana", "Hallo123");
        v2 = new Verhuurder("RobinHana", "Hallo123");
        v3 = new Verhuurder("TomKemper", "Test1234");

        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1);
        vh2 = new Vakantiehuis("Huis1", "A", "30m2", 1);
        vh3 = new Vakantiehuis("Huis2", "B", "40m2", 2);
    }

    @Test
    public void geenDubbeleVakantiehuizen() {
        Vakantiehuis.addHuis(vh1);
        Vakantiehuis.addHuis(vh2);
        assertEquals(1, Vakantiehuis.getAlleHuizen().size());
    }

    @Test
    public void EqualsMethodeVerhuurder() {
        assertEquals(v1, v2);
    }

    @Test
    public void EqualsMethodeVakantieHuis() {
        assertEquals(vh1, vh2);
    }

    @Test
    public void ReedsToegevoegdHuisBijIemandAndersToevoegen() {
        v1 = new Verhuurder("RobinHana", "Hallo123");
        v2 = new Verhuurder("RobinHana2", "Hallo123");
        v1.voegHuisToe(vh2);
        v2.voegHuisToe(vh2);
        assertEquals(1, Verhuurder.getAlleHuizen().size());
    }

    @Test
    public void DezelfdeVerhuurdersInLijst() {
        Verhuurder.voegVerhuurderToe(v1);
        Verhuurder.voegVerhuurderToe(v1);
        assertEquals(1, Verhuurder.getAlleVerhuurders().size());
    }

}
