package edu.colorado.locality.routeb.core;

public abstract class Creature {
    private final String name;
    private boolean alive;

    protected Creature(String name) {
        this.name = name;
        this.alive = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        this.alive = false;
    }

    public abstract String getType();
}
