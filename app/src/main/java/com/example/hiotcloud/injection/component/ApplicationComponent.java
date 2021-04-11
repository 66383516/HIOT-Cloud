package com.example.hiotcloud.injection.component;

import android.app.Application;
import android.content.Context;

import com.example.hiotcloud.App;
import com.example.hiotcloud.injection.ApplicationContext;
import com.example.hiotcloud.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App application);

    @ApplicationContext
    Context context();

    Application application();


    @Component.Builder
    interface ApplicationModuleBuilder {
        ApplicationComponent build();

        ApplicationModuleBuilder applicationModule(ApplicationModule applicationModule);
    }
}
