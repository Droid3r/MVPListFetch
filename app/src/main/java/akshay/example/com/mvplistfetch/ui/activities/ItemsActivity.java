package akshay.example.com.mvplistfetch.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.internal.Util;

import java.util.List;

import javax.inject.Inject;

import akshay.example.com.mvplistfetch.ListFetch;
import akshay.example.com.mvplistfetch.R;
import akshay.example.com.mvplistfetch.model.pojo.Items;
import akshay.example.com.mvplistfetch.presenters.ItemsPresenter;
import akshay.example.com.mvplistfetch.ui.adapters.ItemsListAdapter;
import akshay.example.com.mvplistfetch.ui.decorators.VerticalSpaceItemDecorator;
import akshay.example.com.mvplistfetch.ui.view_interfaces.IMainItemView;
import akshay.example.com.mvplistfetch.util.AppConstants;
import akshay.example.com.mvplistfetch.util.Utils;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ItemsActivity extends AppCompatActivity implements IMainItemView{

    @Inject
    ItemsPresenter mItemsPresenter;

    @InjectView(R.id.items_recycler_view)
    RecyclerView mItemsRecyclerView;

    @InjectView(R.id.error_view)
    TextView tvErrorView;

    ItemsListAdapter mItemsListAdapter;

    private List<Items> mItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        ButterKnife.inject(this);
        ((ListFetch)getApplication()).getAppComponent().inject(this);


        initRecyclerView();

        mItemsPresenter.setItemsInteractor(this);
        mItemsPresenter.fetchItems();
        Utils.showSimpleProgressDialog(this, getResources().getString(R.string.loading_data)
                , getResources().getString(R.string.loading_msg), false);
    }

    public void initRecyclerView() {

        mItemsRecyclerView.setHasFixedSize(true);
        mItemsRecyclerView.setLayoutManager(new LinearLayoutManager(mItemsRecyclerView.getContext()));
        mItemsRecyclerView.addItemDecoration(
                new VerticalSpaceItemDecorator(AppConstants.VERTICAL_ITEM_SPACE, this));
        mItemsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItemsListAdapter = new ItemsListAdapter(this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int)v.getTag();

                mItemsPresenter.onItemRowClick(position);
            }
        });
        mItemsRecyclerView.setAdapter(mItemsListAdapter);
    }

    @Override
    public void onItemsFetchedSuccess(List<Items> itemList) {
        Utils.removeSimpleProgressDialog();
        hideError();
        mItemsList = itemList;
        mItemsListAdapter.addItems(mItemsList);
    }

    @Override
    public void onItemsFetchedError(String message) {
        //TODO propagate the error message on the screen
        Utils.removeSimpleProgressDialog();
        showError();
    }

    @Override
    public void launchDetailedItemActivity(int position) {

        Items item = mItemsList.get(position);
        String title = item.getTitle();
        String description = item.getDescription();
        String imageUrl = item.getImage();

        Intent detailActivityIntent = new Intent(this, ItemDetailActivity.class);

        detailActivityIntent.putExtra(AppConstants.ITEM_TITLE, title);
        detailActivityIntent.putExtra(AppConstants.ITEM_DESCRIPTION, description);
        detailActivityIntent.putExtra(AppConstants.ITEM_IMAGE_URL, imageUrl);
        startActivity(detailActivityIntent);

    }

    private void hideError() {
        mItemsRecyclerView.setVisibility(View.VISIBLE);
        tvErrorView.setVisibility(View.GONE);
    }

    private void showError() {
        mItemsRecyclerView.setVisibility(View.GONE);
        tvErrorView.setVisibility(View.VISIBLE);
    }
}
