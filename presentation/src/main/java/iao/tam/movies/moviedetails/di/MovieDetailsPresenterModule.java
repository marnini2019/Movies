package iao.tam.movies.moviedetails.di;

import iao.tam.movies.moviedetails.MovieDetailsView;
import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsPresenterModule {
    private final MovieDetailsView view;

    public MovieDetailsPresenterModule(MovieDetailsView view) {
        this.view = view;
    }

    @Provides
    MovieDetailsView provideMovieDetailsView() {
        return view;
    }
}
