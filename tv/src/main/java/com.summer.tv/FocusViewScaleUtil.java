/*
 * Copyright (c) Wondertek Technologies Co., Ltd. 2019-2020. All rights reserved.
 */

package com.summer.tv;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class FocusViewScaleUtil {

    public static final float ZOOM_SCALE_SMALL = 1.05f;

    public static final float ZOOM_SCALE = 1.1f;

    public static final float ORIGIN_SCALE = 1.0f;

    private static final long ANIM_DURATION = 300;

    private static final long ANIM_DURATION_FEED = 500;

    public static void setViewAnimator(View v, boolean focus) {
        setViewAnimator(v, focus, ZOOM_SCALE);
    }

    public static void setViewAnimatorSmall(View v, boolean focus) {
        setViewAnimator(v, focus, ZOOM_SCALE_SMALL);
    }

    public static void setViewAnimator(View v, boolean focus, float... params) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX;
        ObjectAnimator scaleY;
        if (focus) {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", ORIGIN_SCALE, params[0]);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", ORIGIN_SCALE, params[0]);
        } else {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", params[0], ORIGIN_SCALE);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", params[0], ORIGIN_SCALE);
        }
        if (params.length > 1) {
            v.setPivotX(params[1]);
            v.setPivotY(params[2]);
        }
        animatorSet.setDuration(ANIM_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }

    public static void setViewAnimator(View v, boolean focus, float originScale, float zoomScale) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX;
        ObjectAnimator scaleY;
        if (focus) {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", originScale, zoomScale);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", originScale, zoomScale);
        } else {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", zoomScale, originScale);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", zoomScale, originScale);
        }

        animatorSet.setDuration(ANIM_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }
    public static void setViewAnimatorFeed(View v, boolean focus, float originScale, float zoomScale) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX;
        ObjectAnimator scaleY;
        if (focus) {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", originScale, originScale);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", originScale, zoomScale);
        } else {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", originScale, originScale);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", zoomScale, originScale);
        }

        animatorSet.setDuration(ANIM_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }
    public static void setViewAnimatorFeed(View v, boolean focus, float originScale, float zoomScaleX,float zoomScaleY) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX;
        ObjectAnimator scaleY;
        if (focus) {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", originScale, zoomScaleX);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", originScale, zoomScaleY);
        } else {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", zoomScaleX, originScale);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", zoomScaleY, originScale);
        }

        animatorSet.setDuration(ANIM_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }


    /**
     * 落焦动画
     * ---------------------
     * 注意：----------------------只适用于签到特殊楼层的列表落焦
     * @param v
     * @param focus  落焦
     * @param originScale
     * @param zoomScale
     */
    public static void setViewAnimatorSignFocus(View v, boolean focus, float originScale, float zoomScale) {
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX;
        ObjectAnimator scaleY;
        if (focus) {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.05f, 1.05f);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", originScale, zoomScale);
        } else {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.0f);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", zoomScale, originScale);
        }

        animatorSet.setDuration(ANIM_DURATION);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }

    public static void setViewAnimatorMiddle(View v, boolean focus,float... params) {
        setViewAnimator(v, focus, params);
    }

    /**
     * 自定义缩放大小、持续时长
     * @param v
     * @param focus
     * @param originScale
     * @param zoomScale
     * @param duration
     */
    public static void setViewAnimatorCustomed(View v, boolean focus, float originScale, float zoomScale,long duration) {
        if(v==null){
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator scaleX;
        ObjectAnimator scaleY;
        if (focus) {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", originScale, zoomScale);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", originScale, zoomScale);
        } else {
            scaleX = ObjectAnimator.ofFloat(v, "scaleX", zoomScale, originScale);
            scaleY = ObjectAnimator.ofFloat(v, "scaleY", zoomScale, originScale);
        }

        animatorSet.setDuration(duration);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }
}
