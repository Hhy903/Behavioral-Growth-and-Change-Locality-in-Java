package edu.colorado.locality.entity;

import edu.colorado.locality.core.Creature;

public class Grass extends Creature {
    public Grass(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Grass";
    }
}