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
package com.tungtung.achievementview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

public class AchievementView extends View {

    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;
    private static final int DEFAULT_FONT_SIZE = 14;
    private static final String DEFAULT_FONT = "fonts/VNFQuickSand-Bold.ttf";
    private final int fontSize;
    private AchievementDrawer achievementDrawer;
    private int index;
    private String font;
    private int normalImage;
    private int thirdImage;
    private int secondImage;
    private int normalColorId;
    private int thirdColorId;
    private int secondColorId;
    private int firstColorId;
    private int firstImage;

    public AchievementView(Context context) {
        super(context);
        firstImage = R.mipmap.achievement_1;
        secondImage = R.mipmap.achievement_2;
        thirdImage = R.mipmap.achievement_3;
        normalImage = R.mipmap.achievement_normal;
        firstColorId = R.color.red;
        secondColorId = R.color.green;
        thirdColorId = R.color.orange;
        normalColorId = R.color.blue;
        font = DEFAULT_FONT;
        index = FIRST_INDEX;
        fontSize = DEFAULT_FONT_SIZE;
        buildDrawer();
    }

    public AchievementView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AchievementView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AchievementView, defStyleAttr, 0);
        firstImage = typedArray.getResourceId(R.styleable.AchievementView_firstImage, R.mipmap.achievement_1);
        secondImage = typedArray.getResourceId(R.styleable.AchievementView_secondImage, R.mipmap.achievement_2);
        thirdImage = typedArray.getResourceId(R.styleable.AchievementView_thirdImage, R.mipmap.achievement_3);
        normalImage = typedArray.getResourceId(R.styleable.AchievementView_normalImage, R.mipmap.achievement_normal);
        firstColorId = typedArray.getResourceId(R.styleable.AchievementView_firstColor, R.color.red);
        secondColorId = typedArray.getResourceId(R.styleable.AchievementView_secondColor, R.color.green);
        thirdColorId = typedArray.getResourceId(R.styleable.AchievementView_thirdColor, R.color.orange);
        normalColorId = typedArray.getResourceId(R.styleable.AchievementView_normalColor, R.color.blue);
        font = typedArray.getString(R.styleable.AchievementView_font);
        index = typedArray.getInt(R.styleable.AchievementView_index, FIRST_INDEX);
        fontSize = typedArray.getInt(R.styleable.AchievementView_fontSize, DEFAULT_FONT_SIZE);

        //
        if (TextUtils.isEmpty(font)) {
            font = DEFAULT_FONT;
        }

        buildDrawer();
        typedArray.recycle();

    }

    /**
     * Build Achievement drawer
     */
    private void buildDrawer() {
        int resId = getImageByIndex(index);
        int colorId = getColorByIndex(index);
        achievementDrawer = new AchievementDrawer.Builder()
                .setResourceId(resId)
                .setIndex(index)
                .setColorId(colorId)
                .setFont(font)
                .setFontSize(fontSize)
                .setShadowColorId(android.R.color.white)
                .build();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        achievementDrawer.draw(getContext(), canvas);
    }

    /**
     * Get Color id by Index
     *
     * @param index index
     * @return color id
     */
    private int getColorByIndex(int index) {
        switch (index) {
            case FIRST_INDEX:
                return firstColorId;
            case SECOND_INDEX:
                return secondColorId;
            case THIRD_INDEX:
                return thirdColorId;
            default:
                return normalColorId;
        }
    }

    /**
     * Get Resource Id by Index
     *
     * @param index index
     * @return image resource Id
     */
    private int getImageByIndex(int index) {
        switch (index) {
            case FIRST_INDEX:
                return firstImage;
            case SECOND_INDEX:
                return secondImage;
            case THIRD_INDEX:
                return thirdImage;
            default:
                return normalImage;
        }
    }

    public void setIndex(int index) {
        this.index = index;
        buildDrawer();
    }
}
