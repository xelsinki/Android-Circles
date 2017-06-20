package com.gomummi.alex.circle;

import android.graphics.Color;

/**
 * Created by Alex on 22.4.2017.
 *
 * Circle objektin luokka, joka on pelajan ympyrä
 */

public class GameCircle extends Circle {


    public static final int  SPEED = 30;

    // Constructor pelajan ympyrälle
    public GameCircle(int x, int y) {
        super(x, y, 40);
        setColor(Color.CYAN);
    }


    // Liikuu kun kosketaan
    public void moveGamer(int x1, int y1) {

        //liikumis suunta ja nopeus joka on suhteessa kosketuksen etäisyyteen
        int dx = (x - x1) * SPEED / GameManager.getWidth();
        int dy = (y - y1) * SPEED / GameManager.getHeight();

        // uusi paikka on:
        x -= dx;
        y -= dy;
    }

    public void newGame() {
        // palauttaa alkuperäisen koon
        radius = 50;
    }

    // Kasvattaa pelajan kokoa syömisen jälkeen
    public void growSize(Circle circle) {

        // Uusi säde on neliöjuuri summasta säteistä potensissa kaksi (KATSO LIITE)
        radius = (int) Math.sqrt(Math.pow(radius, 2)+ Math.pow(circle.radius, 2));

    }
}
