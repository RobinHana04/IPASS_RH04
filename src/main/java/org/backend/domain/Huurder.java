package org.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Huurder implements NamedObject, Serializable {
    private String gebruikersnaam;
    private static ArrayList<Huurder> alleHuurders = new ArrayList<>();
    @JsonIgnore
    private ArrayList<Boeking> alleBoekingen = new ArrayList<>();
    private static Huurder deHuurder;

    public Huurder() {
    }

    public Huurder(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public static Huurder getHuurderBijNaam(String naam){
        return getAlleHuurders().stream().filter(huurder -> huurder.getName().equals(naam)).findFirst().orElse(null);
    }

    public static void addHuurder(Huurder h1) {
        if (!getAlleHuurders().contains(h1)) {
            getAlleHuurders().add(h1);
        }
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public void addBoeking(Boeking b) {
        if (!getAlleBoekingen().contains(b)) {
            getAlleBoekingen().add(b);
        }
    }

    public static ArrayList<Huurder> getAlleHuurders() {
        return alleHuurders;
    }

    public static void setAlleHuurders(ArrayList<Huurder> alleHuurders) {
        Huurder.alleHuurders = alleHuurders;
    }

    @JsonIgnore
    public ArrayList<Boeking> getAlleBoekingen() {
        return alleBoekingen;
    }

    public void setAlleBoekingen(ArrayList<Boeking> alleB) {
        alleBoekingen = alleB;
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
