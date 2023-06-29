package org.backend;

import org.backend.domain.Boeking;
import org.backend.domain.Huurder;
import org.backend.domain.Vakantiehuis;
import org.backend.domain.Verhuurder;
import org.backend.requests.VacationRental;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoekingTests {
    Boeking b1 = Boeking.getDeBoeking();
    Boeking b2 = Boeking.getDeBoeking();
    Boeking b3 = Boeking.getDeBoeking();
    Boeking b4 = Boeking.getDeBoeking();
    VacationRental vr = new VacationRental();
    Verhuurder v1 = Verhuurder.getDeVerhuurder();
    Vakantiehuis vh1 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh2 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh3 = Vakantiehuis.getHetVakantiehuis();
    Huurder h1 = Huurder.getDeHuurder();

    @BeforeEach
    public void setup() {
        LocalDate day = LocalDate.of(2024, 12, 5);
        LocalDate dayAfter = LocalDate.of(2024, 12, 6);
        h1 = new Huurder("Jan");
        v1 = new Verhuurder("Robin");  // Initialize the Verhuurder object first
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);  // Then create the Vakantiehuis object
        vh2 = new Vakantiehuis("Huis1", "A", "30m2", 1, "", v1);
        vh3 = new Vakantiehuis("Huis2", "B", "40m2", 2, "", v1);
        b1 = new Boeking(h1, vh1, day, dayAfter);
        b2 = new Boeking(h1, vh1, day, dayAfter);
    }

    @Test
    public void toStringBoeking() {
        LocalDate day = LocalDate.of(2024, 12, 5);
        LocalDate dayAfter = LocalDate.of(2024, 12, 6);
        b1 = new Boeking(h1, vh1, day, dayAfter);
        assertEquals(b1.toString(), "Boeking{huurder=null, datumVan=2024-12-05, datumTot=2024-12-06}");
    }

    @Test
    public void equalsBoeking() {
        LocalDate day = LocalDate.of(2024, 12, 5);
        LocalDate dayAfter = LocalDate.of(2024, 12, 6);
        b1 = new Boeking(h1, vh1, day, dayAfter);
        b2 = new Boeking(h1, vh1, day, dayAfter);
        assertEquals(b1, b2);
    }

    @Test
    public void BoekingenToevoegen() {
        LocalDate day = LocalDate.of(2024, 12, 5);
        LocalDate dayAfter = LocalDate.of(2024, 12, 6);
        b1 = new Boeking(h1, vh1, day, dayAfter);
        Boeking.addBoeking(b1);
        assertEquals(1, Boeking.getAlleBoekingen().size());
    }

    @Test
    public void DubbeleBoekingToevoegen() {
        LocalDate day = LocalDate.of(2024, 12, 5);
        LocalDate dayAfter = LocalDate.of(2024, 12, 6);
        b1 = new Boeking(h1, vh1, day, dayAfter);
        b2 = new Boeking(h1, vh1, day, dayAfter);
        Boeking.addBoeking(b1);
        Boeking.addBoeking(b2);
        assertEquals(1, Boeking.getAlleBoekingen().size());
    }

    @Test
    public void BoekingVerwijderen() {
        Boeking.getAlleBoekingen().remove(b1);
        assertEquals(1, Boeking.getAlleBoekingen().size());
    }

    @Test
    public void getAlleBoekingenVR() {
        assertEquals(0, vr.getBoekingenVR().size());
    }

    @Test
    public void BoekingVRToevoegen() {
        LocalDate day = LocalDate.of(2024, 12, 5);
        LocalDate dayAfter = LocalDate.of(2024, 12, 6);
        b1 = new Boeking(h1, vh1, day, dayAfter);
        vr.addBoekingenVR(b1);
        assertEquals(1, vr.getBoekingenVR().size());
    }

    @Test
    public void DubbeleBoekingVR() {
        LocalDate day = LocalDate.of(2024, 12, 5);
        LocalDate dayAfter = LocalDate.of(2024, 12, 6);
        b1 = new Boeking(h1, vh1, day, dayAfter);
        vr.addBoekingenVR(b1);
        b2 = new Boeking(h1, vh1, day, dayAfter);
        vr.addBoekingenVR(b2);
        assertEquals(1, vr.getBoekingenVR().size());
    }

    @Test
    public void BoekingVerwijderenVR() {
        vr.getBoekingenVR().remove(b1);
        assertEquals(0, vr.getBoekingenVR().size());
    }

    @Test
    public void getAlleBoekingen() {
        assertEquals(1, Boeking.getAlleBoekingen().size());
    }
}
