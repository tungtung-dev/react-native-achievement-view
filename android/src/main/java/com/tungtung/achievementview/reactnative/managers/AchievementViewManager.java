/*
 * Copyright 2017 - 2017 Tien Nguyen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tungtung.achievementview.reactnative.managers;

import android.util.Log;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.tungtung.achievementview.AchievementView;

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
