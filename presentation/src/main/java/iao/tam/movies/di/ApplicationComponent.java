package iao.tam.movies.di;

import javax.inject.Singleton;

import org.app.metier.data.network.di.NetworkModule;
import org.app.metier.data.repository.di.RepositoryModule;
import org.app.metier.domain.service.MoviesService;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent {
    MoviesService moviesRepository();
}
