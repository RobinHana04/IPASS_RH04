package org.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Verhuurder implements NamedObject, Serializable {
    private String gebruikersnaam;
    private String wachtwoord;
    private static ArrayList<Verhuurder> alleVerhuurders = new ArrayList<>();
    private static ArrayList<Vakantiehuis> alleHuizen = new ArrayList<>();
    private static Verhuurder deVerhuurder;

    public Verhuurder(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public static ArrayList<Verhuurder> getAlleVerhuurders() {
        return alleVerhuurders;
    }

    public static void voegVerhuurderToe(Verhuurder nv) {
        if (!getAlleVerhuurders().contains(nv)) {
            getAlleVerhuurders().add(nv);
        }
    }

    public static void setAlleVerhuurders(ArrayList<Verhuurder> alleVerhuurders) {
        Verhuurder.alleVerhuurders = alleVerhuurders;
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

    public static ArrayList<Vakantiehuis> getAlleHuizen() {
        return alleHuizen;
    }

    public static void setAlleHuizen(ArrayList<Vakantiehuis> alleHuizen) {
        Verhuurder.alleHuizen = alleHuizen;
    }

    public void voegHuisToe(Vakantiehuis vh) {
        if(!getAlleHuizen().contains(vh)) {
            getAlleHuizen().add(vh);
        }
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

    @Override
    public String getName() {
        return this.gebruikersnaam;
    }
}
