package com.coolweather.android.base;

import android.support.v4.widget.SwipeRefreshLayout;

import com.coolweather.android.R;
import com.coolweather.android.util.ThemeUtil;

/**
 * Created by liyu on 2016/10/31.
 */

public abstract class BaseContentFragment extends BaseFragment {

    protected SwipeRefreshLayout refreshLayout;

    private void initRefreshLayout() {
        refreshLayout = findView(R.id.swipe_container);
        //添加下拉刷新监听器
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                lazyFetchData();
            }
        });
        refreshLayout.setColorSchemeResources(ThemeUtil.getCurrentColorPrimary(getActivity()));//设置进度条的颜色主题，最多设置四种
    }

    @Override
    protected void initViews() {
        initRefreshLayout();
    }

    protected void showRefreshing(final boolean refresh) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refresh);//显示或者隐藏刷新进度条
            }
        });
    }
}
