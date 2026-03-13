package edu.colorado.locality.entity;

import edu.colorado.locality.core.Creature;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WolfTest {

    @Test
    void wolfMovesTwoStepsOnXAxis() {
        Wolf wolf = new Wolf("Alpha", 1, 2);

        wolf.move();

        assertTrue(wolf.getX() == 3);
        assertTrue(wolf.getY() == 2);
    }

    @Test
    void wolfCanFeedOnRabbit() {
        Wolf wolf = new Wolf("Alpha");
        Rabbit rabbit = new Rabbit("Bunny");

        assertTrue(wolf.feedOn(rabbit));
        assertFalse(rabbit.isAlive());
    }

    @Test
    void wolfCannotFeedOnGrass() {
        Wolf wolf = new Wolf("Alpha");
        Grass grass = new Grass("Patch-1");

        assertFalse(wolf.feedOn(grass));
        assertTrue(grass.isAlive());
    }

    @Test
    void wolfCanReproduceWithAdjacentWolf() {
        Wolf first = new Wolf("Alpha", 4, 1);
        Wolf second = new Wolf("Beta", 5, 1);

        Creature offspring = first.reproduceWith(second, "Wolf-offspring-1");

        assertNotNull(offspring);
        assertTrue(offspring instanceof Wolf);
        assertTrue(offspring.getX() == 4);
        assertTrue(offspring.getY() == 1);
    }

    @Test
    void wolfCannotReproduceWithRabbit() {
        Wolf wolf = new Wolf("Alpha", 1, 0);
        Rabbit rabbit = new Rabbit("Bunny", 2, 0);

        assertTrue(wolf.reproduceWith(rabbit, "Invalid-offspring") == null);
    }

    @Test
    void wolfUsesSpeciesSpecificMaxAge() {
        Wolf wolf = new Wolf("Alpha");

        assertEquals(8, wolf.getMaxAge());
    }
}
