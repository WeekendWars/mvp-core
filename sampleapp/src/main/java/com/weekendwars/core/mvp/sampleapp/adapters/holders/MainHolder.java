package com.weekendwars.core.mvp.sampleapp.adapters.holders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.weekendwars.core.mvp.sampleapp.R;
import com.weekendwars.core.mvp.sampleapp.dto.Food;
import com.weekendwars.core.mvp.sampleapp.utils.PaletteUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainHolder extends RecyclerView.ViewHolder {
    public MainHolder(View itemView) {
        super(itemView);
    }

    public void onBind(final Food food) {
        final Bitmap background = BitmapFactory.decodeResource(itemView.getContext().getResources(), food.backgroundImage);
        final Palette palette = PaletteUtils.createPaletteSync(background);
        final TextView titleView = itemView.findViewById(R.id.title);
        final TextView descriptionView = itemView.findViewById(R.id.description);
        final CircleImageView imageView = itemView.findViewById(R.id.icon);

        ((ProgressBar) itemView.findViewById(R.id.progress)).setProgress(food.progress);
        itemView.findViewById(R.id.background).setBackgroundColor(itemView
                .getContext().getResources().getColor(food.backgroundId));

        ((ImageView) itemView.findViewById(R.id.backgroundImage)).setImageBitmap(background);

        titleView.setText(food.name);
        titleView.setTextColor(palette.getVibrantSwatch().getTitleTextColor());
        descriptionView.setTextColor(palette.getVibrantSwatch().getBodyTextColor());
        descriptionView.setText(food.description);

        imageView.setImageResource(food.iconId);
        imageView.setBorderColor(palette.getMutedColor(itemView.getContext()
                .getResources().getColor(R.color.core_mvp_sampleapp_colorAccent)));
    }
}
