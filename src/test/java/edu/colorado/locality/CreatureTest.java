package edu.colorado.locality;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}