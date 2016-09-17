package akshay.example.com.mvplistfetch.presenters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import akshay.example.com.mvplistfetch.model.interactors.ItemsAPIInteractor;
import akshay.example.com.mvplistfetch.model.interactors.interactor_interfaces.IMainItemsAPIInteractor;
import akshay.example.com.mvplistfetch.presenters.presenter_interfaces.IOnItemLoadFinishedListener;
import akshay.example.com.mvplistfetch.ui.view_interfaces.IMainItemView;


import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;


/**
 * Created by akshay on 17/09/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({IOnItemLoadFinishedListener.class})
public class ItemsPresenterTest {

    private ItemsPresenter mItemsPresenter;
    private IMainItemsAPIInteractor itemsAPIInteractor;
    private IMainItemView mMainItemView;

    @Before
    public void setUp() throws Exception {
        itemsAPIInteractor = mock(ItemsAPIInteractor.class);
        mItemsPresenter = new ItemsPresenter(itemsAPIInteractor);
        mMainItemView = mock(IMainItemView.class);

    }

    @Test
    public void testShouldScheduleItemsLoadFromItemsInteractor() {

        mItemsPresenter.fetchItems();
        verify(itemsAPIInteractor).loadItemsFromNetwork((IOnItemLoadFinishedListener) anyObject());

    }

    @Test
    public void testShouldLaunchDetailActivityOnItemClick() {

        mItemsPresenter.setItemsInteractor(mMainItemView);
        mItemsPresenter.onItemRowClick(2);
        verify(mMainItemView, times(1)).launchDetailedItemActivity(2);

    }
}
