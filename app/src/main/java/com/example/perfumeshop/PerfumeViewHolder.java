package com.example.perfumeshop;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PerfumeViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView nameTextView;
    TextView brandTextView;
    TextView genderTextView;
    TextView sizeTextView;
    TextView priceTextView;
    TextView descriptionTextView;
    Button addToCartButton;

    public PerfumeViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        brandTextView = itemView.findViewById(R.id.brandTextView);
        genderTextView = itemView.findViewById(R.id.genderTextView);
        sizeTextView = itemView.findViewById(R.id.sizeTextView);
        priceTextView = itemView.findViewById(R.id.priceTextView);
        descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        addToCartButton = itemView.findViewById(R.id.addToCartButton);
    }
}
