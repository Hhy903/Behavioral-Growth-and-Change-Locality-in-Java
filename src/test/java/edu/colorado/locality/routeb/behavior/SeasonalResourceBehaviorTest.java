package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.simulation.Ecosystem;
import edu.colorado.locality.routeb.simulation.Season;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SeasonalResourceBehaviorTest {

    @Test
    void seasonalBehaviorStartsFromSpringAndAddsTwoGrass() {
        Ecosystem ecosystem = new Ecosystem();

        new SeasonalResourceBehavior().apply(ecosystem);

        assertEquals(2, ecosystem.getCreatures().size());
        assertEquals(Season.SUMMER, ecosystem.getCurrentSeason());
    }

    @Test
    void seasonalBehaviorAddsOneGrassInSummer() {
        Ecosystem ecosystem = new Ecosystem();
        SeasonalResourceBehavior behavior = new SeasonalResourceBehavior();

        behavior.apply(ecosystem);
        behavior.apply(ecosystem);

        assertEquals(3, ecosystem.getCreatures().size());
        assertEquals(Season.AUTUMN, ecosystem.getCurrentSeason());
    }

    @Test
    void seasonalBehaviorAddsNoGrassInAutumn() {
        Ecosystem ecosystem = new Ecosystem();
        SeasonalResourceBehavior behavior = new SeasonalResourceBehavior();

        behavior.apply(ecosystem);
        behavior.apply(ecosystem);
        int beforeAutumn = ecosystem.getCreatures().size();
        behavior.apply(ecosystem);

        assertEquals(beforeAutumn, ecosystem.getCreatures().size());
        assertEquals(Season.WINTER, ecosystem.getCurrentSeason());
    }

    @Test
    void seasonalBehaviorAgesLivingGrassInWinter() {
        Ecosystem ecosystem = new Ecosystem();
        SeasonalResourceBehavior behavior = new SeasonalResourceBehavior();
        Grass grass = new Grass("Patch-1");

        ecosystem.addCreature(grass);
        ecosystem.advanceSeason();
        ecosystem.advanceSeason();
        ecosystem.advanceSeason();

        behavior.apply(ecosystem);

        assertEquals(1, grass.getAge());
        assertEquals(Season.SPRING, ecosystem.getCurrentSeason());
    }

    @Test
    void seasonalBehaviorCanKillGrassInWinterThroughResourceDecline() {
        Ecosystem ecosystem = new Ecosystem();
        SeasonalResourceBehavior behavior = new SeasonalResourceBehavior();
        Grass grass = new Grass("Patch-1");

        grass.incrementAge();
        grass.incrementAge();

        ecosystem.addCreature(grass);
        ecosystem.advanceSeason();
        ecosystem.advanceSeason();
        ecosystem.advanceSeason();

        behavior.apply(ecosystem);

        assertFalse(grass.isAlive());
        assertTrue(grass.getAge() >= grass.getMaxAge());
    }
}
