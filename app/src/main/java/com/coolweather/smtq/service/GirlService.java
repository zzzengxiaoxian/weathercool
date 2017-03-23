package com.coolweather.smtq.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.coolweather.smtq.event.GirlsComingEvent;
import com.coolweather.smtq.model.Girl;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyu on 2016/11/9.
 */

public class GirlService extends IntentService {

    private static final String KEY_EXTRA_GIRL_FROM = "from";
    private static final String KEY_EXTRA_GIRL_LIST = "data";

    public GirlService() {
        super("GirlService");
    }

    public static void start(Context context, String from, List<Girl> list) {
        Intent intent = new Intent(context, GirlService.class);
        intent.putExtra(KEY_EXTRA_GIRL_FROM, from);
        intent.putExtra(KEY_EXTRA_GIRL_LIST, (Serializable) list);
        context.startService(intent);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, GirlService.class));
    }

    /**
     * 该函数用于针对Intent的不同进行不同的事务处理就可以了.执行完所一个Intent请求对象所对应的工作之后，如果没有新的Intent请求达到，
     则自动停止Service；否则ServiceHandler会取得下一个Intent请求传人该函数来处理其所对应的任务。
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        String from = intent.getStringExtra(KEY_EXTRA_GIRL_FROM);
        List<Girl> girls = (List<Girl>) intent.getSerializableExtra(KEY_EXTRA_GIRL_LIST);
        for (final Girl girl : girls) {
            Bitmap bitmap = null;
            try {
                bitmap = Glide.with(GirlService.this)
                        .load(girl.getUrl())
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                girl.setHeight(bitmap.getHeight());
                girl.setWidth(bitmap.getWidth());
            }
            EventBus.getDefault().post(new GirlsComingEvent(from, girl));
        }
    }
}
