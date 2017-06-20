package com.gomummi.alex.circle;

/**
 *
 * Rajapinta, piirtää ruudulle
 *
 */

public interface ICanvasView {

    //  piirtää ympyrän ruudulle
    void drawCircle(Circle circle);

    // pelin päättyessä piirtää uudestaan ruudun
    void redraw();

    void showMessage(String text);
}
