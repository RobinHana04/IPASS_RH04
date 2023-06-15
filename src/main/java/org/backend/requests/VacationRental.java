package org.backend.requests;

import org.backend.domain.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class VacationRental implements Serializable {
    private static VacationRental deRental = new VacationRental();
    private List<Vakantiehuis> vakantiehuizenVR = new ArrayList<>();
    private List<Verhuurder> verhuurdersVR = new ArrayList<>();

    private static Predicate<NamedObject> hasName(String name) {
        return e -> e.getName().equals(name);
    }

    public void addVakantiehuizenVR(Vakantiehuis vakantiehuis) {
        getVakantiehuizenVR().add(vakantiehuis);
    }

    public static VacationRental getVacationRental() {
        return deRental;
    }

    public void addVerhuurderVR (Verhuurder verhuurder) {
        getVerhuurdersVR().add(verhuurder);
    }

    public List<Vakantiehuis> getVakantiehuizenVR() {
        return vakantiehuizenVR;
    }

    public List<Verhuurder> getVerhuurdersVR() {
        return verhuurdersVR;
    }

    public static void setVacationRental(VacationRental vacationRental) {
        deRental = vacationRental;
    }

    public List<Vakantiehuis> getAllVakantieHuizen() {
        return Vakantiehuis.getAlleHuizen();
    }

    public Vakantiehuis getVakantieHuisBijNaam(String name) {
        return Vakantiehuis.getAlleHuizen().stream().filter(hasName(name)).findFirst().orElse(null);
    }

    public Verhuurder getVerhuurderBijNaam(String name) {
        return Verhuurder.getAlleVerhuurders().stream().filter(hasName(name)).findFirst().orElse(null);
    }

    public List<Verhuurder> getAllVerhuurders() {
        return Verhuurder.getAlleVerhuurders();
    }

    public List<Huurder> getAllHuurders() {
        return Huurder.getAlleHuurders();
    }

    public List<Boeking> getAllBoekingen() {
        return Huurder.getAlleBoekingen();
    }
}
