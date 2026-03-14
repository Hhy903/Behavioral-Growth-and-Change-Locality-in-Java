package edu.colorado.locality.routeb.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreatureTest {

    @Test
    void creatureStartsAlive() {
        Creature creature = new Creature("Test") {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        assertTrue(creature.isAlive());
    }

    @Test
    void creatureCanDie() {
        Creature creature = new Creature("Test") {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        creature.die();

        assertFalse(creature.isAlive());
    }

    @Test
    void creatureStartsAtOrigin() {
        Creature creature = new Creature("Test") {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        assertEquals(0, creature.getX());
        assertEquals(0, creature.getY());
    }

    @Test
    void creatureStartsAtAgeZero() {
        Creature creature = new Creature("Test") {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        assertEquals(0, creature.getAge());
    }

    @Test
    void creatureCanTranslatePosition() {
        Creature creature = new Creature("Test", 2, 3) {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        creature.translate(1, -2);

        assertEquals(3, creature.getX());
        assertEquals(1, creature.getY());
    }

    @Test
    void creatureCanIncrementAge() {
        Creature creature = new Creature("Test", 0, 0, 4) {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        creature.incrementAge();

        assertEquals(1, creature.getAge());
        assertEquals(4, creature.getMaxAge());
    }
}
