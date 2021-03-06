package com.coolweather.smtq.girl;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.coolweather.smtq.R;
import com.coolweather.smtq.api.ApiFactory;
import com.coolweather.smtq.api.BaseGankResponse;
import com.coolweather.smtq.base.BaseGirlsListFragment;
import com.coolweather.smtq.model.Gank;
import com.coolweather.smtq.model.Girl;
import com.coolweather.smtq.service.GirlService;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liyu on 2016/10/31.
 */

public class GankFragment extends BaseGirlsListFragment {

    @Override
    protected void getGirlFromServer() {
        showRefreshing(true);
        subscription = ApiFactory
                .getGirlsController()
                .getGank(String.valueOf(currentPage))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseGankResponse<List<Gank>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        isLoading = false;
                        showRefreshing(false);
                        Snackbar.make(getView(), "获取Gank妹纸失败!", Snackbar.LENGTH_INDEFINITE).setAction("重试", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getGirlFromServer();
                            }
                        }).setActionTextColor(getActivity().getResources().getColor(R.color.actionColor)).show();
                    }

                    @Override
                    public void onNext(BaseGankResponse<List<Gank>> response) {
                        currentPage++;
                        List<Girl> girls = new ArrayList<>();
                        for (Gank gank : response.results) {
                            girls.add(new Girl(gank.getUrl()));
                        }
                        sendCount = girls.size();
                        receivedCount = 0;
                        GirlService.start(getActivity(), GankFragment.this.getClass().getName(), girls);
                    }
                });
    }
}
