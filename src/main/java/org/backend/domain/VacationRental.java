package org.backend.domain;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

public class VacationRental implements Serializable {
    private static VacationRental deRental = new VacationRental();
    private static Predicate<NamedObject> hasName(String name) {
        return e -> e.getName().equals(name);
    }
    public static VacationRental getVacationRental() {
        return deRental;
    }

    public static void setVacationRental(VacationRental nVr) {
        deRental = nVr;
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
