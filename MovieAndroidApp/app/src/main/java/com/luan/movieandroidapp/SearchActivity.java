package com.luan.movieandroidapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Luan on 3/4/17.
 */

public class SearchActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<String> movies;

    JSONArray jsonArray = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.listView = (ListView) findViewById(R.id.listView);
        this.movies = new ArrayList<>();

        movies.add("one");
        movies.add("two");
        movies.add("three");
        movies.add("four");
        movies.add("five");
        movies.add("six");
        movies.add("seven");
        movies.add("eight");
        movies.add("nine");
        movies.add("ten");


        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, movies);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String text = listView.getItemAtPosition(position).toString();
            Toast.makeText(SearchActivity.this, "" + text, Toast.LENGTH_SHORT).show();

        });
    }

    private class getForMoviesFromServlet extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String newText = params[0];
            ClientServerConnector clientServerConnector = new ClientServerConnector();
            Map<String, String> map = new HashMap<>();
            map.put("title", newText);
            jsonArray = clientServerConnector.getJsonArray("FuzzySearch", map);
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<String> tempList = new ArrayList<>();

                new getForMoviesFromServlet().execute(newText);
                for (int i = 0; i < jsonArray.length(); i++) {
                    tempList.add(jsonArray.optString(i));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_list_item_1, tempList);
                listView.setAdapter(adapter);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
