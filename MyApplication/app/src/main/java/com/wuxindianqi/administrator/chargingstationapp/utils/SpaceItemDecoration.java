package com.wuxindianqi.administrator.chargingstationapp.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by checheng on 2017/12/18.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    //recycler设置间隔

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = space;
        outRect.left = space;
        outRect.right = space;
    }
}
