package com.coolweather.smtq.girl.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coolweather.smtq.R;
import com.coolweather.smtq.girl.MzituPictureActivity;
import com.coolweather.smtq.girl.PictureActivity;
import com.coolweather.smtq.model.Girl;
import com.coolweather.smtq.widget.RatioImageView;

import java.util.List;

/**
 * Created by liyu on 2016/12/9.
 */

public class GirlsAdapter extends RecyclerView.Adapter<GirlsAdapter.GirlViewHolder> {

    private List<Girl> girls;
    private Context context;
    private LayoutInflater inflater;

    public GirlsAdapter(Context context, List<Girl> list) {
        this.context = context;
        this.girls = list;
        this.inflater = LayoutInflater.from(context);
    }

    public void setNewData(List<Girl> data) {
        this.girls = data;
        notifyDataSetChanged();
    }

    public List<Girl> getData() {
        return girls;
    }

    public void addData(int position, List<Girl> data) {
        this.girls.addAll(position, data);
        this.notifyItemRangeInserted(position, data.size());
    }

    private void startPictureActivity(View transitView, int position) {
        Intent intent = PictureActivity.newIntent(context, getData(), position);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                (Activity) context, transitView, PictureActivity.TRANSIT_PIC);
        try {
            ActivityCompat.startActivity(context, intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            context.startActivity(intent);
        }
    }

    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_gank, parent, false);
        GirlViewHolder holder = new GirlViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final GirlViewHolder holder, final int position) {
        final Girl girl = girls.get(position);
        if (girl.getHeight() != 0) {
            holder.iv.setOriginalSize(girl.getWidth(), girl.getHeight());
        } else {
            holder.iv.setOriginalSize(236, 354);
        }
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(girl.getLink())) {
                    startPictureActivity(view, position);
                } else {
                    Intent intent = MzituPictureActivity.newIntent(context, girl.getLink(), "");
                    context.startActivity(intent);
                }
            }
        });
        Glide.with(context).load(girl.getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.ic_glide_holder).crossFade(500).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return girls == null ? 0 : girls.size();
    }

    @Override
    public int getItemViewType(int position) {
        Girl girl = girls.get(position);
        return Math.round((float) girl.getWidth() / (float) girl.getHeight() * 10f);
    }

    @Override
    public long getItemId(int position) {
        return girls.get(position).hashCode();
    }

    class GirlViewHolder extends RecyclerView.ViewHolder {

        RatioImageView iv;

        public GirlViewHolder(View view) {
            super(view);
            iv = (RatioImageView) view.findViewById(R.id.iv_gank);
        }

    }
}
