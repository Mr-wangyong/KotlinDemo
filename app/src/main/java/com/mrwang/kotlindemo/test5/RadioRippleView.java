package com.mrwang.kotlindemo.test5;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import com.mrwang.kotlindemo.R;


/**
 * Created by Mao on 2018/2/1.
 */

public class RadioRippleView extends FrameLayout {

    private RadioRippleLayerView mLayer1;
    private RadioRippleLayerView mLayer2;
    private RadioRippleLayerView mLayer3;

    private AnimationSet mLayer1Animation;
    private AnimationSet mLayer2Animation;
    private AnimationSet mLayer3Animation;

    private double mAudioSwitchValue;
    private boolean isMute;

    public RadioRippleView(@NonNull Context context) {
        this(context, null);
    }

    public RadioRippleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadioRippleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RadioRippleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.hani_view_radio_ripple_bg, this);
        mLayer1 = (RadioRippleLayerView) findViewById(R.id.radio_ciclr1);
        mLayer2 = (RadioRippleLayerView) findViewById(R.id.radio_ciclr2);
        mLayer3 = (RadioRippleLayerView) findViewById(R.id.radio_ciclr3);


        gone();
    }

    public void startAnimation(float volume) {
        if (isMute) {
            gone();
            return;
        }
        if (volume < mAudioSwitchValue) {
            gone();
            return;
        }
        if (mLayer1Animation != null || mLayer2Animation != null) {
            return;
        }

        mLayer1Animation = new AnimationSet(true);
        mLayer2Animation = new AnimationSet(true);

        mLayer1Animation.addAnimation(getScaleAnimation());
        mLayer1Animation.addAnimation(getAlphaAnimation());

        mLayer2Animation.addAnimation(getScaleAnimation());
        mLayer2Animation.addAnimation(getAlphaAnimation());
        mLayer2Animation.setStartOffset(400);

        mLayer2Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                gone();
                mLayer1Animation = null;
                mLayer2Animation = null;
            }


            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        show();
        mLayer1.startAnimation(mLayer1Animation);
        mLayer2.startAnimation(mLayer2Animation);
    }

    private void show() {
        mLayer1.setVisibility(VISIBLE);
        mLayer2.setVisibility(VISIBLE);
        mLayer3.setVisibility(VISIBLE);
    }

    private void gone() {
        if (mLayer1 != null) {
            mLayer1.setVisibility(INVISIBLE);
        }
        if (mLayer2 != null) {
            mLayer2.setVisibility(INVISIBLE);
        }
    }

    private ScaleAnimation getScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1600);
        scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        //scaleAnimation.setFillAfter(true);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        return scaleAnimation;
    }

    private AlphaAnimation getAlphaAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0);
        alphaAnimation.setDuration(1600);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        //alphaAnimation.setFillAfter(true);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        return alphaAnimation;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelRippleAnimation();
    }

    public void cancelRippleAnimation() {
        if (mLayer1Animation != null) {
            mLayer1Animation.cancel();
            mLayer1.clearAnimation();
        }
        if (mLayer2Animation != null) {
            mLayer2Animation.cancel();
            mLayer2.clearAnimation();
        }
    }

    public void clearAnimation() {
        cancelRippleAnimation();
        mLayer1Animation = null;
        mLayer2Animation = null;
    }

    public void setIsMute(boolean mute) {
        isMute = mute;
        if (!mute) {
            cancelRippleAnimation();
            gone();
        }
    }

    public void clear() {
        gone();
    }

    //电台小窗单独用
    public void startLooperAnim() {
        if (mLayer1Animation != null || mLayer2Animation != null) {
            return;
        }
        mLayer1Animation = new AnimationSet(true);
        mLayer2Animation = new AnimationSet(true);
        mLayer3Animation = new AnimationSet(true);

        ScaleAnimation scaleAnimation = getScaleAnimation();
        AlphaAnimation alphaAnimation = getAlphaAnimation();
        mLayer1Animation.addAnimation(scaleAnimation);
        mLayer1Animation.addAnimation(alphaAnimation);

        ScaleAnimation scaleAnimation2 = getScaleAnimation();
        AlphaAnimation alphaAnimation2 = getAlphaAnimation();
        mLayer2Animation.addAnimation(scaleAnimation2);
        mLayer2Animation.addAnimation(alphaAnimation2);
        mLayer2Animation.setStartOffset(800);

        ScaleAnimation scaleAnimation3 = getScaleAnimation();
        AlphaAnimation alphaAnimation3 = getAlphaAnimation();
        mLayer3Animation.addAnimation(scaleAnimation3);
        mLayer3Animation.addAnimation(alphaAnimation3);
        mLayer3Animation.setStartOffset(1600);

        show();
        mLayer1.startAnimation(mLayer1Animation);
        mLayer2.startAnimation(mLayer2Animation);
        mLayer3.startAnimation(mLayer3Animation);
    }
}
