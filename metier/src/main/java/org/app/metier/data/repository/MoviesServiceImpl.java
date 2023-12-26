package org.app.metier.data.repository;

import android.os.Build;

import org.app.metier.domain.dto.Movie;
import org.app.metier.domain.service.MoviesService;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.app.metier.data.entities.Movies;
import org.app.metier.data.network.api.TheMovieDbApi;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MoviesServiceImpl implements MoviesService {
    private final TheMovieDbApi theMovieDbService;

    @Inject
    public MoviesServiceImpl(TheMovieDbApi theMovieDbService) {
        this.theMovieDbService = theMovieDbService;
    }
    @Override
    public Single<Movie> getMovieDetails(Integer movieId) {
        return theMovieDbService.getMovieDetails(movieId)
                .map(movies -> new RetrofitMovieEntityToMovieMapper().apply(movies))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<Movie>> listUpcomingMovies() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return theMovieDbService.listUpcomingMovies()
                    .map(retrofitMovieEntities -> retrofitMovieEntities
                            .stream().map(new RetrofitMovieEntityToMovieMapper())
                            .collect(Collectors.toList()))
                    .subscribeOn(Schedulers.io());
        }
        return null;
    }
    @Override
    public Single<List<Movie>> listSimilarMovies(Integer movieId) {
        return theMovieDbService.listSimilarMovies(movieId)
                .map(retrofitMovieEntities -> retrofitMovieEntities.stream().map(new RetrofitMovieEntityToMovieMapper()).collect(Collectors.toList()))
                .subscribeOn(Schedulers.io());
    }

    private class RetrofitMovieEntityToMovieMapper implements java.util.function.Function<Movies, Movie> {
        private final String IMAGE_TMDB_BASE_URL = "https://image.tmdb.org/t/p/w500";

        private final Double MAX_VOTE_AVERAGE = 10.0;

        @Override
        public Movie apply(Movies movies) {
            return new Movie.Builder(movies.getId(), movies.getTitle())
                    .setPosterPath(IMAGE_TMDB_BASE_URL + movies.getPosterPath())
                    .setOverview(movies.getOverview())
                    .setReleaseDate(movies.getReleaseDate())
                    .setVoteAverage(movies.getVoteAverage() / MAX_VOTE_AVERAGE)
                    .build();
        }
    }
}
