package com.coolweather.smtq.api;

import java.util.List;

import com.coolweather.smtq.model.Gank;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dell on 2017/3/17.
 */
public interface GirlsController {

@GET("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/{page}")
rx.Observable<BaseGankResponse<List<Gank>>> getGank(@Path("page") String page);

    @GET("http://i.jandan.net/?oxwlxojflwblxbsapi=jandan.get_ooxx_comments")
    rx.Observable<BaseJiandanResponse> getXXOO(@Query("page") int page);
}
