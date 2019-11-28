package me.amplitudo.networkingapp;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    static OkHttpClient.Builder httpClient = new OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

    static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://www.mocky.io/")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(new Gson()));

    static Retrofit retrofit = builder.build();

    public static ApiService apiService = retrofit.create(ApiService.class);

}
