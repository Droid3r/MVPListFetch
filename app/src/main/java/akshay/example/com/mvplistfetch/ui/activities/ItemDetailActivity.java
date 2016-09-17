package akshay.example.com.mvplistfetch.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import akshay.example.com.mvplistfetch.R;
import akshay.example.com.mvplistfetch.util.AppConstants;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ItemDetailActivity extends AppCompatActivity {

    @InjectView(R.id.item_title_detail)
    TextView tvItemTitle;
    @InjectView(R.id.item_description_detail)
    TextView tvItemDescription;
    @InjectView(R.id.item_image_detail)
    ImageView ivItemImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        ButterKnife.inject(this);

        //getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {

            String title = intent.getExtras().getString(AppConstants.ITEM_TITLE);
            title = title.substring(0, 1).toUpperCase() + title.substring(1);
            String description = intent.getExtras().getString(AppConstants.ITEM_DESCRIPTION);
            description = description.substring(0, 1).toUpperCase() + description.substring(1);
            tvItemTitle.setText(title);
            tvItemDescription.setText(description);

            //if image is in cache it will load without download
            Picasso.with(this)
                    .load(intent.getExtras().getString(AppConstants.ITEM_IMAGE_URL))
                    .error(R.drawable.error)
                    .into(ivItemImage);
        }

    }
}
