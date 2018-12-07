package com.weekendwars.core.mvp.sampleapp.utils;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

public class PaletteUtils {

    public static Palette createPaletteSync(Bitmap bitmap) {
        return Palette.from(bitmap).generate();
    }

    public static void createPaletteAsync(Bitmap bitmap, Palette.PaletteAsyncListener listener) {
        Palette.from(bitmap).generate(listener);
    }
}
