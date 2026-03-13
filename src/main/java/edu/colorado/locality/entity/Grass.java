package edu.colorado.locality.entity;

import edu.colorado.locality.core.Creature;

public class Grass extends Creature {
    private static final int MAX_AGE = 3;

    public Grass(String name) {
        super(name, 0, 0, MAX_AGE);
    }

    public Grass(String name, int x, int y) {
        super(name, x, y, MAX_AGE);
    }

    @Override
    public String getType() {
        return "Grass";
    }
}
