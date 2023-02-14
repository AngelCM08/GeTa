package com.example.geta;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

public class FrontPageFragment extends AppCompatActivity {
    private ImageView logo;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_front_page);

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
                navController.navigate(R.id.loginFragment);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        logo.startAnimation(animacion);
    }
}