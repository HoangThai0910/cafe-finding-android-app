package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Adapter.CafeAdapter;
import com.example.myapplication.Model.CafeModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    private CafeAdapter cafeAdapter;
    private List<CafeModel> cafeList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.search_fragment, container, false);

        // Tìm RecyclerView từ view đã inflate
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Khởi tạo danh sách quán cafe
        cafeList = new ArrayList<>();
        cafeList.add(new CafeModel("Gongcha", "Học viện Công nghệ Bưu chính Viễn thông", true, 12.3));
//        cafeList.add(new CafeModel("P-Coffee", "Học viện Công nghệ Bưu chính Viễn thông", true));
//        cafeList.add(new CafeModel("Ami Coffee", "Học viện Công nghệ Bưu chính Viễn thông", false));
//        cafeList.add(new CafeModel("Forest Coffee", "Học viện Công nghệ Bưu chính Viễn thông", false));

        // Gán adapter cho RecyclerView
        cafeAdapter = new CafeAdapter(cafeList, requireContext());
        recyclerView.setAdapter(cafeAdapter);
        return view;
    }
}
