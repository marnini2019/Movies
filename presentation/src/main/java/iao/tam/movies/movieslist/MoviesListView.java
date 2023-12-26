package iao.tam.movies.movieslist;

import java.util.List;

import org.app.metier.domain.dto.Movie;

public interface MoviesListView {
    void renderMoviesList(List<Movie> moviesList);

    void startMovieDetailsActivity(Integer id);

    void cleanMoviesList();
}
