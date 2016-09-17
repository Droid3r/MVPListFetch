package akshay.example.com.mvplistfetch.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    private List<Items> mItems;
    private Context mContext;
    private View.OnClickListener mListener;

    public ItemsListAdapter(Context context, View.OnClickListener listener) {
        this.mItems = new ArrayList<>();
        this.mContext = context;
        this.mListener = listener;
    }

    public void addItems(List<Items> newItems) {
        mItems.addAll(newItems);
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
        String title = mItems.get(position).getTitle();
        title = title.substring(0, 1).toUpperCase() + title.substring(1);
        String description = mItems.get(position).getDescription();
        description = description.substring(0, 1).toUpperCase() + description.substring(1);

        holder.tvItemTitle.setText(title);
        holder.tvItemDescription.setText(description);
        Picasso.with(mContext)
                .load(mItems.get(position).getImage())
                //.centerInside()
                .resize(60,60)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error)
                .into(holder.iviItemImage);

        holder.llItemRow.setOnClickListener(mListener);
        holder.llItemRow.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.item_title)
        TextView tvItemTitle;
        @InjectView(R.id.item_description)
        TextView tvItemDescription;
        @InjectView(R.id.item_image)
        ImageView iviItemImage;

        @InjectView(R.id.item_row_ll)
        LinearLayout llItemRow;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
