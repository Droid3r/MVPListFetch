package akshay.example.com.mvplistfetch.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import akshay.example.com.mvplistfetch.R;
import akshay.example.com.mvplistfetch.model.pojo.Items;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by akshay on 16/09/16.
 */
public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ViewHolder> {

    private List<Items> items;
    private Context mContext;

    public ItemsListAdapter(Context context) {
        this.items = new ArrayList<>();
        this.mContext = context;
    }

    public void addItems(List<Items> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTitle.setText(items.get(position).getTitle());
        holder.itemDescription.setText(items.get(position).getDescription());
        Picasso.with(mContext)
                .load(items.get(position).getImage())
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error)
                .into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.item_title)
        TextView itemTitle;
        @InjectView(R.id.item_description)
        TextView itemDescription;
        @InjectView(R.id.item_image)
        ImageView itemImage;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
