package dev.victorman.cornhole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ConstraintLayout constraintLayout;
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        constraintLayout = new ConstraintLayout(context);

        gameView = new GameView(context);
        gameView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        gameView.setOnTouchListener(new AimTouchListener());

        constraintLayout.addView(gameView);

        setContentView(constraintLayout);

        Runnable r = new Runnable() {
            long elapsedTime;
            long previousFrameTime = 0;
            long fps = 40;
            long frameDuration = 1000/fps;

            @Override
            public void run() {
                while(true) {
                    elapsedTime = System.currentTimeMillis() - previousFrameTime;
                    if(elapsedTime > frameDuration) {
                        gameView.invalidate();
                        previousFrameTime = System.currentTimeMillis();
                    }
                }
            }
        };
        Thread gameThread = new Thread(r);
        gameThread.start();
    }
}