package akshay.example.com.mvplistfetch.rest;

import java.util.List;

import akshay.example.com.mvplistfetch.model.pojo.Items;
import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by akshay on 16/09/16.
 */
public interface RetrofitClient {

    @GET("https://gist.githubusercontent.com/maclir/" +
            "f715d78b49c3b4b3b77f/raw/8854ab2fe4cbe2a5919cea97d71b714ae5a4838d/items.json")
    Call<List<Items>> getJsonItems();
}
