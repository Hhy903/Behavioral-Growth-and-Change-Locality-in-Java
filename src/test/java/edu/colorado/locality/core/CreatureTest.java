package edu.colorado.locality.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreatureTest {

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
    void creatureStartsAtOrigin() {
        Creature creature = new Creature("Test") {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        assertTrue(creature.getX() == 0);
        assertTrue(creature.getY() == 0);
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
    void creatureCannotFeedByDefault() {
        Creature creature = new Creature("Test") {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        Creature prey = new Creature("Prey") {
            @Override
            public String getType() {
                return "PreyType";
            }
        };

        assertFalse(creature.feedOn(prey));
        assertTrue(prey.isAlive());
    }

    @Test
    void creatureDoesNotMoveByDefault() {
        Creature creature = new Creature("Test", 3, 4) {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        creature.move();

        assertTrue(creature.getX() == 3);
        assertTrue(creature.getY() == 4);
    }

    @Test
    void creatureCannotReproduceByDefault() {
        Creature creature = new Creature("Test", 0, 0) {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        Creature mate = new Creature("Mate", 0, 0) {
            @Override
            public String getType() {
                return "MateType";
            }
        };

        assertTrue(creature.reproduceWith(mate, "Child") == null);
    }

    @Test
    void agingIncrementsAgeForLivingCreature() {
        Creature creature = new Creature("Test", 0, 0, 3) {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        creature.ageOneStep();

        assertEquals(1, creature.getAge());
        assertTrue(creature.isAlive());
    }

    @Test
    void agingKillsCreatureAtMaxAge() {
        Creature creature = new Creature("Test", 0, 0, 2) {
            @Override
            public String getType() {
                return "TestType";
            }
        };

        creature.ageOneStep();
        creature.ageOneStep();

        assertEquals(2, creature.getAge());
        assertFalse(creature.isAlive());
    }
}
