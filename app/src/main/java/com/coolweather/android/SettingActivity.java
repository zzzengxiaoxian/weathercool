package com.coolweather.android;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.jrummyapps.android.widget.AnimatedSvgView;

/**
 * Created by dell on 2017/2/3.
 */
public class SettingActivity extends AppCompatActivity {

    private AnimatedSvgView svgView;
    private TextView tv_app;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //5.0以上系统才可以支持
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_setting);

        svgView = (AnimatedSvgView) findViewById(R.id.animated_svg_view);

        svgView.postDelayed(new Runnable() {

            @Override
            public void run() {
                svgView.start();
            }
        }, 500);

        svgView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (svgView.getState() == AnimatedSvgView.STATE_FINISHED) {
                    svgView.start();
                }
            }
        });

        tv_app = (TextView) findViewById(R.id.tv_app);
        tv_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri CONTENT_URI_BROWSERS = Uri.parse("https://github.com/zzzengxiaoxian/weathercool");
                intent.setData(CONTENT_URI_BROWSERS);
                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(intent);
            }
        });

        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation("PinJump.json");
        animationView.loop(true);


        LottieAnimationView animationView1 = (LottieAnimationView) findViewById(R.id.animation_view1);
        animationView1.setAnimation("TwitterHeart.json");
        animationView1.loop(true);


    }

//
//    private void setSvg(SVG svg) {
//        svgView.setGlyphStrings(svg.glyphs);
//        svgView.setFillColors(svg.colors);
//        svgView.setViewportSize(svg.width, svg.height);
//        svgView.setTraceResidueColor(0x32000000);
//        svgView.setTraceColors(svg.colors);
//        svgView.rebuildGlyphData();
//        svgView.start();
//    }

}
