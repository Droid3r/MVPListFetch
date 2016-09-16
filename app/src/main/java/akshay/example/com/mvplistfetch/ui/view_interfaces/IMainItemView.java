package akshay.example.com.mvplistfetch.ui.view_interfaces;

import java.util.List;

import akshay.example.com.mvplistfetch.model.pojo.Items;

/**
 * Created by akshay on 16/09/16.
 */
public interface IMainItemView {

    void onItemsFetchedSuccess(List<Items> itemList);
    void onItemsFetchedError(String message);
}
