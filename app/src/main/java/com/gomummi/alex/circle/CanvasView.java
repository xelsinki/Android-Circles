package com.gomummi.alex.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 *       Näkymän luokka
 */

public class CanvasView  extends View implements ICanvasView{

    // muuttujat leveys ja korkeus
    private static int width;
    private static int height;
    private GameManager gameManager;
    // penseli
    private Paint paint;
    private Canvas canvas;
    //Message
    private Toast toast;


    // näkymän konstruktori
    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //initialisoi leveyden ja korkeuden
        intitWidthandHeight(context);

        initPaint();
        gameManager = new GameManager(this, width, height);


    }


    private void intitWidthandHeight(Context context) {
        // saadaan selville ruudun koko
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        // luodaan koordinaatti pisteen
        Point point = new Point();
        //saa tieto ruudun koosta
        display.getSize(point);
        width = point.x;
        height = point.y;

    }

    //Metodi joka luo penselin
    private void initPaint() {
        paint = new Paint();
        //Antialiasing tasoittaa reunat mitä ollaan vedetty*(сглаживание краев)
        paint.setAntiAlias(true);
        //täyttää värillä
        paint.setStyle(Paint.Style.FILL);
    }

    @Override // piirtää kaikki ruudulle
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
     //   gameManager.onDraw(canvas);
        this.canvas = canvas;
        gameManager.onDraw();
    }

    @Override
    public void drawCircle(Circle circle) {

        paint.setColor(circle.getColor());
        // piirtää ympyrän
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
    }

    @Override  //  piirtää uudestaan ruudun
    public void redraw() {
        invalidate();
    }

    @Override
    // Show message (Win or Lose)
    public void showMessage(String text) {
        if (toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getContext(),text, Toast.LENGTH_SHORT);
        //keskelle ruutua
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override   // Toimminta kosketukseen
    public boolean onTouchEvent(MotionEvent event){
        // Kosketuksen koordinatit
        int x =(int)event.getX();
        int y =(int)event.getY();
        //Liikkuuttaa
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            gameManager.onTouchEvent(x, y);
        }
        // uudelleen piirtää koko ruudun
        invalidate();
        return true;
    }
}
