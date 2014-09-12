package krazyrobot.com.babycountdown;

import android.app.Application;

import dagger.ObjectGraph;

public class BabyApplication extends Application {
    private static BabyApplication sInstance;
    private ObjectGraph mGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mGraph = ObjectGraph.create(new AppModule(this));
    }
    public static BabyApplication getInstance() {
        return sInstance;
    }

    public static <T> T inject(T instance) {
        return getInstance().mGraph.inject(instance);
    }
}
