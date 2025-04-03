package com.example.myapplication.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.DataBase.DBHandler;
import com.example.myapplication.Model.PostModel;
import com.example.myapplication.Adapter.PostAdapter;
import com.example.myapplication.R;

import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvPosts;
    private DBHandler dbHandler;
    private List<PostModel> postList;
    private PostAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        rvPosts = view.findViewById(R.id.rvPosts);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        dbHandler = new DBHandler(getContext());
        postList = dbHandler.getAllPosts(); // bạn cần đã có hàm này trong DBHandler
        adapter = new PostAdapter(getContext(), postList);
        rvPosts.setAdapter(adapter);

        return view;
    }
}
