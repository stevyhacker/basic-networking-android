package me.amplitudo.networkingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import me.amplitudo.networkingapp.models.Character;
import me.amplitudo.networkingapp.ui.anime_details.AnimeDetailsActivity;

public class MangaCharactersAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private ArrayList<Character> animeResults;

    public MangaCharactersAdapter(Context context, ArrayList<Character> animeResults) {
        this.context = context;
        this.animeResults = animeResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.anime_item, parent, false);
        return new ResultViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Character character = animeResults.get(position);

        ResultViewHolder viewHolder = (ResultViewHolder) holder;

        Glide.with(context)
                .load(character.getImageUrl())
                .into(viewHolder.animePosterImg);

        viewHolder.animeTitle.setText(character.getName());
        viewHolder.animeDescription.setText(character.getRole());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnimeDetailsActivity.class);
                intent.putExtra("mal_id", character.getMalId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return animeResults.size();
    }

    static class ResultViewHolder extends ViewHolder {

        TextView animeTitle, animeDescription;
        ImageView animePosterImg;

        ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            animeTitle = itemView.findViewById(R.id.anime_title);
            animeDescription = itemView.findViewById(R.id.anime_description);
            animePosterImg = itemView.findViewById(R.id.anime_poster);
        }
    }

}
