package edu.colorado.locality.routeb.entity;

import edu.colorado.locality.routeb.core.Creature;

public class Grass extends Creature {
    public Grass(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Grass";
    }
}
