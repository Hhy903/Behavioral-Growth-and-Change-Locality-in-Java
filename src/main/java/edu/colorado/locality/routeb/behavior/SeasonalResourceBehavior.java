package edu.colorado.locality.routeb.behavior;

import edu.colorado.locality.routeb.core.Creature;
import edu.colorado.locality.routeb.entity.Grass;
import edu.colorado.locality.routeb.simulation.Ecosystem;
import edu.colorado.locality.routeb.simulation.Season;

public class SeasonalResourceBehavior implements BehaviorStep {
    private int grassCounter = 1;

    @Override
    public void apply(Ecosystem ecosystem) {
        Season season = ecosystem.getCurrentSeason();

        switch (season) {
            case SPRING -> addGrass(ecosystem, 2);
            case SUMMER -> addGrass(ecosystem, 1);
            case AUTUMN -> {
                // No new grass resources in autumn.
            }
            case WINTER -> ageLivingGrass(ecosystem);
        }

        ecosystem.advanceSeason();
    }

    private void addGrass(Ecosystem ecosystem, int count) {
        for (int i = 0; i < count; i++) {
            ecosystem.addCreature(new Grass(nextGrassName(), grassCounter - 1, 0));
        }
    }

    private void ageLivingGrass(Ecosystem ecosystem) {
        for (Creature creature : ecosystem.getCreatures()) {
            if (creature instanceof Grass && creature.isAlive()) {
                creature.incrementAge();
                if (creature.getAge() >= creature.getMaxAge()) {
                    creature.die();
                }
            }
        }
    }

    private String nextGrassName() {
        String name = "Seasonal-Grass-" + grassCounter;
        grassCounter++;
        return name;
    }
}
