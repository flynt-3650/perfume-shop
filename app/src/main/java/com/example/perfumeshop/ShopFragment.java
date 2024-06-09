package com.example.perfumeshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {

    private RecyclerView perfumeRecyclerView;
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
        perfumeRecyclerView = view.findViewById(R.id.perfumeRecyclerView);
        perfumeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        perfumeAdapter = new PerfumeAdapter(getContext(), perfumeList);
        perfumeRecyclerView.setAdapter(perfumeAdapter);

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("perfumes");

        // Fetch data from Firebase
        fetchPerfumes();
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
}
