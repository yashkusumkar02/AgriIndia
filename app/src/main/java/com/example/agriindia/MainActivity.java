package com.example.agriindia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
//import android.widget.Toolbar;


import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.agriindia.Fragments.APMCFragment;
import com.example.agriindia.Fragments.CartFragment;
import com.example.agriindia.Fragments.HomeFragment;
import com.example.agriindia.Fragments.OrderFragment;
import com.example.agriindia.Fragments.OtherFragment;
import com.example.agriindia.Fragments.PostFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    String username;
    MeowBottomNavigation bottomNavigation;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.white));
        //Bottom Navigation Drawer
        username = getIntent().getStringExtra("username");

        frameLayout = findViewById(R.id.frame_layout);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_groups_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_timeline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.baseline_menu_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new PostFragment();
                        break;
                    case 3:
                        fragment = new APMCFragment();
                        break;
                    case 4:
                        fragment = new OrderFragment();
                        break;
                    case 5:
                        fragment = new OtherFragment();
                        break;
                }

                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();

                bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
                    @Override
                    public void onClickItem(MeowBottomNavigation.Model item) {

                    }
                });

                bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
                    @Override
                    public void onReselectItem(MeowBottomNavigation.Model item) {

                    }
                });
            }
        });
        bottomNavigation.show(1, true);


        //Side Navigation Drawer

//        drawerLayout = findViewById(R.id.drawerlayout);
//        navigationView = findViewById(R.id.navigationview);
//        toolbar = findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
//
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
    }

}