package akshay.example.com.mvplistfetch.model.interactors;

import java.util.List;

import javax.inject.Inject;

import akshay.example.com.mvplistfetch.model.interactors.interactor_interfaces.IMainItemsAPIInteractor;
import akshay.example.com.mvplistfetch.model.pojo.Items;
import akshay.example.com.mvplistfetch.presenters.presenter_interfaces.IOnItemLoadFinishedListener;
import akshay.example.com.mvplistfetch.rest.RetrofitClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by akshay on 16/09/16.
 */
public class ItemsAPIInteractor implements IMainItemsAPIInteractor{

    private RetrofitClient mRetrofitClient;



    @Inject
    public ItemsAPIInteractor(RetrofitClient client) {
        this.mRetrofitClient = client;
    }

    @Override
    public void loadItemsFromNetwork(final IOnItemLoadFinishedListener itemLoadFinishedListener) {
        Call<List<Items>> itemsCall = mRetrofitClient.getJsonItems();
        itemsCall.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Response<List<Items>> response, Retrofit retrofit) {
                List<Items> itemsList = response.body();
                itemLoadFinishedListener.onItemLoadSuccess(itemsList);
            }

            @Override
            public void onFailure(Throwable t) {
                itemLoadFinishedListener.onItemLoadFailure("Could not load the items!");
            }
        });
    }

}
