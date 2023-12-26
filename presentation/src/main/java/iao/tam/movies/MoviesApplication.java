package iao.tam.movies;


import android.app.Application;

import iao.tam.movies.di.ApplicationComponent;
import iao.tam.movies.di.ApplicationModule;
import iao.tam.movies.di.DaggerApplicationComponent;
import org.app.metier.data.network.di.NetworkModule;
import org.app.metier.data.repository.di.RepositoryModule;

public class MoviesApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        createApplicationComponent();
    }

    private void createApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule("01643e091b663b8a7d9dc90188c60cd0"))
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
