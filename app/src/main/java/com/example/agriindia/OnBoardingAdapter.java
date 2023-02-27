package com.example.agriindia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnboardingViewHolder> {

    private List<OnBaordingItem> onBaordingItems;

    public OnBoardingAdapter(List<OnBaordingItem> onBaordingItems) {
        this.onBaordingItems = onBaordingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding, parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnboardingData(onBaordingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBaordingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle;
        private TextView textDescription;
        private LottieAnimationView lottieAnimationView;

         OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle =itemView.findViewById(R.id.textTitleOnboarding);
            textDescription=itemView.findViewById(R.id.textDescOnboarding);
            lottieAnimationView=itemView.findViewById(R.id.imgOnboardingImages);
        }
        void setOnboardingData(OnBaordingItem onBaordingItem){
             textTitle.setText(onBaordingItem.getTitle());
             textDescription.setText(onBaordingItem.getDescription());
             lottieAnimationView.setAnimation(onBaordingItem.getImage());
        }
    }
}
