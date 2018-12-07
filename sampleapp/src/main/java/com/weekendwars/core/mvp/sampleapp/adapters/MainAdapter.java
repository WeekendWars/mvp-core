package com.weekendwars.core.mvp.sampleapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weekendwars.core.mvp.sampleapp.R;
import com.weekendwars.core.mvp.sampleapp.adapters.holders.MainHolder;
import com.weekendwars.core.mvp.sampleapp.dto.Food;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainHolder> {
    private final List<Food> mData;
    private int mSelected = -1;

    public MainAdapter(final ArrayList<Food> list) {
        mData = list;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new MainHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return mSelected == -1 || position != mSelected
                ? R.layout.core_mvp_sampleapp_holder_main : R.layout.core_mvp_sampleapp_holder_main_expanded;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainHolder holder, final int position) {
        holder.onBind(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final int adapterPosition = holder.getAdapterPosition();
                final int oldPosition = mSelected;

                if (mSelected != adapterPosition) {
                    mSelected = adapterPosition;
                } else {
                    mSelected = -1;
                }

                if (oldPosition < 0 || oldPosition == adapterPosition) {
                    notifyItemChanged(adapterPosition);
                } else {
                    if (oldPosition < adapterPosition) {
                        notifyItemRangeChanged(oldPosition, adapterPosition - oldPosition + 1);
                    } else {
                        notifyItemRangeChanged(adapterPosition, oldPosition - adapterPosition + 1);
                    }
                }
            }
        });
    }

    @Override
    public long getItemId(final int position) {
        return mData.get(position).iconId;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
