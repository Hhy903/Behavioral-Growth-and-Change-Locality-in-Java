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

    public boolean canEat(Creature other) {
        return false;
    }

    public boolean feedOn(Creature other) {
        if (!alive || other == null || other == this || !other.isAlive() || !canEat(other)) {
            return false;
        }

        other.die();
        return true;
    }

    public abstract String getType();
}
