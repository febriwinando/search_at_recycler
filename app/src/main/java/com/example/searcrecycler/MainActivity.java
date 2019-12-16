package com.example.searcrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.searcrecycler.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserAdapter userAdapter;
    private List<UserModel> userModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList();
        userRecyClerView();
    }

    public void userList(){
        userModelList = new ArrayList<>();
        userModelList.add(new UserModel(R.drawable.ic_android, "One", "one@gmail.com"));
        userModelList.add(new UserModel(R.drawable.ic_child, "Two", "two@gmail.com"));
        userModelList.add(new UserModel(R.drawable.ic_cloud, "Three", "three@gmail.com"));
        userModelList.add(new UserModel(R.drawable.ic_color, "Four", "four@gmail.com"));
        userModelList.add(new UserModel(R.drawable.ic_favorite, "Five", "five@gmail.com"));
    }

    private void userRecyClerView(){
        RecyclerView recyclerView = findViewById(R.id.rvUser);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        userAdapter = new UserAdapter(userModelList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_menu);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Cari bro");
        searchView.setIconified(false);
        searchView.setIconifiedByDefault(false);
        searchView.setFocusable(false);
        searchView.requestFocusFromTouch();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
