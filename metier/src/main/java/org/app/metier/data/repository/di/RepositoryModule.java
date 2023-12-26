package org.app.metier.data.repository.di;

import javax.inject.Singleton;

import org.app.metier.data.repository.MoviesServiceImpl;
import org.app.metier.domain.service.MoviesService;
import org.app.metier.data.network.api.TheMovieDbApi;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    MoviesService provideMoviesRepository(TheMovieDbApi theMovieDbService) {
        return new MoviesServiceImpl(theMovieDbService);
    }
}
