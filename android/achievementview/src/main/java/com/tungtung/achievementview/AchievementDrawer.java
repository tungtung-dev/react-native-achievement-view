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
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;


class AchievementDrawer {
    private final String font;
    private final int resourceId;
    private final int colorId;
    private final int shadowColorId;
    private final int index;
    private final int fontSize;

    private AchievementDrawer(Builder builder) {
        this.font = builder.font;
        this.resourceId = builder.resourceId;
        this.colorId = builder.colorId;
        this.shadowColorId = builder.shadowColorId;
        this.index = builder.index;
        this.fontSize = builder.fontSize;
    }

    /**
     * Draw star and index on canvas
     *
     * @param context input Context from view
     * @param canvas  input Canvas from view
     */
    void draw(Context context, Canvas canvas) {
        Resources resources = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, resourceId);

        Bitmap.Config bitmapConfig = bitmap.getConfig();
        // set default bitmap config if none
        if (bitmapConfig == null) {
            bitmapConfig = Bitmap.Config.ARGB_8888;
        }
        // resource bitmaps are immutable,
        // so we need to convert it to mutable one
        bitmap = bitmap.copy(bitmapConfig, true);

        // new actualised Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        Rect src = new Rect(0, 0, bitmap.getWidth() - 1, bitmap.getHeight() - 1);
        Rect dest = canvas.getClipBounds();
        canvas.drawBitmap(bitmap, src, dest, paint);
        // text color - #3D3D3D
        int paintColor = getColor(context, colorId);

        paint.setColor(paintColor);

        // draw text to the Canvas center
        Rect bounds = new Rect();
        String text = String.valueOf(index);
        paint.getTextBounds(text, 0, text.length(), bounds);

        // Scale text to fix in the canvas - BEGIN
        float hypotenuseBound = countHypotenuse(bounds);
        float hypotenuseDest = countHypotenuse(dest);
        float myScale = hypotenuseDest / (hypotenuseBound * 3);
        int x = (dest.width() - (int) (bounds.width() * myScale)) / 2;
        int y = (dest.height() + (int) (bounds.height() * myScale)) / 2;
        // text size in pixels
        paint.setTextSize((int) (fontSize * myScale));
        // Scale text to fix in the canvas - END
        // text shadow
        int shadowColor = getColor(context, shadowColorId);
        paint.setShadowLayer(1f, 0f, 1f, shadowColor);
        Typeface plain = Typeface.createFromAsset(context.getAssets(), font);
        Typeface bold = Typeface.create(plain, Typeface.BOLD);
        paint.setTypeface(bold);

        canvas.drawText(text, x, y, paint);
    }

    private int getColor(Context context, int colorId) {
        int paintColor;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            paintColor = context.getColor(colorId);
        } else {
            paintColor = context.getResources().getColor(colorId);
        }
        return paintColor;
    }

    /**
     * Count Hypotenuse of rectangle
     *
     * @param rectangle input rectangle
     * @return Hypotenuse of input rectangle
     */
    private float countHypotenuse(Rect rectangle) {
        return (float) Math.sqrt(Math.pow(rectangle.width(), 2) + Math.pow(rectangle.height(), 2));
    }

    /**
     * Builder
     */
    static final class Builder {
        private String font;
        private int resourceId;
        private int colorId;
        private int shadowColorId;
        private int index;
        private int fontSize;

        /**
         * Set font url
         *
         * @param font Font url
         * @return current builder
         */
        public Builder setFont(String font) {
            this.font = font;
            return this;
        }

        /**
         * Set image Resource Id
         *
         * @param resourceId resource Id
         * @return current builder
         */
        Builder setResourceId(int resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        /**
         * Set text Color Id
         *
         * @param colorId color Id
         * @return current builder
         */
        Builder setColorId(int colorId) {
            this.colorId = colorId;
            return this;
        }

        /**
         * Set text shadowColor Id
         *
         * @param shadowColorId shadowColor Id
         * @return current builder
         */
        Builder setShadowColorId(int shadowColorId) {
            this.shadowColorId = shadowColorId;
            return this;
        }

        /**
         * Set index
         *
         * @param index index
         * @return current builder
         */
        Builder setIndex(int index) {
            this.index = index;
            return this;
        }

        /**
         * Set font size
         *
         * @param fontSize fontSize
         * @return current builder
         */
        Builder setFontSize(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        /**
         * Build Achievement Drawer
         *
         * @return new instance of Achievement Drawer
         */
        AchievementDrawer build() {
            return new AchievementDrawer(this);
        }
    }
}
