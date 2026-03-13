package edu.colorado.locality.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GrassTest {

    @Test
    void grassUsesSpeciesSpecificMaxAge() {
        Grass grass = new Grass("Patch-1");

        assertEquals(3, grass.getMaxAge());
    }

    @Test
    void grassDiesFromAging() {
        Grass grass = new Grass("Patch-1");

        grass.ageOneStep();
        grass.ageOneStep();
        grass.ageOneStep();

        assertEquals(3, grass.getAge());
        assertFalse(grass.isAlive());
    }

    @Test
    void deadGrassDoesNotAgeFurther() {
        Grass grass = new Grass("Patch-1");

        grass.ageOneStep();
        grass.ageOneStep();
        grass.ageOneStep();
        grass.ageOneStep();

        assertEquals(3, grass.getAge());
        assertFalse(grass.isAlive());
        assertTrue(grass.getMaxAge() == 3);
    }

    @Test
    void grassCanBeInfected() {
        Grass grass = new Grass("Patch-1");

        assertTrue(grass.infect());
        assertTrue(grass.isInfected());
    }
}
