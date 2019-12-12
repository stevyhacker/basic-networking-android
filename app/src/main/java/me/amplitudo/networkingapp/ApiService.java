package me.amplitudo.networkingapp;

import me.amplitudo.networkingapp.models.Result;
import me.amplitudo.networkingapp.models.SearchResultsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/anime")
    Call<SearchResultsResponse> getSearchResults(@Query("q") String query);

    @GET("anime/{mal_id}")
    Call<Result> getAnimeResult(@Path("mal_id") int malId);

}
