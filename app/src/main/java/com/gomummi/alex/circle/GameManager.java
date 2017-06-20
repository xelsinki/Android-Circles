package com.gomummi.alex.circle;

import java.util.ArrayList;

/**
 * Pelitoiminta
 *
 */

public class GameManager {

    private GameCircle gameCircle;
    // Vihollisympyröiden kokoelma
    private ArrayList<OtherCircle> circles;
   // ruudun parametrit
    private CanvasView canvasView;
    private static int width;
    private static int height;
    //Ympyröiden määrä
    public static final int CIRCLES = 10;



    // Constructor
    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        // Staatiset muuttujat ei onnnistu antamaan this-sanan kautta
        width = w;
        height = h;
        // laittaa ruudulle pelajan ympyrän
        initGameCircle();
        // laittaa ruudulle ympyrät
        initOtherCircles();


    }


    //Metodi joka luo ympyrän parametereilla
    private void initGameCircle() {
        // pelajan ympyrän paika suuhteessa ruudun levyteen ja korkeuteen keskellä ruutua
        gameCircle = new GameCircle(width/2, height/2);
    }

    //
    private void initOtherCircles() {

        //Pelin alussa pelajan vieressä ei saa olla mitään muuta palloa
        // Alue pelajan ympyrän vieressä
        Circle gameCircleArea = gameCircle.getCircleArea();

        // Ympyröiden kokoelma
        circles = new ArrayList<OtherCircle>();


        // Silmukka joka luo ympyrät
        for (int i = 0; i < CIRCLES; i++) {

            OtherCircle circle;

            //Silmukka tekee uuden ympyrän ja tarkistaa että ympyrä ei ole pelajan ympyrän aluella
            //  sitten se lisää sen kokoelmaan
            do {
                // Vihollinen ympyrä on satunnainen
                circle = OtherCircle.getRandomCircle();
                //
            } while (circle.isIntersect(gameCircleArea));
            circles.add(circle);
        }
        setOtherCirclesColor();
    }

    private void setOtherCirclesColor() {

        // Silmukka joka valitse ympyröiden oikea väri kaikille ympyrille
        for (OtherCircle circle : circles) {
            //Vertaillaan pelajaan palloon
            circle.choiseColor(gameCircle);
           }
    }

    // Pituuden ja leveyden Getterit
    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }


    public void onDraw(){

        // metodi joka piirtää ympyrän rudulle
        canvasView.drawCircle(gameCircle);
        //Silmukka joka piirtää ympyrät ruudulle
        for (OtherCircle circle : circles) {
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {

        //liikutaan pelajan ympyrä
        gameCircle.moveGamer(x, y);

        // Tarkista törmäyksiä
        checkCollision();

        //liikkutaan muut ympyrät
        moveCircles();

    }

    // Tarkistaa törmäykset
    private void checkCollision() {

        Circle circleIntersect = null;
        // Silmukka tarkistaa jokaisen ypyrän
        for (OtherCircle circle : circles) {

            if (gameCircle.isIntersect(circle)) {
                // Tarkistaa törmätyn ympyrän koko (saako syödä vai keskeyttää pelin)
                if (circle.isSmaller(gameCircle)) {

                    // Kasvatetaan pelajan ympyrä syötyn ympyrän verran
                    gameCircle.growSize(circle);
                    circleIntersect = circle;
                    // Lasketaan vihollisten väärit uudestaan
                    setOtherCirclesColor();
                    break;
                } else {
                    // Peli keskeytetään
                    gameEnd("YOU LOSE!!!");
                    return;
                }

            }
        }
            // jos törmätty ympyrä on olemassa, niin poistetaan sen listasta
        if (circleIntersect != null) {

            circles.remove(circleIntersect);
        }
        // Jos listassa ei ole enää ympyröitä, lopetetaan peli
        if (circles.isEmpty()){
            gameEnd("YOU WIN!!!");
        }
    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);
        gameCircle.newGame();
        initOtherCircles();
        canvasView.redraw();
    }

    private void moveCircles() {

        // Silmukka pistää liikkumaan kaikki ympyrät
        for (OtherCircle circle : circles) {

            circle.moveCircles();
        }
    }
}
