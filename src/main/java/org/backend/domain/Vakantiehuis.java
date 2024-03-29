package org.backend.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Vakantiehuis implements NamedObject, Serializable {
    private String naam;
    private String adres;
    private String woonOppervlakte;
    private int status;
    private String image;
    private static ArrayList<Vakantiehuis> alleHuizen = new ArrayList<>();
    private static Vakantiehuis hetVakantiehuis;
    private Verhuurder verhuurder;

    public Vakantiehuis(String naam, String adres, String woonOppervlakte, int status, String image, Verhuurder verhuurder) {
        this.adres = adres;
        this.woonOppervlakte = woonOppervlakte;
        this.status = status;
        this.naam = naam;
        this.image = image;
        this.verhuurder = verhuurder;
    }

    public String getImage() {
        return image;
    }

    public Vakantiehuis() {
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getName() {
        return this.naam;
    }

    public static ArrayList<Vakantiehuis> getAlleHuizen() {
        return alleHuizen;
    }

    public static void addHuis(Vakantiehuis vh) {
        if (!getAlleHuizen().contains(vh)) {
            getAlleHuizen().add(vh);
        }
    }

    public static Vakantiehuis removeVakantiehuis(String naam) {
        Vakantiehuis selectedVakantiehuis = getVakantiehuisBijNaam(naam);
        if(selectedVakantiehuis == null) {
            return null;
        } else {
            getAlleHuizen().remove(selectedVakantiehuis);
        }
        return selectedVakantiehuis;
    }

    public static Vakantiehuis getVakantiehuisBijNaam(String naam){
        return getAlleHuizen().stream().filter(vakantiehuis -> vakantiehuis.getName().equals(naam)).findFirst().orElse(null);
    }

    public static void setAlleHuizen(ArrayList<Vakantiehuis> alleHuizen) {
        Vakantiehuis.alleHuizen = alleHuizen;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getWoonOppervlakte() {
        return woonOppervlakte;
    }

    public void setWoonOppervlakte(String woonOppervlakte) {
        this.woonOppervlakte = woonOppervlakte;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public static Vakantiehuis getHetVakantiehuis() {
        return hetVakantiehuis;
    }

    public static void setHetVakantiehuis(Vakantiehuis hetVakantiehuis) {
        Vakantiehuis.hetVakantiehuis = hetVakantiehuis;
    }

    public Verhuurder getVerhuurder() {
        return verhuurder;
    }

    public void setVerhuurder(Verhuurder verhuurder) {
        this.verhuurder = verhuurder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vakantiehuis that = (Vakantiehuis) o;
        return Objects.equals(adres, that.adres) && Objects.equals(woonOppervlakte, that.woonOppervlakte);
    }

    @Override
    public String toString() {
        return "Vakantiehuis{" +
                "naam='" + naam + '\'' +
                ", adres='" + adres + '\'' +
                ", woonOppervlakte='" + woonOppervlakte + '\'' +
                ", status=" + status +
                ", image='" + image + '\'' +
                '}';
    }
}
