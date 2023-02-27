package com.example.agriindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.agriindia.Fragments.APMCFragment;
import com.example.agriindia.Fragments.CartFragment;
import com.example.agriindia.Fragments.HomeFragment;
import com.example.agriindia.Fragments.PostFragment;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.frame_layout);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.baseline_groups_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.baseline_timeline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.baseline_shopping_cart_24));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()){
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
                        fragment = new CartFragment();
                        break;
                }

                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

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
        bottomNavigation.show(1,true);
    }
}