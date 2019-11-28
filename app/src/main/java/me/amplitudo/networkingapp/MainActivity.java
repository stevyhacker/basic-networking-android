package me.amplitudo.networkingapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import me.amplitudo.networkingapp.models.City;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static me.amplitudo.networkingapp.ApiClient.apiService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView nameTextView = findViewById(R.id.name);

        apiService.getCity().enqueue(new Callback<City>() {
            @Override
            public void onResponse(@NotNull Call<City> call, @NotNull Response<City> response) {
                if (response.isSuccessful()) {
                    nameTextView.setText(response.body().getName());
                } else {
                    Toast.makeText(MainActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<City> call, @NotNull Throwable t) {
                Log.e("MainActivity", t.getMessage());
            }
        });


    }
}
