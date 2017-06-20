package com.gomummi.alex.circle;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by Alex on 25.4.2017.
 *
 *
 */

public class OtherCircle extends Circle {

    private double dx;
    private double dy;

    // Constructor
    public OtherCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    // Generoi satunnaisia ympyröitä ja niiden liikkumissuuntan
    public static OtherCircle getRandomCircle() {
        // Satunnaisluku
        Random random = new Random();

        // tehdään metodin omat muuttujat
        int x , y , dx, dy, radius;

        // Satunnainen paikka ja koko
        // satunnainen kokonaisluku pituudesta ja leveyedstä
         x = random.nextInt(GameManager.getWidth());
         y = random.nextInt(GameManager.getHeight());

        //Radius min 5, radius max 100
        radius = 5 + random.nextInt(95);

        // Uudet koordinatit dx,dy riippuvat satunnaisesta luvusta
        // Lentävät eri suuntiin
         dx = random.nextInt(10) - 5;
         dy = random.nextInt(10)- 5;

        // lentävät alas oikeaan suuntaan
//        dx = random.nextInt(10);
//        dy = random.nextInt(10);


        // luodaan uusi vihillisympyrän
        OtherCircle otherCircle = new OtherCircle(x, y, radius, dx, dy);

        return otherCircle;
    }

    // laskee ympyröiden oikea väri, vertailemalla pelajan ympyrään
    public void choiseColor(GameCircle gameCircle) {

        // jos pienempi kun pelajan, niin väri VIHREÄ
        if (isSmaller(gameCircle)){
            setColor(Color.GREEN);
        } else {
            setColor(Color.MAGENTA);
        }
    }
    // Vertaillaan ympyrät pelajan ympyrään
    public boolean isSmaller(Circle circle) {

        if(radius < circle.radius){
            return  true;
        } else {
            return false;
        }
    }

    // Liikuttaa ympyrät
    public void moveCircles() {

        // Normaali nopeus
        x += dx;
        y += dy;

        // Hidas nopeus
//        x += dx/2;
//        y += dy/2;

        //Tarkistaa reunat omassa metodissa
        checkBorders();
    }

    // Vaihtaa liikkumissuuntaa kun pallo menee reunan yli
    private void checkBorders() {

        // Jos X-koordinatit menivät reunan yli yhteen tai toiseen suuntaan
        if ( x < 0 || x > GameManager.getWidth()) {
            // vaihdetaan liikumissuuntaa x-kooordinatistossa
            dx = -dx;

        }
        // Jos Y-koordinatit menivät näytön reunan yli yhteen tai toiseen suuntaan
        if ( y < 0 || y > GameManager.getHeight() ) {
            // vaihdetaan liikkumissuuntaa y-kooordinatistossa
            dy = -dy;

        }
    }
}
