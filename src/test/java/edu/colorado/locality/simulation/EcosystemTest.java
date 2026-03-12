package edu.colorado.locality.simulation;

import edu.colorado.locality.entity.Grass;
import edu.colorado.locality.entity.Rabbit;
import edu.colorado.locality.entity.Wolf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EcosystemTest {

    @Test
    void ecosystemStoresCreatures() {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.addCreature(new Wolf("Alpha"));
        ecosystem.addCreature(new Rabbit("Bunny"));

        assertEquals(2, ecosystem.getCreatures().size());
    }

    @Test
    void feedingStepUsesCurrentStateInInsertionOrder() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Rabbit rabbit = new Rabbit("Bunny");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        ecosystem.simulateFeeding();

        assertTrue(wolf.isAlive());
        assertFalse(rabbit.isAlive());
        assertTrue(grass.isAlive());
    }

    @Test
    void feedingStepLetsRabbitEatGrassWhenPredatorIsAbsent() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit rabbit = new Rabbit("Bunny");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        ecosystem.simulateFeeding();

        assertTrue(rabbit.isAlive());
        assertFalse(grass.isAlive());
    }

    @Test
    void feedingStepDoesNotAffectUnsupportedPrey() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(grass);

        ecosystem.simulateFeeding();

        assertTrue(wolf.isAlive());
        assertTrue(grass.isAlive());
    }

    @Test
    void movementStepMovesAliveCreatures() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha", 0, 0);
        Rabbit rabbit = new Rabbit("Bunny", 5, 2);
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        ecosystem.simulateMovement();

        assertEquals(2, wolf.getX());
        assertEquals(0, wolf.getY());
        assertEquals(6, rabbit.getX());
        assertEquals(2, rabbit.getY());
        assertEquals(0, grass.getX());
        assertEquals(0, grass.getY());
    }

    @Test
    void movementStepSkipsDeadCreatures() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit rabbit = new Rabbit("Bunny", 4, 1);
        rabbit.die();

        ecosystem.addCreature(rabbit);

        ecosystem.simulateMovement();

        assertEquals(4, rabbit.getX());
        assertEquals(1, rabbit.getY());
    }
}
