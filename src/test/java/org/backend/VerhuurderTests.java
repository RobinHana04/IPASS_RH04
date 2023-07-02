package org.backend;

import org.backend.domain.Vakantiehuis;
import org.backend.domain.Verhuurder;
import org.backend.requests.VacationRental;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerhuurderTests {
    Verhuurder v1 = Verhuurder.getDeVerhuurder();
    Verhuurder v2 = Verhuurder.getDeVerhuurder();
    Verhuurder v3 = Verhuurder.getDeVerhuurder();
    Vakantiehuis vh1 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh2 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh3 = Vakantiehuis.getHetVakantiehuis();
    VacationRental vr = new VacationRental();


    @BeforeEach
    public void setup() {
        v1 = new Verhuurder("RobinHana");
        v2 = new Verhuurder("RobinHana");
        v3 = new Verhuurder("TomKemper");

        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);
        vh2 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v2);
        vh3 = new Vakantiehuis("Huis2", "B", "40m2", 2, "", v3);
    }

    @Test
    public void EqualsMethodeVerhuurder() {
        assertEquals(v1, v2);
    }


    @Test
    public void DezelfdeVerhuurdersInLijst() {
        Verhuurder.addVerhuurder(v1);
        Verhuurder.addVerhuurder(v1);
        assertEquals(1, Verhuurder.getAlleVerhuurders().size());
    }

    @Test
    public void VerhuurdersVRLijst() {
        assertEquals(0, vr.getVerhuurdersVR().size());
    }

    @Test
    public void VerhuurdersToevoegenAanVRLijst() {
        vr.addVerhuurderVR(v1);
        assertEquals(1, vr.getVerhuurdersVR().size());
    }

    @Test
    public void GeenDubbeleVerhuurderToevoegenAanVRLijst() {
        vr.addVerhuurderVR(v1);
        vr.addVerhuurderVR(v1);
        assertEquals(1, vr.getVerhuurdersVR().size());
    }

    @Test
    public void VerhuurderVerwijderenVanLijst() {
        vr.getVerhuurdersVR().remove(v1);
        assertEquals(0, vr.getVerhuurdersVR().size());
    }

    @Test
    public void toStringVerhuurder() {
        v1 = new Verhuurder("RobinHana");
        assertEquals(v1.toString(), "Verhuurder{gebruikersnaam='RobinHana'}");
    }

    @Test
    public void EmptyConstructor() {
        Verhuurder v3 = new Verhuurder();
        assertEquals(v3.toString(), "Verhuurder{gebruikersnaam='null'}");
    }

    @Test
    public void getGebruikersnaam() {
        Verhuurder v4 = new Verhuurder("Robin");
        assertEquals(v4.getGebruikersnaam(), "Robin");
    }

    @Test
    public void setGebruikersnaam() {
        Verhuurder v4 = new Verhuurder("Robin");
        v4.setGebruikersnaam("Henk");
        assertEquals(v4.getGebruikersnaam(), "Henk");
    }


}
