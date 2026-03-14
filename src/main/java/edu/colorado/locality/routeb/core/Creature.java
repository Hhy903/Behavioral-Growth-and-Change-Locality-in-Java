package edu.colorado.locality.routeb.core;

public abstract class Creature {
    private final String name;
    private boolean alive;
    private int x;
    private int y;

    protected Creature(String name) {
        this(name, 0, 0);
    }

    protected Creature(String name, int x, int y) {
        this.name = name;
        this.alive = true;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void die() {
        this.alive = false;
    }

    public void translate(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public abstract String getType();
}
