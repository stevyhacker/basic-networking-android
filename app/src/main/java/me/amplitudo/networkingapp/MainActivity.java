package me.amplitudo.networkingapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.amplitudo.networkingapp.models.Result;
import me.amplitudo.networkingapp.models.SearchResultsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static me.amplitudo.networkingapp.ApiClient.apiService;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Result> animeResults = new ArrayList<>();
    AnimeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SearchView searchView = findViewById(R.id.search_view);
        adapter = new AnimeAdapter(this, animeResults);
        RecyclerView animeList = findViewById(R.id.results_recycler_view);
        animeList.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchResults(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

    }

    private void fetchResults(String query) {
        apiService.getSearchResults(query).enqueue(new Callback<SearchResultsResponse>() {
            @Override
            public void onResponse(Call<SearchResultsResponse> call, Response<SearchResultsResponse> response) {
                if (response.isSuccessful()) {
                    animeResults.clear();
                    animeResults.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
//                    Toast.makeText(
//                            MainActivity.this,
//                            "Results: " + response.body().getResults().size(),
//                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Server error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SearchResultsResponse> call, Throwable t) {

            }
        });
    }
}
