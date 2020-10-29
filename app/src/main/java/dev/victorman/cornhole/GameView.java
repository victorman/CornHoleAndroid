package dev.victorman.cornhole;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {

    private CornHoleBoard cornHoleBoard;
    boolean fingerDown;
    Paint aimPaint;
    Paint ballPaint;
    float startX, startY, endX, endY;
//    long clickTime;
//    long clickDuration;
    float epsilon = 0.0000001f;
    DashPathEffect dashEffect;
    List<Ball> balls;
    List<Ball> goalBalls;
    private float gravity = 9.81f;
    private float friction;
    float scale = 0.25f;
    int score;
    private Paint textPaint;

    public GameView(Context context) {
        super(context);
        fingerDown = false;
        score = 0;

        aimPaint = new Paint();
        aimPaint.setStrokeWidth(6.0f);
        aimPaint.setColor(Color.RED);
        aimPaint.setStyle(Paint.Style.STROKE);
        dashEffect = new DashPathEffect(new float[] {20f,20f}, 0f);
        aimPaint.setPathEffect(dashEffect);

        ballPaint = new Paint();
        ballPaint.setStrokeWidth(2.0f);
        ballPaint.setColor(Color.BLACK);

        textPaint = new Paint();
        textPaint.setTextSize(80f);
        textPaint.setColor(Color.BLUE);

        balls = new ArrayList<Ball>(10);
        goalBalls = new ArrayList<Ball>(10);
    }


    public void fingerDown(float x, float y) {
        fingerDown = true;
        startX = endX = x;
        startY = endY = y;
//        clickTime = System.currentTimeMillis();
//        clickDuration = 0L;
    }

    public void fingerMove(float x, float y) {
        endX = x;
        endY = y;
    }

    public void fingerUp(float x, float y) {

        fingerDown = false;
        spawnBall();
    }

    private void spawnBall() {
        Ball ball = new Ball(startX, startY, 10, ballPaint, 0f, gravity);
        ball.setSpeed(endX - startX, endY - startY);
        ball.setScale(scale);
        balls.add(ball);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("" + score,10f, 70f, textPaint);

        if(cornHoleBoard == null) {

            cornHoleBoard = new CornHoleBoard(getRight() - 310f, getBottom() - 60f, 300f, 50f);
        }

        cornHoleBoard.draw(canvas);

        if (fingerDown) {
            drawAim(canvas);
        }

        for(Ball ball: goalBalls) {
            ball.draw(canvas);
        }

        for(Ball ball: balls) {
            ball.draw(canvas);
        }

//        clickDuration = (System.currentTimeMillis() - clickTime);
//        long dashPhase = (long) (clickDuration / 31.25);

        update();
    }

    private void update() {
        List<Ball> removeList = new ArrayList<Ball>();
        for(Ball ball: balls) {
            ball.update();
            if(ball.intersects(cornHoleBoard.getRect())) {
                score++;
                ball.setSpeed(ball.dx, 0f);
                ball.ay = 0f;
                goalBalls.add(ball);
                removeList.add(ball);
            }
            if(ball.y > getBottom()) {
                removeList.add(ball);
            }
        }

        for(Ball ball: goalBalls) {
            ball.ax = -(ball.dx * 0.2f);
            ball.update();
            ball.ay = 0f;
        }

        for (Ball ball: removeList) {
            balls.remove(ball);
        }
        removeList.clear();
    }

    private void drawAim(Canvas canvas) {
        canvas.drawLine(startX, startY, endX, endY, aimPaint);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
