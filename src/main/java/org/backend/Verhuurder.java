package org.backend;

import java.util.ArrayList;
import java.util.Objects;

public class Verhuurder {
    private String gebruikersnaam;
    private String wachtwoord;
    private ArrayList<Vakantiehuis> alleHuizen = new ArrayList<>();
    private static Verhuurder deVerhuurder;

    public Verhuurder(String gebruikersnaam, String wachtwoord) {
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

    public ArrayList<Vakantiehuis> getAlleHuizen() {
        return alleHuizen;
    }

    public void setAlleHuizen(ArrayList<Vakantiehuis> alleHuizen) {
        this.alleHuizen = alleHuizen;
    }

    public void voegHuisToe(Vakantiehuis vh) {
        getAlleHuizen().add(vh);
    }

    public static Verhuurder getDeVerhuurder() {
        return deVerhuurder;
    }

    public static void setDeVerhuurder(Verhuurder deVerhuurder) {
        Verhuurder.deVerhuurder = deVerhuurder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Verhuurder that = (Verhuurder) o;
        return Objects.equals(gebruikersnaam, that.gebruikersnaam) && Objects.equals(wachtwoord, that.wachtwoord);
    }

    @Override
    public String toString() {
        return "Verhuurder{" +
                "gebruikersnaam='" + gebruikersnaam + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                '}';
    }
}
