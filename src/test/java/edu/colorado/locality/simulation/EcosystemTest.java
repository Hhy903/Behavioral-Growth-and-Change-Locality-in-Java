package edu.colorado.locality.simulation;

import edu.colorado.locality.core.Creature;
import edu.colorado.locality.entity.Grass;
import edu.colorado.locality.entity.Rabbit;
import edu.colorado.locality.entity.Wolf;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    void reproductionStepAddsNewRabbitAtEndOfRound() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit first = new Rabbit("Bunny-1", 2, 0);
        Rabbit second = new Rabbit("Bunny-2", 3, 0);

        ecosystem.addCreature(first);
        ecosystem.addCreature(second);

        ecosystem.simulateReproduction();

        assertEquals(3, ecosystem.getCreatures().size());

        Creature offspring = ecosystem.getCreatures().get(2);
        assertNotNull(offspring);
        assertTrue(offspring instanceof Rabbit);
        assertEquals(2, offspring.getX());
        assertEquals(0, offspring.getY());
    }

    @Test
    void reproductionStepSkipsDeadCreatures() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit first = new Rabbit("Bunny-1", 0, 0);
        Rabbit second = new Rabbit("Bunny-2", 1, 0);
        second.die();

        ecosystem.addCreature(first);
        ecosystem.addCreature(second);

        ecosystem.simulateReproduction();

        assertEquals(2, ecosystem.getCreatures().size());
    }

    @Test
    void reproductionStepDoesNotLetNewbornReproduceInSameRound() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit first = new Rabbit("Bunny-1", 0, 0);
        Rabbit second = new Rabbit("Bunny-2", 1, 0);
        Rabbit third = new Rabbit("Bunny-3", 10, 0);
        Rabbit fourth = new Rabbit("Bunny-4", 11, 0);

        ecosystem.addCreature(first);
        ecosystem.addCreature(second);
        ecosystem.addCreature(third);
        ecosystem.addCreature(fourth);

        ecosystem.simulateReproduction();

        assertEquals(6, ecosystem.getCreatures().size());
    }

    @Test
    void agingStepAdvancesAgeForLivingCreatures() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha");
        Rabbit rabbit = new Rabbit("Bunny");
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        ecosystem.simulateAgingAndDeath();

        assertEquals(1, wolf.getAge());
        assertEquals(1, rabbit.getAge());
        assertEquals(1, grass.getAge());
    }

    @Test
    void agingStepKillsCreaturesThatReachMaxAge() {
        Ecosystem ecosystem = new Ecosystem();
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(grass);

        ecosystem.simulateAgingAndDeath();
        ecosystem.simulateAgingAndDeath();
        ecosystem.simulateAgingAndDeath();

        assertEquals(3, grass.getAge());
        assertFalse(grass.isAlive());
    }

    @Test
    void agingStepDoesNotAdvanceDeadCreaturesFurther() {
        Ecosystem ecosystem = new Ecosystem();
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(grass);

        ecosystem.simulateAgingAndDeath();
        ecosystem.simulateAgingAndDeath();
        ecosystem.simulateAgingAndDeath();
        ecosystem.simulateAgingAndDeath();

        assertEquals(3, grass.getAge());
        assertFalse(grass.isAlive());
    }

    @Test
    void diseaseStepSpreadsAcrossSpeciesToAdjacentCreatures() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha", 0, 0);
        Rabbit rabbit = new Rabbit("Bunny", 1, 0);
        Grass grass = new Grass("Patch-1", 2, 0);

        wolf.infect();

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        ecosystem.simulateDiseaseSpread();

        assertTrue(wolf.isInfected());
        assertTrue(rabbit.isInfected());
        assertFalse(grass.isInfected());
    }

    @Test
    void diseaseStepDoesNotLetNewlyInfectedCreatureSpreadInSameRound() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha", 0, 0);
        Rabbit rabbit = new Rabbit("Bunny", 1, 0);
        Grass grass = new Grass("Patch-1", 2, 0);

        wolf.infect();

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(grass);

        ecosystem.simulateDiseaseSpread();

        assertTrue(rabbit.isInfected());
        assertFalse(grass.isInfected());
    }

    @Test
    void diseaseStepProgressesOnlyActiveCarriersAndCanKillThem() {
        Ecosystem ecosystem = new Ecosystem();
        Rabbit rabbit = new Rabbit("Bunny", 0, 0);
        Wolf wolf = new Wolf("Alpha", 1, 0);

        rabbit.infect();

        ecosystem.addCreature(rabbit);
        ecosystem.addCreature(wolf);

        ecosystem.simulateDiseaseSpread();
        ecosystem.simulateDiseaseSpread();

        assertFalse(rabbit.isAlive());
        assertTrue(wolf.isInfected());
        assertEquals(1, wolf.getInfectionAge());
    }

    @Test
    void diseaseStepSkipsDeadCreaturesAsTargets() {
        Ecosystem ecosystem = new Ecosystem();
        Wolf wolf = new Wolf("Alpha", 0, 0);
        Rabbit rabbit = new Rabbit("Bunny", 1, 0);
        rabbit.die();

        wolf.infect();

        ecosystem.addCreature(wolf);
        ecosystem.addCreature(rabbit);

        ecosystem.simulateDiseaseSpread();

        assertFalse(rabbit.isInfected());
    }

    @Test
    void seasonalPolicyStartsInSpring() {
        Ecosystem ecosystem = new Ecosystem();

        assertEquals(Season.SPRING, ecosystem.getCurrentSeason());
    }

    @Test
    void springPolicyAddsTwoGrassResources() {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.applySeasonalResourcePolicy();

        assertEquals(2, ecosystem.getCreatures().size());
        assertTrue(ecosystem.getCreatures().get(0) instanceof Grass);
        assertTrue(ecosystem.getCreatures().get(1) instanceof Grass);
    }

    @Test
    void summerPolicyAddsOneGrassResource() {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.advanceSeason();
        ecosystem.applySeasonalResourcePolicy();

        assertEquals(Season.SUMMER, ecosystem.getCurrentSeason());
        assertEquals(1, ecosystem.getCreatures().size());
        assertTrue(ecosystem.getCreatures().get(0) instanceof Grass);
    }

    @Test
    void autumnPolicyAddsNoNewGrassResources() {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.advanceSeason();
        ecosystem.advanceSeason();
        ecosystem.applySeasonalResourcePolicy();

        assertEquals(Season.AUTUMN, ecosystem.getCurrentSeason());
        assertEquals(0, ecosystem.getCreatures().size());
    }

    @Test
    void winterPolicyAgesLivingGrassAsResourceDecline() {
        Ecosystem ecosystem = new Ecosystem();
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(grass);
        ecosystem.advanceSeason();
        ecosystem.advanceSeason();
        ecosystem.advanceSeason();

        ecosystem.applySeasonalResourcePolicy();

        assertEquals(Season.WINTER, ecosystem.getCurrentSeason());
        assertEquals(1, grass.getAge());
    }

    @Test
    void advancingSeasonWrapsBackToSpring() {
        Ecosystem ecosystem = new Ecosystem();

        ecosystem.advanceSeason();
        ecosystem.advanceSeason();
        ecosystem.advanceSeason();
        ecosystem.advanceSeason();

        assertEquals(Season.SPRING, ecosystem.getCurrentSeason());
    }
}
