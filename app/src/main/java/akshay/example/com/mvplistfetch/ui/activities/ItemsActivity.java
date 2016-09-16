package akshay.example.com.mvplistfetch.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import akshay.example.com.mvplistfetch.ListFetch;
import akshay.example.com.mvplistfetch.R;
import akshay.example.com.mvplistfetch.model.pojo.Items;
import akshay.example.com.mvplistfetch.presenters.ItemsPresenter;
import akshay.example.com.mvplistfetch.ui.adapters.ItemsListAdapter;
import akshay.example.com.mvplistfetch.ui.view_interfaces.IMainItemView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ItemsActivity extends AppCompatActivity implements IMainItemView{

    @Inject
    ItemsPresenter mItemsPresenter;

    @InjectView(R.id.items_recycler_view)
    RecyclerView mItemsRecyclerView;

    @InjectView(R.id.error_view)
    TextView errorView;

    ItemsListAdapter mItemsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        ButterKnife.inject(this);
        ((ListFetch)getApplication()).getAppComponent().inject(this);


        initRecyclerView();


        mItemsPresenter.fetchItems(this);
    }

    public void initRecyclerView() {

        mItemsRecyclerView.setHasFixedSize(true);
        mItemsRecyclerView.setLayoutManager(new LinearLayoutManager(mItemsRecyclerView.getContext()));
        mItemsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mItemsListAdapter = new ItemsListAdapter(this);
        mItemsRecyclerView.setAdapter(mItemsListAdapter);
    }

    @Override
    public void onItemsFetchedSuccess(List<Items> itemList) {
        hideError();
        mItemsListAdapter.addItems(itemList);
    }

    @Override
    public void onItemsFetchedError(String message) {
        //TODO propagate the error message on the screen
        showError();
    }

    private void hideError() {
        mItemsRecyclerView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    private void showError() {
        mItemsRecyclerView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }
}
