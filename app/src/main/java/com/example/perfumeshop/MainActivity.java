package com.example.perfumeshop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
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
//            } else if (item.getItemId() == R.id.authorFragment) {
//                Navigation.findNavController(this, R.id.fragment_container)
//                        .navigate(R.id.authorFragment);
//                return true;
            } else {
                return false;
            }
        });
    }
}