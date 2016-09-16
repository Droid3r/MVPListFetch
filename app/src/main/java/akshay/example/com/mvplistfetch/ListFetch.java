package akshay.example.com.mvplistfetch;

import android.app.Application;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import akshay.example.com.mvplistfetch.di.components.AppComponent;
import akshay.example.com.mvplistfetch.di.components.DaggerAppComponent;
import akshay.example.com.mvplistfetch.di.modules.AppModule;

/**
 * Created by akshay on 16/09/16.
 */
public class ListFetch extends android.app.Application {

    private AppComponent mAppComponent = createAppComponent();

    @Override
    public void onCreate() {
        super.onCreate();

        //flags to check image caching in debug mode
        //TODO comment this in production
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        //built.setIndicatorsEnabled(true);
        //built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }


    protected AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
