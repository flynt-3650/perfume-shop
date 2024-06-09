package com.example.perfumeshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    private PerfumeAdapter perfumeAdapter;
    private List<Perfume> perfumeList;
    private DatabaseReference databaseReference;

    public ShopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the list
        perfumeList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup RecyclerView
        RecyclerView perfumeRecyclerView = view.findViewById(R.id.perfumeRecyclerView);
        perfumeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        perfumeAdapter = new PerfumeAdapter(getContext(), perfumeList);
        perfumeRecyclerView.setAdapter(perfumeAdapter);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("perfumes");

        // Fetch data from Firebase
        fetchPerfumes();

        // Check if current user is a super user and show button
        if (isSuperUser()) {
            Button addPerfumeButton = view.findViewById(R.id.addPerfumeButton);
            addPerfumeButton.setVisibility(View.VISIBLE);
            addPerfumeButton.setOnClickListener(v -> showAddPerfumeDialog());
        }
    }

    private void fetchPerfumes() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Clear the previous list to avoid duplicates
                perfumeList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Perfume perfume = snapshot.getValue(Perfume.class);
                    perfumeList.add(perfume);
                }
                // Notify the adapter of data changes
                perfumeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    private boolean isSuperUser() {
        String currentUserEmail = getCurrentUserEmail();
        List<String> superUserEmails = MainActivity.getSus(); // Предполагаем, что getSus() возвращает Set<String>
        return superUserEmails.contains(currentUserEmail);
    }

    private String getCurrentUserEmail() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            return currentUser.getEmail();
        } else {
            return null; // or handle the case when the user is not logged in
        }
    }

    private void showAddPerfumeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Добавить парфюм");

        // Inflate and set the layout for the dialog
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_perfume, null);
        builder.setView(dialogView);

        EditText brandInput = dialogView.findViewById(R.id.brandInput);
        EditText descriptionInput = dialogView.findViewById(R.id.descriptionInput);
        EditText genderInput = dialogView.findViewById(R.id.genderInput);
        EditText idInput = dialogView.findViewById(R.id.idInput);
        EditText imageUrlInput = dialogView.findViewById(R.id.imageUrlInput);
        EditText nameInput = dialogView.findViewById(R.id.nameInput);
        EditText priceInput = dialogView.findViewById(R.id.priceInput);
        EditText sizeInput = dialogView.findViewById(R.id.sizeInput);

        builder.setPositiveButton("Добавить", (dialog, which) -> {
            String brand = brandInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String gender = genderInput.getText().toString();
            String id = idInput.getText().toString();
            String imageUrl = imageUrlInput.getText().toString();
            String name = nameInput.getText().toString();
            double price = Double.parseDouble(priceInput.getText().toString());
            int size = Integer.parseInt(sizeInput.getText().toString());

            Perfume newPerfume = new Perfume(id, name, brand, gender, size, price, description, imageUrl, false);
            addPerfumeToDatabase(newPerfume);
        });

        builder.setNegativeButton("Отмена", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void addPerfumeToDatabase(Perfume perfume) {
        databaseReference.child(perfume.getId()).setValue(perfume)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Notify the user that the perfume was added
                        Toast.makeText(getContext(), "Парфюм добавлен", Toast.LENGTH_SHORT).show();
                    } else {
                        // Notify the user of an error
                        Toast.makeText(getContext(), "Ошибка добавления парфюма", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
