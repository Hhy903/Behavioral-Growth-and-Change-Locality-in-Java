package edu.colorado.locality.routeb.entity;

import edu.colorado.locality.routeb.core.Creature;

public class Grass extends Creature {
    public Grass(String name) {
        super(name);
    }

    public Grass(String name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public String getType() {
        return "Grass";
    }
}
