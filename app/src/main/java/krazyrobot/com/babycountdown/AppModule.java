package krazyrobot.com.babycountdown;

import android.app.Application;
import android.content.Context;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Module(injects = {AppPreferences.class, HomeActivity.class})
public class AppModule {
    private final Application mApp;

    public AppModule(Application app) {
        mApp = app;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return mApp;
    }

    @Qualifier
    @Retention(RUNTIME)
    public @interface ForApplication {

    }
}
