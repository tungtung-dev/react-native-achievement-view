package com.tungtung.achievementview.reactnative.managers;

import android.util.Log;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.tungtung.achievementview.AchievementView;

/**
 * This file is created by Tien Nguyen on 5/25/17.
 */

public class AchievementViewManager extends SimpleViewManager<AchievementView> {
    private static final String NAME = "AchievementView";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected AchievementView createViewInstance(ThemedReactContext reactContext) {
        return new AchievementView(reactContext);
    }

    /**
     * This function will be able to call from Js components
     * @param view view instance
     * @param index input index
     */
    @ReactProp(name = "index")
    public void setSrc(AchievementView view, int index) {
        view.setIndex(index);
        Log.d(NAME, String.valueOf(index));
    }
}
