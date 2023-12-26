package org.app.metier.domain.service;

import org.app.metier.domain.dto.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Single;


public interface MoviesService {
    Single<List<Movie>> listUpcomingMovies();

    Single<Movie> getMovieDetails(Integer movieId);

    Single<List<Movie>> listSimilarMovies(Integer movieId);
}
