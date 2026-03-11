package edu.colorado.locality.core;

public abstract class Creature {
    protected String name;
    protected boolean alive;

    public Creature(String name) {
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