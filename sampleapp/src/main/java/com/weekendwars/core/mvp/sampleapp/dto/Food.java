package com.weekendwars.core.mvp.sampleapp.dto;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

public class Food {

    public final String name;
    @DrawableRes
    public final int iconId;
    public final int progress;
    @ColorRes
    public final int backgroundId;
    public final int backgroundImage;
    public final String description;

    public Food(String name, @DrawableRes int iconId, int progress, int backgroundId,
                @DrawableRes int background, String description) {
        this.name = name;
        this.iconId = iconId;
        this.progress = progress;
        this.backgroundId = backgroundId;
        this.backgroundImage = background;
        this.description = description;
    }
}
