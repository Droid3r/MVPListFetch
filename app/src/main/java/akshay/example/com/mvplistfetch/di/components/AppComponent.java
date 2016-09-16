package akshay.example.com.mvplistfetch.di.components;

import javax.inject.Singleton;

import akshay.example.com.mvplistfetch.di.modules.AppModule;
import akshay.example.com.mvplistfetch.rest.RetrofitClient;
import akshay.example.com.mvplistfetch.ui.activities.ItemsActivity;
import dagger.Component;

/**
 * Created by akshay on 17/09/16.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject (ItemsActivity itemsActivity);
    RetrofitClient client();
}
