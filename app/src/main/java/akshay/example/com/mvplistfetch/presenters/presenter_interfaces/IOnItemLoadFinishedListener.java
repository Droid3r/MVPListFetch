package akshay.example.com.mvplistfetch.presenters.presenter_interfaces;

import java.util.List;

import akshay.example.com.mvplistfetch.model.pojo.Items;

/**
 * Created by akshay on 17/09/16.
 */
public interface IOnItemLoadFinishedListener {

    void onItemLoadSuccess(List<Items> itemsList);
    void onItemLoadFailure(String message);
}
