package com.coolweather.smtq.api;


import java.util.List;

import com.coolweather.smtq.model.JiandanXXOO;

/**
 * Created by liyu on 2016/10/31.
 */

public class BaseJiandanResponse {
    public String status;
    public int current_page;
    public int total_comments;
    public int page_count;
    public int count;
    public List<JiandanXXOO> comments;
}
