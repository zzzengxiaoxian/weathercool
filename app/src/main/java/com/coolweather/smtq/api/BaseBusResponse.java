package com.coolweather.smtq.api;

/**
 * Created by liyu on 2016/10/31.
 */

public class BaseBusResponse<T> {
    public int errorCode;
    public T data;
    public String errorMsg;
}
