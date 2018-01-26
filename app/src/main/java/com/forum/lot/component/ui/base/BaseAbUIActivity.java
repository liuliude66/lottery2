package com.forum.lot.component.ui.base;

import android.view.View;

/**
 * 第二层常用的基本操作.
 */

public abstract class BaseAbUIActivity extends BaseActivity {

    protected abstract void initView();

    protected BaseClickListener mBaseClickListener = new BaseClickListener();

    private final class BaseClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){

            }
        }
    }
}
