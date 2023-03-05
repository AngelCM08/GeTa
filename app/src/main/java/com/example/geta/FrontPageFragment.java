package com.example.geta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class FrontPageFragment extends AppCompatActivity {
    private static final long SPLASH_SCREEN_DELAY = 3000;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_front_page);

        logo = findViewById(R.id.logo);

        logo.setScaleX(0.1f);
        logo.setScaleY(0.1f);
        logo.animate()
                .scaleX(1f)
                .scaleY(1f)
                .rotation(0f)
                .setDuration(1500)
                .setInterpolator(new OvershootInterpolator())
                .start();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_DELAY);
    }
}