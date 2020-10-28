package dev.victorman.cornhole;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CornHoleBoard extends Drawable {

    private final Rect rect;
    private final Paint paint;

    public CornHoleBoard(float left, float top, float width, float height) {
        this.rect = new Rect((int)left, (int)top, (int) (left + width), (int) (top + height));
        this.paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5.0f);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawRect(rect, paint);
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

    public Rect getRect() {
        return rect;
    }
}
