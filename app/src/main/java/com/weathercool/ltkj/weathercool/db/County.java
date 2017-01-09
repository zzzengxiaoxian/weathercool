package com.weathercool.ltkj.weathercool.db;

import org.litepal.crud.DataSupport;

/**
 * Created by dell on 2017/1/4.
 */


public class County extends DataSupport {

    private int id;

    private String countyName;//县名字

    private String weatherId;//县对应天气ID

    private int cityId;//当前县所属城市ID

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

}
