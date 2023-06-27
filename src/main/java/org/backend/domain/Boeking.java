package org.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Boeking implements Serializable {
    @JsonIgnore
    private Huurder huurder;
    private LocalDate datumVan;
    private LocalDate datumTot;
    private Vakantiehuis vakantiehuis;
    private static Boeking deBoeking;
    private static ArrayList<Boeking> alleBoekingen = new ArrayList<>();

    public Boeking(Huurder huurder, Vakantiehuis vakantiehuis, LocalDate datumVan, LocalDate datumTot) {
        this.huurder = huurder;
        this.datumVan = datumVan;
        this.datumTot = datumTot;
        this.vakantiehuis = vakantiehuis;
    }

    public Huurder getHuurder() {
        return huurder;
    }

    public void setHuurder(Huurder huurder) {
        this.huurder = huurder;
    }

    public LocalDate getDatumVan() {
        return datumVan;
    }

    public void setDatumVan(LocalDate datumVan) {
        this.datumVan = datumVan;
    }

    public LocalDate getDatumTot() {
        return datumTot;
    }

    public static void addBoeking(Boeking b) {
        if (!getAlleBoekingen().contains(b)) {
            getAlleBoekingen().add(b);
        }
    }

    public void setDatumTot(LocalDate datumTot) {
        this.datumTot = datumTot;
    }

    public static Boeking getDeBoeking() {
        return deBoeking;
    }

    public static void setDeBoeking(Boeking deBoeking) {
        Boeking.deBoeking = deBoeking;
    }

    public Vakantiehuis getVakantiehuis() {
        return vakantiehuis;
    }

    public void setVakantiehuis(Vakantiehuis vakantiehuis) {
        this.vakantiehuis = vakantiehuis;
    }

    public static ArrayList<Boeking> getAlleBoekingen() {
        return alleBoekingen;
    }

    public static void setAlleBoekingen(ArrayList<Boeking> alleBoekingen) {
        Boeking.alleBoekingen = alleBoekingen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Boeking)) return false;
        Boeking boeking = (Boeking) o;
        return Objects.equals(huurder, boeking.huurder) && Objects.equals(datumVan, boeking.datumVan) && Objects.equals(datumTot, boeking.datumTot) && Objects.equals(vakantiehuis, boeking.vakantiehuis);
    }

    @Override
    public String toString() {
        return "Boeking{" +
                "huurder=" + huurder +
                ", datumVan=" + datumVan +
                ", datumTot=" + datumTot +
                '}';
    }
}
