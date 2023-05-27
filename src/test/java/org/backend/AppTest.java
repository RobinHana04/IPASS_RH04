package org.backend;


import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.Assert.assertEquals;

public class AppTest {
    private Huurder huurder1 = Huurder.getDeHuurder();
    private Huurder huurder2 = Huurder.getDeHuurder();
    private Verhuurder verhuurder1 = Verhuurder.getDeVerhuurder();
    private Verhuurder verhuurder2 = Verhuurder.getDeVerhuurder();
    private Boeking boeking1 = Boeking.getDeBoeking();
    private Boeking boeking2 = Boeking.getDeBoeking();
    private Vakantiehuis v1 = Vakantiehuis.getHetVakantiehuis();
    private Vakantiehuis v2 = Vakantiehuis.getHetVakantiehuis();


    @BeforeEach
    public void init() {
        huurder1 = new Huurder("RobinHana", "Hoi123");
        huurder2 = new Huurder("Mark", "HU1234");

        verhuurder1 = new Verhuurder("Joe", "Hm12345");
        verhuurder2 = new Verhuurder("Tom", "hallo1234");

        v1 = new Vakantiehuis("Bolognalaan 15", "300m3", 1);
        v2 = new Vakantiehuis("Heidelberglaan 17", "250m3", 1);

        boeking1 = new Boeking(huurder1, v2, 5000.0, 1, "22-05-2023", "23-05-2023");
        boeking2 = new Boeking(huurder2, v1, 6000.0, 2, "22-06-2023", "23-06-2023");

        verhuurder1.voegHuisToe(v1);
        verhuurder2.voegHuisToe(v2);

        huurder1.boekingToevoegen(boeking1);
        huurder2.boekingToevoegen(boeking2);
    }

    @Test
    public void LijstHuizenVerhuurder() {
        init();
        assertEquals(1, verhuurder1.getAlleHuizen().size(), 0);
    }

    @Test
    public void LijstBoekingenHuurder() {
        init();
        assertEquals(1, huurder1.getAlleBoekingen().size(), 0);
    }

    @Test
    public void LijstMagNietZelfdeBoekingHebben() {
        init();
        huurder1.boekingToevoegen(boeking1);
        assertEquals(1, huurder1.getAlleBoekingen().size());
    }

    @Test
    public void AlsDatumGehaaldIsHaalBoekingWeg() {
        init();
    }

    @Test
    public void DatumAankomstMagNietLaterDanVertrek() {
        init();
    }

    @Test
    public void LijstMagNooitDezelfdeVakantieHuizenHebben() {
        init();
    }

    @Test
    public void AlsDatumGehaaldIsVanBoekingHaalVakantieHuisWeg() {
        init();
    }

    @Test
    public void JeHebtNooitDeExactZelfdeHuurders() {
        init();
    }

    @Test
    public void JeHebtNooitDeExactZelfdeVerhuurders() {
        init();
    }

}
