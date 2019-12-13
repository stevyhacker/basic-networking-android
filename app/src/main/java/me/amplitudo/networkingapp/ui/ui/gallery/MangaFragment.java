package me.amplitudo.networkingapp.ui.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.amplitudo.networkingapp.MangaCharactersAdapter;
import me.amplitudo.networkingapp.R;
import me.amplitudo.networkingapp.models.Character;

public class MangaFragment extends Fragment {

    private MangaViewModel mangaViewModel;
    ArrayList<Character> results = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mangaViewModel = ViewModelProviders.of(this).get(MangaViewModel.class);

        View root = inflater.inflate(R.layout.fragment_manga, container, false);

        final TextView textView = root.findViewById(R.id.text_gallery);
        RecyclerView mangaRecyclerView = root.findViewById(R.id.results_recycler_view);

        final MangaCharactersAdapter adapter = new MangaCharactersAdapter(getContext(), results);
        mangaRecyclerView.setAdapter(adapter);

        mangaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        mangaViewModel.getResults().observe(this, new Observer<List<Character>>() {
            @Override
            public void onChanged(List<Character> mangaResults) {
                results.clear();
                results.addAll(mangaResults);
                adapter.notifyDataSetChanged();
            }
        });

        mangaViewModel.fetchMangaResults();

        return root;
    }
}