package org.backend;

import org.backend.domain.Vakantiehuis;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VakantieHuisTests {
    Vakantiehuis vh1 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh2 = Vakantiehuis.getHetVakantiehuis();
    Vakantiehuis vh3 = Vakantiehuis.getHetVakantiehuis();

    @BeforeEach
    public void setup() {
        vh1 = new Vakantiehuis("Huis1", "A", "30m2", 1);
        vh2 = new Vakantiehuis("Huis1", "A", "30m2", 1);
        vh3 = new Vakantiehuis("Huis2", "B", "40m2", 2);
    }

    @Test
    public void geenDubbeleVakantiehuizen() {
        Vakantiehuis.addHuis(vh1);
        Vakantiehuis.addHuis(vh2);
        assertEquals(1, Vakantiehuis.getAlleHuizen().size());
    }

    @Test
    public void EqualsMethodeVakantieHuis() {
        assertEquals(vh1, vh2);
    }

}
