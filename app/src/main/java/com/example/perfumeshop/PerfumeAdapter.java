package com.example.perfumeshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PerfumeAdapter extends RecyclerView.Adapter<PerfumeViewHolder> {
    private Context context;
    private List<Perfume> perfumeList;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("perfumes");

    // Constructor with DatabaseReference parameter
    public PerfumeAdapter(Context context, List<Perfume> perfumeList) {
        this.context = context;
        this.perfumeList = perfumeList;
    }

    @NonNull
    @Override
    public PerfumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_perfume, parent, false);
        return new PerfumeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfumeViewHolder holder, int position) {
        Perfume perfume = perfumeList.get(position);

        holder.nameTextView.setText(perfume.getName());
        holder.brandTextView.setText(perfume.getBrand());
        holder.genderTextView.setText(perfume.getGender());
        holder.sizeTextView.setText(context.getString(R.string.size_format, perfume.getSize())); // Assuming size is in ml
        holder.priceTextView.setText(context.getString(R.string.price_format, perfume.getPrice()));
        holder.descriptionTextView.setText(perfume.getDescription());

        Picasso.get().load(perfume.getImageUrl()).placeholder(R.drawable.default_perfume_image).into(holder.imageView);

        holder.addToCartButton.setOnClickListener(v -> {
            boolean added = !perfume.isAdded();
            perfume.setAdded(added);
            String message = added ? "Added to cart" : "Removed from cart";
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            holder.addToCartButton.setText(added ? "Remove" : "Add");

            // Update the 'added' attribute in the Firebase Realtime Database
            databaseReference.child(perfume.getId()).child("added").setValue(added);
        });
    }

    @Override
    public int getItemCount() {
        return perfumeList.size();
    }
}
