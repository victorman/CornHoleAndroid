package dev.victorman.cornhole;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class AimTouchListener implements View.OnTouchListener {
    private static final String TAG = AimTouchListener.class.getCanonicalName();

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        GameView view = (GameView) v;

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                view.fingerDown(x,y);
                break;
            case MotionEvent.ACTION_UP:
                view.fingerUp(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                view.fingerMove(x,y);
                break;
        }

        view.invalidate();

        return true;
    }
}
