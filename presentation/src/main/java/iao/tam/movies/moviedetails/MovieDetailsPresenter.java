package iao.tam.movies.moviedetails;

import java.util.List;

import javax.inject.Inject;

import org.app.metier.domain.dto.Movie;
import org.app.metier.domain.service.MoviesService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MovieDetailsPresenter {

    private final MovieDetailsView view;

    private final MoviesService moviesService;

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public MovieDetailsPresenter(MovieDetailsView view, MoviesService moviesService) {
        this.view = view;
        this.moviesService = moviesService;
    }

    public void loadMovieDetails(int movieId) {
        Disposable disposable = moviesService.getMovieDetails(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayMovieDetails, this::displayLoadingErrorMessage);

        disposables.add(disposable);

        loadSimilarMovies(movieId);
    }

    private void displayLoadingErrorMessage(Throwable throwable) {
        // TODO
    }

    private void loadSimilarMovies(int movieId) {
        Disposable disposable = moviesService.listSimilarMovies(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displaySimilarMovies, error -> view.hideSimilarMoviesPanel());

        disposables.add(disposable);
    }

    private void displayMovieDetails(Movie movie) {
        view.renderMovieDetails(movie);
    }

    private void displaySimilarMovies(List<Movie> movies) {
        if (!movies.isEmpty()) {
            view.displaySimilarMoviesPanel();
            view.renderSimilarMovies(movies);
        } else {
            view.hideSimilarMoviesPanel();
        }
    }

    public void pause() {
        disposables.clear();
    }

    public void clickedOnSimilarMovie(Movie movie) {
        view.startMovieDetailsActivity(movie.getId());
    }
}
