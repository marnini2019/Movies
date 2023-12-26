package iao.tam.movies.movieslist.di;

import iao.tam.movies.di.ApplicationComponent;
import iao.tam.movies.di.scopes.FragmentScope;
import iao.tam.movies.movieslist.MoviesListActivity;
import dagger.Component;

@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = MoviesPresenterModule.class)
public interface MoviesComponent {
    void inject(MoviesListActivity activity);
}
