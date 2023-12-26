package iao.tam.movies.movieslist;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import iao.tam.movies.MoviesApplication;
import iao.tam.movies.databinding.ActivityMoviesBinding;
import iao.tam.movies.di.ApplicationComponent;
import org.app.metier.domain.dto.Movie;
import iao.tam.movies.moviedetails.MovieDetailsActivity;
import iao.tam.movies.movieslist.adapter.MoviesAdapter;
import iao.tam.movies.movieslist.di.DaggerMoviesComponent;
import iao.tam.movies.movieslist.di.MoviesPresenterModule;

public class MoviesListActivity extends AppCompatActivity implements MoviesListView {

    private MoviesAdapter moviesAdapter;

    private ActivityMoviesBinding binding;

    @Inject
    MoviesListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectPresenter();
        setupView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    private void setupView() {
        binding = ActivityMoviesBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        setupRecyclerViewMovies();
    }

    private void setupRecyclerViewMovies() {
        moviesAdapter = new MoviesAdapter(presenter::onMovieClick);
        binding.recyclerViewMovies.setAdapter(moviesAdapter);
    }

    private void injectPresenter() {
        ApplicationComponent applicationComponent = ((MoviesApplication) getApplication()).getApplicationComponent();

        DaggerMoviesComponent.builder()
                .applicationComponent(applicationComponent)
                .moviesPresenterModule(new MoviesPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void renderMoviesList(List<Movie> moviesList) {
        moviesAdapter.updateAdapterData(moviesList);
    }

    @Override
    public void startMovieDetailsActivity(Integer movieId) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE_ID, movieId);
        startActivity(intent);
    }

    @Override
    public void cleanMoviesList() {
        moviesAdapter.clearItems();
    }
}
