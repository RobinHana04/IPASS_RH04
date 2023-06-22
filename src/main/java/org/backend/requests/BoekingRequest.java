package org.backend.requests;

import org.backend.domain.Huurder;
import org.backend.domain.Vakantiehuis;

import java.time.LocalDate;

public class BoekingRequest {
    public Huurder huurder;
    public LocalDate datumVan;
    public LocalDate datumTot;

    public Vakantiehuis vakantiehuis;
}
