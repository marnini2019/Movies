package iao.tam.movies.moviedetails;

import java.util.List;

import org.app.metier.domain.dto.Movie;

public interface MovieDetailsView {
    void renderMovieDetails(Movie movie);

    void hideSimilarMoviesPanel();

    void renderSimilarMovies(List<Movie> movies);

    void displaySimilarMoviesPanel();

    void startMovieDetailsActivity(Integer id);
}
