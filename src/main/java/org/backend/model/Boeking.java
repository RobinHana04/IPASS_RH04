package org.backend.model;

import java.io.Serializable;
import java.util.Objects;

public class Boeking implements Serializable {
    private Huurder huurder;
    private double bedrag;
    private int transactieNr;
    private String datumVan;
    private String datumTot;
    private Vakantiehuis vakantiehuis;
    private static Boeking deBoeking;

    public Boeking(Huurder huurder, Vakantiehuis vakantiehuis, double bedrag, int transactieNr, String datumVan, String datumTot) {
        this.huurder = huurder;
        this.bedrag = bedrag;
        this.transactieNr = transactieNr;
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

    public double getBedrag() {
        return bedrag;
    }

    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }

    public int getTransactieNr() {
        return transactieNr;
    }

    public void setTransactieNr(int transactieNr) {
        this.transactieNr = transactieNr;
    }

    public String getDatumVan() {
        return datumVan;
    }

    public void setDatumVan(String datumVan) {
        this.datumVan = datumVan;
    }

    public String getDatumTot() {
        return datumTot;
    }

    public void setDatumTot(String datumTot) {
        this.datumTot = datumTot;
    }

    public static Boeking getDeBoeking() {
        return deBoeking;
    }

    public static void setDeBoeking(Boeking deBoeking) {
        Boeking.deBoeking = deBoeking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boeking boeking = (Boeking) o;
        return Double.compare(boeking.bedrag, bedrag) == 0 && transactieNr == boeking.transactieNr && Objects.equals(huurder, boeking.huurder) && Objects.equals(datumVan, boeking.datumVan) && Objects.equals(datumTot, boeking.datumTot);
    }

    @Override
    public String toString() {
        return "Boeking{" +
                "huurder=" + huurder +
                ", bedrag=" + bedrag +
                ", transactieNr=" + transactieNr +
                ", datumVan=" + datumVan +
                ", datumTot=" + datumTot +
                '}';
    }

}
