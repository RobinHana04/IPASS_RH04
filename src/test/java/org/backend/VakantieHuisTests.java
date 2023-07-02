package org.backend;

import org.backend.domain.Vakantiehuis;
import org.backend.domain.Verhuurder;
import org.backend.requests.VacationRental;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VakantieHuisTests {
    Vakantiehuis vh1 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh2 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh3 = Vakantiehuis.getHetVakantiehuis();
    Verhuurder v1 = Verhuurder.getDeVerhuurder();
    VacationRental vr = new VacationRental();

    @BeforeEach
    public void setup() {
        v1 = new Verhuurder("Robin");  // Initialize the Verhuurder object first
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);  // Then create the Vakantiehuis object
        vh2 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);
        vh3 = new Vakantiehuis("Huis2", "B", "40m2", 2, "", v1);
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
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);
        vh3 = new Vakantiehuis("Huis2", "B", "40m2", 2, "", v1);
        Vakantiehuis.addHuis(vh1);
        Vakantiehuis.addHuis(vh3);
        assertEquals(3, Vakantiehuis.getAlleHuizen().size()); //Eerder huis is al toegevoegd
    }

    @Test
    public void correcteToString() {
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 100, "", v1);
        assertEquals("Vakantiehuis{naam='Huis1', adres='A', woonOppervlakte='30m2', status=100, image=''}", vh1.toString());
    }

    @Test
    public void VakantiehuizenVR() {
        assertEquals(0, vr.getVakantiehuizenVR().size());
    }

    @Test
    public void geenDubbeleVakantieHuizenInVR() {
        vr.addVakantiehuizenVR(vh1);
        vr.addVakantiehuizenVR(vh1);
        assertEquals(1, vr.getVakantiehuizenVR().size());
    }

    @Test
    public void emptyConstructor() {
        Vakantiehuis vh3 = new Vakantiehuis();
        assertEquals(vh3.toString(), "Vakantiehuis{naam='null', adres='null', woonOppervlakte='null', status=0, image='null'}");
    }

    @Test
    public void getImage() {
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);
        assertEquals(vh1.getImage(), "");
    }

    @Test
    public void getName() {
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);
        assertEquals(vh1.getName(), "Huis1");
    }

    @Test
    public void getAdres() {
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);
        assertEquals(vh1.getAdres(), "A");
    }

    @Test
    public void getWoonOppervlakte() {
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);
        assertEquals(vh1.getWoonOppervlakte(), "30m2");
    }

    @Test
    public void getStatus() {
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);
        assertEquals(vh1.getStatus(), 1);
    }

    @Test
    public void getVerhuurderObject() {
        Vakantiehuis vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);
        assertEquals(vh1.getVerhuurder(), v1);

    }
}
