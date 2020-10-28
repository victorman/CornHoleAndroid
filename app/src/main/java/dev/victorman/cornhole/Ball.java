package dev.victorman.cornhole;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Ball extends Drawable {

    private float scale;
    float x;
    float y;
    float radius;
    float dx;
    float dy;
    private Paint ballPaint;
    float ax;
    float ay;

    public Ball(float x, float y, float r, Paint paint, float ax, float ay) {
        this.radius = r;
        this.x = x;
        this.y = y;
        ballPaint = paint;
        this.ax = ax;
        this.ay = ay;
        this.scale = 1f;
    }

    public void setSpeed(float dx, float dy) {
        this.dx = dx * scale;
        this.dy = dy * scale;
    }

    public void update() {
        dx += ax;
        dy += ay;
        x += dx * scale;
        y += dy * scale;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawCircle(x, y, radius, ballPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean intersects(Rect rect) {
        return Rect.intersects(new Rect((int)(x - radius),(int)(y - radius), (int)(x + radius), (int)(y + radius)), rect);
    }
}
