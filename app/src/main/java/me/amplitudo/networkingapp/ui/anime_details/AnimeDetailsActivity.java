package me.amplitudo.networkingapp.ui.anime_details;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import me.amplitudo.networkingapp.ApiService;
import me.amplitudo.networkingapp.R;
import me.amplitudo.networkingapp.models.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static me.amplitudo.networkingapp.ApiClient.apiService;

public class AnimeDetailsActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mDescription;
    private TextView mDuration;
    private TextView mPremiered;
    private TextView mStatus;
    private ImageView mThumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_details);

        findViewsById();
        setContent(getIntent().getExtras().getInt("mal_id"));
    }

    private void setContent(int malId) {
        apiService.getAnimeResult(malId).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AnimeDetailsActivity.this,
                            "Server Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                Result res = response.body();
                Glide.with(AnimeDetailsActivity.this)
                        .load(res.getImageUrl())
                        .into(mThumbnail);
                mTitle.setText(res.getTitle());
                mDuration.setText(res.getDuration());
                mStatus.setText(res.getStatus());
                mDescription.setText(res.getSynopsis());
                mPremiered.setText(res.getPremiered());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(AnimeDetailsActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void findViewsById() {
        mThumbnail = findViewById(R.id.anime_detail_thumbnail);
        mTitle = findViewById(R.id.anime_detail_title);
        mDescription = findViewById(R.id.anime_detail_description);
        mDuration = findViewById(R.id.anime_detail_duration);
        mPremiered = findViewById(R.id.anime_detail_premiered);
        mStatus = findViewById(R.id.anime_detail_status);
    }
}
