package com.example.myapplication.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.DataBase.DBHandler;
import com.example.myapplication.R;
import com.example.myapplication.fragments.FavoriteFragment;
import com.example.myapplication.fragments.FeedFragment;
import com.example.myapplication.fragments.HomeFragment;
import com.example.myapplication.fragments.ProfileFragment;
import com.example.myapplication.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(MainActivity.this);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        String dbPath = getDatabasePath("CafeFinderDB").getAbsolutePath();
        Log.d("DB_PATH", "Database Path: " + dbPath);

        // Ẩn thanh trạng thái (Status Bar)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        Fragment homeFragment = new HomeFragment();
        Fragment searchFragment = new SearchFragment();
        Fragment feedFragment = new FeedFragment();
        Fragment favoriteFragment = new FavoriteFragment();
        Fragment profileFragment = new ProfileFragment();

        setCurrentFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                setCurrentFragment(homeFragment);
            } else if (itemId == R.id.profile) {
                setCurrentFragment(profileFragment);
            } else if (itemId == R.id.search) {
                setCurrentFragment(searchFragment);
            }
            else if(itemId == R.id.feed){
                setCurrentFragment(feedFragment);
            }
            else if(itemId == R.id.favorite){
                setCurrentFragment(favoriteFragment);
            }
            return true;
        });
    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flFragment, fragment)
                .commit();
    }

}