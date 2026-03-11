package edu.colorado.locality.core;

import org.junit.jupiter.api.Test;

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
}
