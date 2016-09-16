package akshay.example.com.mvplistfetch.di.modules;

import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import akshay.example.com.mvplistfetch.model.interactors.ItemsAPIInteractor;
import akshay.example.com.mvplistfetch.model.interactors.interactor_interfaces.IMainItemsAPIInteractor;
import akshay.example.com.mvplistfetch.rest.RetrofitClient;
import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by akshay on 17/09/16.
 */
@Module
public class AppModule {


    private final String TAG = AppModule.class.getName();

    //TODO move this to constants
    private final String JSON_FETCH_URL = "https://gist.githubusercontent.com/maclir/" +
            "f715d78b49c3b4b3b77f/raw/8854ab2fe4cbe2a5919cea97d71b714ae5a4838d/items.json";

    public AppModule() {

    }

    @Singleton
    @Provides
    RetrofitClient provideRetrofitClient(Retrofit retrofit) {
        RetrofitClient retrofitClient;
        retrofitClient = retrofit.create(RetrofitClient.class);
        return retrofitClient;
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit(OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(JSON_FETCH_URL)
                .build();
        return retrofit;
    }

    @Singleton
    @Provides
    OkHttpClient providesOkHttpClient() {
        OkHttpClient client = new OkHttpClient();
        return client;
    }

    @Singleton
    @Provides
    IMainItemsAPIInteractor providesMainItemAPIInteractor(RetrofitClient client) {
        return new ItemsAPIInteractor(client);
    }

}