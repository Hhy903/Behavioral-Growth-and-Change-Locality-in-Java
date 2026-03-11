package edu.colorado.locality.entity;

import edu.colorado.locality.core.Creature;

public class Rabbit extends Creature {
    public Rabbit(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "Rabbit";
    }
}