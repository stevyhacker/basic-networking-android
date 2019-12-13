package me.amplitudo.networkingapp.ui.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import me.amplitudo.networkingapp.AnimeAdapter;
import me.amplitudo.networkingapp.R;
import me.amplitudo.networkingapp.models.Result;
import me.amplitudo.networkingapp.models.SearchResultsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static me.amplitudo.networkingapp.ApiClient.apiService;


public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private AnimeAdapter adapter;
    private ArrayList<Result> animeResults = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel.class);

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final SearchView searchView = root.findViewById(R.id.search_view);
        adapter = new AnimeAdapter(getContext(), animeResults);
        RecyclerView animeList = root.findViewById(R.id.results_recycler_view);
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


        return root;
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
                    Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SearchResultsResponse> call, Throwable t) {

            }
        });
    }
}