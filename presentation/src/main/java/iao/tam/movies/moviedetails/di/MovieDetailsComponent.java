package iao.tam.movies.moviedetails.di;

import iao.tam.movies.di.ApplicationComponent;
import iao.tam.movies.di.scopes.FragmentScope;
import iao.tam.movies.moviedetails.MovieDetailsActivity;
import dagger.Component;

@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = MovieDetailsPresenterModule.class)
public interface MovieDetailsComponent {
    void inject(MovieDetailsActivity activity);
}
