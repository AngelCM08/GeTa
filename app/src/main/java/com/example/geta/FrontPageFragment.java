package com.example.geta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

        Animation animacion = new TranslateAnimation(0, 0, 0, -150);
        animacion.setDuration(1000);

        animacion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                logo.clearAnimation(); // Limpia la animación para que la imagen no vuelva a su posición anterior
                logo.setTranslationY(30); // Cambia la posición de la imagen 200 píxeles hacia abajo
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        logo.startAnimation(animacion);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_DELAY);
    }
}