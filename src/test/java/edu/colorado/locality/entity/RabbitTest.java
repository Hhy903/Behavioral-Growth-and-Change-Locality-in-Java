package edu.colorado.locality.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RabbitTest {

    @Test
    void rabbitMovesOneStepOnXAxis() {
        Rabbit rabbit = new Rabbit("Bunny", 2, 1);

        rabbit.move();

        assertTrue(rabbit.getX() == 3);
        assertTrue(rabbit.getY() == 1);
    }

    @Test
    void rabbitCanFeedOnGrass() {
        Rabbit rabbit = new Rabbit("Bunny");
        Grass grass = new Grass("Patch-1");

        assertTrue(rabbit.feedOn(grass));
        assertFalse(grass.isAlive());
    }

    @Test
    void deadRabbitCannotFeed() {
        Rabbit rabbit = new Rabbit("Bunny");
        Grass grass = new Grass("Patch-1");

        rabbit.die();

        assertFalse(rabbit.feedOn(grass));
        assertTrue(grass.isAlive());
    }
}
