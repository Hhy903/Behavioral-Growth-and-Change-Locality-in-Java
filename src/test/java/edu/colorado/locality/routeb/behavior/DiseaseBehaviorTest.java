package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;
import edu.colorado.locality.routeb.simulation.Ecosystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiseaseBehaviorTest {

    @Test
    void diseaseBehaviorSpreadsAcrossSpeciesToAdjacentCreatures() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha", 0, 0);
        Rabbit rabbit = new Rabbit("Bunny", 1, 0);
        Grass grass = new Grass("Patch-1", 2, 0);

        wolf.infect();

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        new DiseaseBehavior().apply(ecosystem);

        assertTrue(wolf.isInfected());
        assertTrue(rabbit.isInfected());
        assertFalse(grass.isInfected());
    }

    @Test
    void diseaseBehaviorDoesNotLetNewlyInfectedCreatureSpreadInSameRound() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha", 0, 0);
        Rabbit rabbit = new Rabbit("Bunny", 1, 0);
        Grass grass = new Grass("Patch-1", 2, 0);

        wolf.infect();

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        new DiseaseBehavior().apply(ecosystem);

        assertTrue(rabbit.isInfected());
        assertFalse(grass.isInfected());
    }

    @Test
    void diseaseBehaviorKillsCarrierAtInfectionLimit() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit rabbit = new Rabbit("Bunny", 0, 0);
        Wolf wolf = new Wolf("Alpha", 1, 0);

        rabbit.infect();

        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(wolf);

        new DiseaseBehavior().apply(ecosystem);
        new DiseaseBehavior().apply(ecosystem);

        assertFalse(rabbit.isAlive());
        assertTrue(wolf.isInfected());
        assertEquals(1, wolf.getInfectionAge());
    }

    @Test
    void diseaseBehaviorSkipsDeadTargets() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha", 0, 0);
        Rabbit rabbit = new Rabbit("Bunny", 1, 0);
        rabbit.die();

        wolf.infect();

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);

        new DiseaseBehavior().apply(ecosystem);

        assertFalse(rabbit.isInfected());
    }
}
