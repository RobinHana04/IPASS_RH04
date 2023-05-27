package org.backend;

import java.util.Objects;

public class Vakantiehuis {
    private String naam;
    private String adres;
    private String woonOppervlakte;
    private static Vakantiehuis hetVakantiehuis;
    private int status;

    public Vakantiehuis(String adres, String woonOppervlakte, int status) {
        this.adres = adres;
        this.woonOppervlakte = woonOppervlakte;
        this.status = status;
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

    public String getNaam() {
        return naam;
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
                '}';
    }
}
