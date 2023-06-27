package org.backend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Verhuurder implements NamedObject, Serializable {
    private String gebruikersnaam;
    private static ArrayList<Verhuurder> alleVerhuurders = new ArrayList<>();

    private ArrayList<Vakantiehuis> alleHuizen = new ArrayList<>();
    private static Verhuurder deVerhuurder;

    public Verhuurder(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
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

    public ArrayList<Vakantiehuis> getAlleHuizen() {
        return alleHuizen;
    }

    public void setAlleHuizen(ArrayList<Vakantiehuis> alleHuizen) {
        this.alleHuizen = alleHuizen;
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
        return Objects.equals(gebruikersnaam, that.gebruikersnaam);
    }

    @Override
    public String toString() {
        return "Verhuurder{" +
                "gebruikersnaam='" + gebruikersnaam + '\'' +
                '}';
    }

    @Override
    public String getName() {
        return this.gebruikersnaam;
    }
}
