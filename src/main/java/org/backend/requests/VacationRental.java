package org.backend.requests;

import org.backend.domain.*;
import org.backend.security.Username;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.backend.domain.Huurder.getAlleHuurders;

public class VacationRental implements Serializable {
    private static VacationRental deRental = new VacationRental();
    private List<Vakantiehuis> vakantiehuizenVR = new ArrayList<>();
    private List<Verhuurder> verhuurdersVR = new ArrayList<>();
    private List<Huurder> huurdersVR = new ArrayList<>();
    private List<Boeking> BoekingenVR = new ArrayList<>();
    private List<Username> UsernamesVR = new ArrayList<>();

    public List<Username> getUsernamesVR() {
        return UsernamesVR;
    }

    public void setUsernamesVR(List<Username> usernamesVR) {
        UsernamesVR = usernamesVR;
    }

    public void addUsername(Username un) {
        if (!getUsernamesVR().contains(un)) {
            getUsernamesVR().add(un);
        }
    }

    private static Predicate<NamedObject> hasName(String name) {
        return e -> e.getName().equals(name);
    }

    public static VacationRental getDeRental() {
        return deRental;
    }

    public static void setDeRental(VacationRental deRental) {
        VacationRental.deRental = deRental;
    }

    public void setVakantiehuizenVR(List<Vakantiehuis> vakantiehuizenVR) {
        this.vakantiehuizenVR = vakantiehuizenVR;
    }

    public void setVerhuurdersVR(List<Verhuurder> verhuurdersVR) {
        this.verhuurdersVR = verhuurdersVR;
    }

    public List<Huurder> getHuurdersVR() {
        return huurdersVR;
    }

    public void setHuurdersVR(List<Huurder> huurdersVR) {
        this.huurdersVR = huurdersVR;
    }

    public List<Boeking> getBoekingenVR() {
        return BoekingenVR;
    }

    public void setBoekingenVR(List<Boeking> boekingenVR) {
        BoekingenVR = boekingenVR;
    }

    public void addVakantiehuizenVR(Vakantiehuis vakantiehuis) {
        if(!getVakantiehuizenVR().contains(vakantiehuis)) {
            getVakantiehuizenVR().add(vakantiehuis);
        }
    }

    public void addBoekingenVR(Boeking boeking) {
        if(!getBoekingenVR().contains(boeking)) {
            getBoekingenVR().add(boeking);
        }
    }

    public void addHuurdersVR(Huurder huurder) {
        if(!getHuurdersVR().contains(huurder)) {
            getHuurdersVR().add(huurder);
        }
    }

    public static VacationRental getVacationRental() {
        return deRental;
    }

    public void addVerhuurderVR (Verhuurder verhuurder) {
        getVerhuurdersVR().add(verhuurder);
    }

    public List<Vakantiehuis> getVakantiehuizenVR() {
        return vakantiehuizenVR;
    }

    public List<Verhuurder> getVerhuurdersVR() {
        return verhuurdersVR;
    }

    public static void setVacationRental(VacationRental vacationRental) {
        deRental = vacationRental;
    }

    public List<Vakantiehuis> getAllVakantieHuizen() {
        return Vakantiehuis.getAlleHuizen();
    }

    public Vakantiehuis getVakantieHuisBijNaam(String name) {
        return Vakantiehuis.getAlleHuizen().stream().filter(hasName(name)).findFirst().orElse(null);
    }

    public Huurder getHuurderBijNaam(String name){
        return getAlleHuurders().stream().filter(huurder -> huurder.getName().equals(name)).findFirst().orElse(null);
    }

    public Verhuurder getVerhuurderBijNaam(String name) {
        return Verhuurder.getAlleVerhuurders().stream().filter(hasName(name)).findFirst().orElse(null);
    }

    public List<Verhuurder> getAllVerhuurders() {
        return Verhuurder.getAlleVerhuurders();
    }

}
