package com.example.agriindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.agriindia.LoginSignUp.LoginandSignupPage;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnBoardScreen extends AppCompatActivity {

    private OnBoardingAdapter onBoardingAdapter;
    private LinearLayout layoutOnboardingIndicators;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_screen);

        layoutOnboardingIndicators=findViewById(R.id.layoutOnboardingIndicators);

        buttonOnboardingAction = findViewById(R.id.buttonOnboardingAction);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onBoardingAdapter);

        setupOnboardingIndicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onboardingViewPager.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }else {
                    startActivity(new Intent(getApplicationContext(), LoginandSignupPage.class));
                    finish();
                }
            }
        });


    }

    private void setupOnboardingItems() {

        List<OnBaordingItem> onBaordingItems= new ArrayList<>();

        OnBaordingItem itemSocialMedia = new OnBaordingItem();
        itemSocialMedia.setTitle("Share Knowledge");
        itemSocialMedia.setDescription("Social Media Let's you share Knowledge with \n other framers..!  \n Create your own posts using Image/Video\n/Texts.");
        itemSocialMedia.setImage(R.raw.socialmedia);

        OnBaordingItem itemEcommerce = new OnBaordingItem();
        itemEcommerce.setTitle("E-Commerce");
        itemEcommerce.setDescription("Buy / Sell Agriculture related \nproducts & Manage your Cart Online...!");
        itemEcommerce.setImage(R.raw.ecommerce);

        OnBaordingItem itemWeather = new OnBaordingItem();
        itemWeather.setTitle("Weather Forecast");
        itemWeather.setDescription("Get Notified your Daily Weather Conditions. \n 24*7 Data.");
        itemWeather.setImage(R.raw.weatherforecast);

        OnBaordingItem itemLetsGrowTogether = new OnBaordingItem();
        itemLetsGrowTogether.setTitle("Let's Grow Together");
        itemLetsGrowTogether.setDescription("Agri India- Farming App.");
        itemLetsGrowTogether.setImage(R.raw.letsgrowtogether);

        OnBaordingItem itemReadArticles = new OnBaordingItem();
        itemReadArticles.setTitle("Read Articles");
        itemReadArticles.setDescription("Read Online articles realted to Farming Concepts, \nTechnologies and other useful Knowledge.");
        itemReadArticles.setImage(R.raw.articles);

        OnBaordingItem itemWelcomeApp = new OnBaordingItem();
        itemWelcomeApp.setTitle("Welcome to the \n Agri India- Farming App");
        itemWelcomeApp.setDescription("Best Guide and Helper for any Farmer. \n Provides various features at one Place...!");
        itemWelcomeApp.setImage(R.raw.welometoapp);

        onBaordingItems.add(itemSocialMedia);
        onBaordingItems.add(itemEcommerce);
        onBaordingItems.add(itemWeather);
        onBaordingItems.add(itemLetsGrowTogether);
        onBaordingItems.add(itemReadArticles);
        onBaordingItems.add(itemWelcomeApp);

        onBoardingAdapter = new OnBoardingAdapter(onBaordingItems);

    }

    private void setupOnboardingIndicators() {
        ImageView[] indicators = new  ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0 , 8, 0);
        for (int i=0;i<indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboariding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicators.addView(indicators[i]);

        }
    }

    private void setCurrentOnboardingIndicator(int index) {

        int childCount= layoutOnboardingIndicators.getChildCount();
        for (int i=0; i<childCount; i++) {
            ImageView imageView = (ImageView)layoutOnboardingIndicators.getChildAt(i);
            if(i==index)
            {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboarding_indicator_active)
                );
            }
            else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboariding_indicator_inactive)
                );
            }
        }
        if (index== onBoardingAdapter.getItemCount()-1){
            buttonOnboardingAction.setText("Start");
        }
        else {
            buttonOnboardingAction.setText("Next");
        }

    }

}