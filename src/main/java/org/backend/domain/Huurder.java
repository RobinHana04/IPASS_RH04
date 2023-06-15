package org.backend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Huurder implements NamedObject, Serializable {
    private String gebruikersnaam;
    private static ArrayList<Huurder> alleHuurders = new ArrayList<>();

    private static ArrayList<Boeking> alleBoekingen = new ArrayList<>();
    private static Huurder deHuurder;


    public Huurder(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }


    public static ArrayList<Huurder> getAlleHuurders() {
        return alleHuurders;
    }

    public static void setAlleHuurders(ArrayList<Huurder> alleHuurders) {
        Huurder.alleHuurders = alleHuurders;
    }

    public static ArrayList<Boeking> getAlleBoekingen() {
        return alleBoekingen;
    }

    public static void setAlleBoekingen(ArrayList<Boeking> alleBoekingen) {
        Huurder.alleBoekingen = alleBoekingen;
    }

    public void boekingToevoegen(Boeking b) {
        getAlleBoekingen().add(b);
    }

    public static Huurder getDeHuurder() {
        return deHuurder;
    }

    public static void setDeHuurder(Huurder deHuurder) {
        Huurder.deHuurder = deHuurder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Huurder huurder = (Huurder) o;
        return Objects.equals(gebruikersnaam, huurder.gebruikersnaam);
    }

    @Override
    public String toString() {
        return "Huurder{" +
                "gebruikersnaam='" + gebruikersnaam + '\'' +
                '}';
    }

    @Override
    public String getName() {
        return this.gebruikersnaam;
    }
}
