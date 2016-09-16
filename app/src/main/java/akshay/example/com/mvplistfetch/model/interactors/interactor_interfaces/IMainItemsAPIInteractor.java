package akshay.example.com.mvplistfetch.model.interactors.interactor_interfaces;

import akshay.example.com.mvplistfetch.presenters.presenter_interfaces.IOnItemLoadFinishedListener;

/**
 * Created by akshay on 17/09/16.
 */
public interface IMainItemsAPIInteractor {

    void loadItemsFromNetwork(final IOnItemLoadFinishedListener itemLoadFinishedListener);
}
