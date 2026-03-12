package edu.colorado.locality.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
}
