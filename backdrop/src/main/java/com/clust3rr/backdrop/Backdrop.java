package com.clust3rr.backdrop;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

public class Backdrop implements BackdropActions {

    private Context context;
    private Toolbar toolbar;
    private View backLayer;
    private View frontLayer;
    private Drawable navigationIcon;
    private Drawable closeIcon;
    private Integer duration;
    private Integer heightPixels;
    private Integer widthPixels;
    private Float density;
    private Integer dropHeight = 24;
    private final Integer closeDrop = 0;
    private Boolean dropped = false;
    private Boolean supportToolbar = false;
    private Boolean supportSubtitle = false;
    private View.OnClickListener onClickNavigation;

    public Backdrop(@NonNull Context context, View backLayer, View frontLayer, Integer duration) {
        this.context = context;
        this.backLayer = backLayer;
        this.frontLayer = frontLayer;
        this.duration = duration;
    }

    private Toolbar getToolbar() {
        return toolbar;
    }

    private Drawable getNavigationIcon() {
        return navigationIcon;
    }

    private Drawable getCloseIcon() {
        return closeIcon;
    }

    private Integer getDuration() {
        return duration;
    }

    private Context getContext() {
        return context;
    }

    public Integer getHeightPixels() {
        return heightPixels;
    }

    public Integer getWidthPixels() {
        return widthPixels;
    }

    public Float getDensity() {
        return density;
    }

    private float getDrop() {
        return dropHeight;
    }

    @Override
    public boolean isDropped() {
        return dropped;
    }

    private boolean isSupportToolbar() {
        return supportToolbar;
    }

    private Boolean isSupportSubtitle() {
        return supportSubtitle;
    }

    public View.OnClickListener getOnClickNavigation() {
        return onClickNavigation;
    }

    private void setDisplayMetrics(Context context) {
        this.heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        this.widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        this.density = context.getResources().getDisplayMetrics().density;
    }

    public void setSupportToolbar(Toolbar toolbar, Drawable navigationIcon, Drawable closeIcon) {
        this.dropHeight += 56;
        this.supportToolbar = true;
        this.toolbar = toolbar;
        this.navigationIcon = navigationIcon;
        this.closeIcon = closeIcon;
        this.toolbar.setNavigationIcon(navigationIcon);
    }

    public void onClickNavigation(View.OnClickListener onClickListener) {
        this.onClickNavigation = onClickListener;
    }

    public void setSupportSubtitle() {
        this.dropHeight += 56;
        this.supportSubtitle = true;
    }

    public void build() {
        if (isSupportToolbar()) {
            if (getOnClickNavigation() != null) {
                getToolbar().setNavigationOnClickListener(v -> getOnClickNavigation().onClick(v));
            } else {
                throw new NullPointerException("implement a click event for the navigation button");
            }
        }
        if (context != null) setDisplayMetrics(getContext());
        else throw new NullPointerException("context can not be null, check the instance");
    }

    @Override
    public void show() {
        float pixel = closeDrop;
        if (!isDropped()) {
            pixel = getHeightPixels() - getDrop() * getDensity();
            if (isSupportSubtitle()) {
                backLayer.setPadding(0, 0, 0, (int) (56 * density));
            }
        }
        moveBackdrop(pixel);
    }

    @Override
    public void showByViewSize() {
        float pixel = closeDrop;
        if (!isDropped()) {
            float result = getHeightPixels() - getDrop() * getDensity();
            int backLayerHeight = backLayer.getHeight();
            if (backLayerHeight < result) {
                pixel = backLayerHeight;
            } else {
                pixel = result;
                if (isSupportSubtitle()) {
                    backLayer.setPadding(0, 0, 0, (int) (56 * density));
                }
            }
        }
        moveBackdrop(pixel);
    }

    @Override
    public void close() {
        moveBackdrop(closeDrop);
    }

    private void moveBackdrop(float values) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(frontLayer, "translationY", values);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setDuration(getDuration());
        animation.start();
        dropped = !dropped;
        updateIcon();
    }

    private void updateIcon() {
        if (isDropped()) toolbar.setNavigationIcon(getCloseIcon());
        else toolbar.setNavigationIcon(getNavigationIcon());
    }

}
