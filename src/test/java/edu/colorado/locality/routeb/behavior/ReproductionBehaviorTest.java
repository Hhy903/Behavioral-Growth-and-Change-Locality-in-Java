package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.core.Creature;
import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.entity.Rabbit;
import edu.colorado.locality.routeb.entity.Wolf;
import edu.colorado.locality.routeb.simulation.Ecosystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReproductionBehaviorTest {

    @Test
    void reproductionBehaviorAddsNewRabbitForAdjacentParents() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit first = new Rabbit("Bunny-1", 2, 0);
        Rabbit second = new Rabbit("Bunny-2", 3, 0);

        ecosystem.addCreature(first);
        ecosystem.addCreature(second);

        new ReproductionBehavior().apply(ecosystem);

        assertEquals(3, ecosystem.getCreatures().size());
        Creature offspring = ecosystem.getCreatures().get(2);
        assertTrue(offspring instanceof Rabbit);
        assertEquals(2, offspring.getX());
        assertEquals(0, offspring.getY());
    }

    @Test
    void reproductionBehaviorAddsNewWolfForAdjacentParents() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf first = new Wolf("Alpha", 4, 1);
        Wolf second = new Wolf("Beta", 5, 1);

        ecosystem.addCreature(first);
        ecosystem.addCreature(second);

        new ReproductionBehavior().apply(ecosystem);

        assertEquals(3, ecosystem.getCreatures().size());
        assertTrue(ecosystem.getCreatures().get(2) instanceof Wolf);
    }

    @Test
    void reproductionBehaviorRejectsDifferentSpecies() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha", 0, 0);
        Rabbit rabbit = new Rabbit("Bunny", 1, 0);

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);

        new ReproductionBehavior().apply(ecosystem);

        assertEquals(2, ecosystem.getCreatures().size());
    }

    @Test
    void reproductionBehaviorRejectsDeadCreatures() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit first = new Rabbit("Bunny-1", 0, 0);
        Rabbit second = new Rabbit("Bunny-2", 1, 0);
        second.die();

        ecosystem.addCreature(first);
        ecosystem.addCreature(second);

        new ReproductionBehavior().apply(ecosystem);

        assertEquals(2, ecosystem.getCreatures().size());
        assertFalse(second.isAlive());
    }

    @Test
    void reproductionBehaviorDoesNotLetNewbornReproduceInSameRound() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit first = new Rabbit("Bunny-1", 0, 0);
        Rabbit second = new Rabbit("Bunny-2", 1, 0);
        Rabbit third = new Rabbit("Bunny-3", 10, 0);
        Rabbit fourth = new Rabbit("Bunny-4", 11, 0);

        ecosystem.addCreature(first);
        ecosystem.addCreature(second);
        ecosystem.addCreature(third);
        ecosystem.addCreature(fourth);

        new ReproductionBehavior().apply(ecosystem);

        assertEquals(6, ecosystem.getCreatures().size());
    }

    @Test
    void reproductionBehaviorLeavesGrassOutOfReproduction() {
        Ecosystem ecosystem = new Ecosystem();
        Grass first = new Grass("Patch-1", 0, 0);
        Grass second = new Grass("Patch-2", 1, 0);

        ecosystem.addCreature(first);
        ecosystem.addCreature(second);

        new ReproductionBehavior().apply(ecosystem);

        assertEquals(2, ecosystem.getCreatures().size());
    }
}
