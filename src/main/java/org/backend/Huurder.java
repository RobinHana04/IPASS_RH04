package org.backend;

import java.util.ArrayList;
import java.util.Objects;

public class Huurder {
    private String gebruikersnaam;
    private String wachtwoord;
    private ArrayList<Boeking> alleBoekingen = new ArrayList<>();
    private static Huurder deHuurder;


    public Huurder(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public ArrayList<Boeking> getAlleBoekingen() {
        return alleBoekingen;
    }

    public void setAlleBoekingen(ArrayList<Boeking> alleBoekingen) {
        this.alleBoekingen = alleBoekingen;
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
        return Objects.equals(gebruikersnaam, huurder.gebruikersnaam) && Objects.equals(wachtwoord, huurder.wachtwoord);
    }

    @Override
    public String toString() {
        return "Huurder{" +
                "gebruikersnaam='" + gebruikersnaam + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                '}';
    }
}
