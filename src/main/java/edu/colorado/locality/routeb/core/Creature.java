package edu.colorado.locality.routeb.core;

public abstract class Creature {
    private final String name;
    private boolean alive;
    private int x;
    private int y;
    private int age;
    private int maxAge;

    protected Creature(String name) {
        this(name, 0, 0, Integer.MAX_VALUE);
    }

    protected Creature(String name, int x, int y) {
        this(name, x, y, Integer.MAX_VALUE);
    }

    protected Creature(String name, int x, int y, int maxAge) {
        this.name = name;
        this.alive = true;
        this.x = x;
        this.y = y;
        this.age = 0;
        this.maxAge = maxAge;
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

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void die() {
        this.alive = false;
    }

    public void translate(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public void incrementAge() {
        this.age++;
    }

    public abstract String getType();
}
