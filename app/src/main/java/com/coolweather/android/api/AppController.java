package com.coolweather.android.api;


import com.coolweather.android.model.UpdateInfo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by liyu on 2016/12/1.
 */

public interface AppController {

    @GET("https://raw.githubusercontent.com/zzzengxiaoxian/WeatherMore/master/appupdate.json")
    Observable<BaseAppResponse<UpdateInfo>> checkUpdate();
}
