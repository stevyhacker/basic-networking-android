package me.amplitudo.networkingapp;

import me.amplitudo.networkingapp.models.City;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("v2/5de0153c3500008865480b25")
    Call<City> getCity();


}
