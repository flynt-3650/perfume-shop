package com.example.perfumeshop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static List<String> getSus() {
        return Arrays.asList("admin@gmail.com", "superuser2@example.com");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationHelper.createNotificationChannel(this);
        NotificationHelper.sendNotification(this, "Добро пожаловать!", "Вы вошли в приложение Perfume Shop");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                Navigation.findNavController(this, R.id.fragment_container)
                        .navigate(R.id.cartFragment);
                return true;
            } else if (item.getItemId() == R.id.navigation_shop) {
                Navigation.findNavController(this, R.id.fragment_container)
                        .navigate(R.id.shopFragment);
                return true;
            } else if (item.getItemId() == R.id.navigation_about_app) {
                Navigation.findNavController(this, R.id.fragment_container)
                        .navigate(R.id.profileFragment);
                return true;
            } else if (item.getItemId() == R.id.navigation_about_author) {
                Navigation.findNavController(this, R.id.fragment_container)
                        .navigate(R.id.aboutAuthorFragment);
                return true;
            } else if (item.getItemId() == R.id.navigation_manual) {
                Navigation.findNavController(this, R.id.fragment_container).navigate(R.id.manualFragment);
                return true;
            } else {
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NotificationHelper.sendNotification(this, "До свидания!", "Вы вышли из приложения Perfume Shop");
    }
}
