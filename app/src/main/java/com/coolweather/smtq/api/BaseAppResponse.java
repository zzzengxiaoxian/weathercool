package com.coolweather.smtq.api;

/**
 * Created by liyu on 2016/10/31.
 */

public class BaseAppResponse<T> {
    public boolean error;
    public T results;
}
