package akshay.example.com.mvplistfetch.presenters;

import java.util.List;

import javax.inject.Inject;

import akshay.example.com.mvplistfetch.model.interactors.interactor_interfaces.IMainItemsAPIInteractor;
import akshay.example.com.mvplistfetch.model.pojo.Items;
import akshay.example.com.mvplistfetch.presenters.presenter_interfaces.IOnItemLoadFinishedListener;
import akshay.example.com.mvplistfetch.ui.view_interfaces.IMainItemView;

/**
 * Created by akshay on 17/09/16.
 */
public class ItemsPresenter implements IOnItemLoadFinishedListener {

    private IMainItemsAPIInteractor itemsInteractor;
    private IMainItemView mMainItemView;

    @Inject
    public ItemsPresenter(IMainItemsAPIInteractor interactor) {

        this.itemsInteractor = interactor;

    }

    public void setItemsInteractor(IMainItemView mainItemView) {
        this.mMainItemView = mainItemView;
    }

    public void fetchItems() {
        itemsInteractor.loadItemsFromNetwork(this);
    }

    public void onItemRowClick(int position) {
        mMainItemView.launchDetailedItemActivity(position);
    }

    @Override
    public void onItemLoadSuccess(List<Items> itemsList) {
        mMainItemView.onItemsFetchedSuccess(itemsList);
    }

    @Override
    public void onItemLoadFailure(String message) {
        mMainItemView.onItemsFetchedError(message);
    }
}
