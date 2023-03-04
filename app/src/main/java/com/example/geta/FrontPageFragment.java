package com.example.geta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

public class FrontPageFragment extends AppCompatActivity {
    private static final long SPLASH_SCREEN_DELAY = 3000;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_front_page);
        Objects.requireNonNull(getSupportActionBar()).hide();

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