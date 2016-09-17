package akshay.example.com.mvplistfetch.ui.decorators;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import akshay.example.com.mvplistfetch.R;
import akshay.example.com.mvplistfetch.util.AppConstants;
import akshay.example.com.mvplistfetch.util.Utils;

/**
 * Created by akshay on 17/09/16.
 */
public class VerticalSpaceItemDecorator extends RecyclerView.ItemDecoration {

    private final int mVerticalSpaceHeight;
    private Drawable mDivider;
    private Context mContext;

    public VerticalSpaceItemDecorator(int mVerticalSpaceHeight, Context context) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        mContext = context;
        mDivider = ContextCompat.getDrawable(mContext, R.drawable.recycler_item_divider);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        //no space after last element
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = mVerticalSpaceHeight;
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left + (int)Utils.convertDpToPixel(AppConstants.ITEM_DIVIDER_LEFT_MARGIN_DP, mContext), top, right, bottom);
            mDivider.draw(c);
        }
    }
}
