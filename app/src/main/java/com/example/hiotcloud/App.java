package com.example.hiotcloud;

import android.app.Application;

import com.example.hiotcloud.injection.component.ApplicationComponent;
import com.example.hiotcloud.injection.component.DaggerApplicationComponent;
import com.example.hiotcloud.injection.module.ApplicationModule;

/**
 * 所有程序的入口
 */

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }

    public ApplicationComponent component() {
        return component;
    }

}

