package me.amplitudo.networkingapp;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static OkHttpClient.Builder httpClient = new OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v3/")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(new Gson()));

    private static Retrofit retrofit = builder.build();

    public static ApiService apiService = retrofit.create(ApiService.class);

}
