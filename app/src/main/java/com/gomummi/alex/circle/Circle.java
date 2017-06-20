package com.gomummi.alex.circle;

/**
 * Created by Alex on 24.4.2017.
 */

public class Circle {

    // protected, että pystyy kutsumaan toisestza luokasta samassa paketissa
    protected int x;
    protected int y;
    protected int radius;

    private  int color;

    // Constructor
    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }


    // Pelin alussa, pelajan vieressä ei saisi olla muita ympyröitä
    public Circle getCircleArea() {

        // Ympyran alue on ympyrä isomalla radiuksella

        Circle circle = new Circle(x, y, radius * 3);

        return circle;
    }

    // Tarkistaa ympyröiden törmäystä
    public boolean isIntersect(Circle circle) {

        // Lasketaan kolmion Pythagorean teoreeman mukaan (KATSO LIITE)

        if ( Math.pow(radius + circle.radius, 2) >= (Math.pow(x-circle.x, 2))+ (Math.pow(y-circle.y, 2))){

            return true;
        }
            else {
            return false;
        }

    }
}

