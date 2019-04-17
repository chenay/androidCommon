package com.chenay.common.desgin.recycleView.stickyitem1;

import android.view.View;

public class ExampleStickyView implements StickyView{
    @Override
    public boolean isStickyView(View view) {
        return (Boolean) view.getTag();
    }

    @Override
    public int getStickViewType() {
        return 11;
    }
}
