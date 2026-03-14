package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;
import edu.colorado.locality.routeb.simulation.Ecosystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovementBehaviorTest {

    @Test
    void movementBehaviorMovesWolfByTwoSteps() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha", 1, 2);

        ecosystem.addCreature(wolf);

        new MovementBehavior().apply(ecosystem);

        assertEquals(3, wolf.getX());
        assertEquals(2, wolf.getY());
    }

    @Test
    void movementBehaviorMovesRabbitByOneStep() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit rabbit = new Rabbit("Bunny", 5, 0);

        ecosystem.addCreature(rabbit);

        new MovementBehavior().apply(ecosystem);

        assertEquals(6, rabbit.getX());
        assertEquals(0, rabbit.getY());
    }

    @Test
    void movementBehaviorLeavesGrassInPlace() {
        Ecosystem ecosystem = new Ecosystem();
        Grass grass = new Grass("Patch-1", 4, 4);

        ecosystem.addCreature(grass);

        new MovementBehavior().apply(ecosystem);

        assertEquals(4, grass.getX());
        assertEquals(4, grass.getY());
    }

    @Test
    void movementBehaviorSkipsDeadCreatures() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit rabbit = new Rabbit("Bunny", 2, 1);
        rabbit.die();

        ecosystem.addCreature(rabbit);

        new MovementBehavior().apply(ecosystem);

        assertEquals(2, rabbit.getX());
        assertEquals(1, rabbit.getY());
    }
}
