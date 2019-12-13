package me.amplitudo.networkingapp.ui.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import me.amplitudo.networkingapp.models.Character;
import me.amplitudo.networkingapp.models.MangaCharactersResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static me.amplitudo.networkingapp.ApiClient.apiService;

public class MangaViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<Character>> mangaResults = new MutableLiveData<>();


    public MangaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Character>> getResults() {
        return mangaResults;
    }

    public void fetchMangaResults() {

        apiService.getMangaResult(1).enqueue(new Callback<MangaCharactersResponse>() {
            @Override
            public void onResponse(@NotNull Call<MangaCharactersResponse> call, @NotNull Response<MangaCharactersResponse> response) {
                if (response.isSuccessful()) {
                    mangaResults.setValue(response.body().getCharacters());
                }

            }

            @Override
            public void onFailure(@NotNull Call<MangaCharactersResponse> call, @NotNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

}