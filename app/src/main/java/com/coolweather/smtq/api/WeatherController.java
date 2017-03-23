package com.coolweather.smtq.api;



import com.coolweather.smtq.model.HeWeather5;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 天气查询接口
 * Created by liyu on 2016/10/31.
 */

public interface WeatherController {

    @GET("https://free-api.heweather.com/v5/weather?key=aadb0a3ea1624fb586894e4544ae22ff")
    Observable<BaseWeatherResponse<HeWeather5>> getWeather(@Query("city") String city);
}
