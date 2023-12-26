package iao.tam.movies.movieslist.di;

import iao.tam.movies.movieslist.MoviesListView;
import dagger.Module;
import dagger.Provides;

@Module
public class MoviesPresenterModule {
    private final MoviesListView view;

    public MoviesPresenterModule(MoviesListView view) {
        this.view = view;
    }

    @Provides
    MoviesListView providesMoviesView() {
        return view;
    }
}
