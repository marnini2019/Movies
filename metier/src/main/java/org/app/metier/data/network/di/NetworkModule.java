package org.app.metier.data.network.di;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.app.metier.data.network.deserializer.MoviesResultDeserializer;
import org.app.metier.data.network.interceptors.ApiKeyInterceptor;
import org.app.metier.data.network.api.TheMovieDbApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private final String apiKey;

    private final long CACHE_SIZE = 10 * 1024 * 1024;

    public NetworkModule(String apiKey) {
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient httpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(TheMovieDbApi.BASE_URL)
                .client(httpClient)
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    TheMovieDbApi provideTheMovieDbService(Retrofit retrofit) {
        return retrofit.create(TheMovieDbApi.class);
    }
    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        Cache cache = new Cache(application.getCacheDir(), CACHE_SIZE);
        return cache;
    }

    @Provides
    @Singleton
    ApiKeyInterceptor provideApiKeyInterceptor() {
        return new ApiKeyInterceptor(apiKey);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, ApiKeyInterceptor apiKeyInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.cache(cache);

        builder.interceptors().add(apiKeyInterceptor);

        return builder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(MoviesResultDeserializer.getRetrofitMovieEntitiesListType(), new MoviesResultDeserializer());

        return gsonBuilder.create();
    }


}
