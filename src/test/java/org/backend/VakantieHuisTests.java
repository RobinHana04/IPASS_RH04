package org.backend;

import org.backend.domain.Vakantiehuis;
import org.backend.domain.Verhuurder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class VakantieHuisTests {
    Vakantiehuis vh1 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh2 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh3 = Vakantiehuis.getHetVakantiehuis();
    Verhuurder v1 = Verhuurder.getDeVerhuurder();

    @BeforeEach
    public void setup() {
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "");
        vh2 = new Vakantiehuis("Huis1", "A", "30m2", 1, "");
        vh3 = new Vakantiehuis("Huis2", "B", "40m2", 2, "");
        v1 = new Verhuurder("Robin");
    }

    @Test
    public void geenDubbeleVakantiehuizen() {
        Vakantiehuis.addHuis(vh1);
        Vakantiehuis.addHuis(vh2);
        assertEquals(1, Vakantiehuis.getAlleHuizen().size());
    }

    @Test
    public void EqualsMethodeVakantieHuis() {
        assertEquals(vh1, vh2);
    }

    @Test
    public void verschillendeHuizenToevoegen() {
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "");
        vh3 = new Vakantiehuis("Huis2", "B", "40m2", 2, "");
        Vakantiehuis.addHuis(vh1);
        Vakantiehuis.addHuis(vh3);
        assertEquals(3, Vakantiehuis.getAlleHuizen().size()); //Eerder huis is al toegevoegd
    }

    @Test
    public void correcteToString() {
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "");
        assertEquals("Vakantiehuis{naam='Huis1', adres='A', woonOppervlakte='30m2', status='1'}", vh1.toString());
    }

}
