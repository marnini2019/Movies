package org.app.metier.data.network.api;

import java.util.List;

import org.app.metier.data.entities.Movies;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TheMovieDbApi {
    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("movie/upcoming")
    Single<List<Movies>> listUpcomingMovies();

    @GET("movie/{movieId}")
    Single<Movies> getMovieDetails(@Path("movieId") Integer movieId);


    @GET("movie/{movieId}/similar")
    Single<List<Movies>> listSimilarMovies(@Path("movieId") Integer movieId);
}
