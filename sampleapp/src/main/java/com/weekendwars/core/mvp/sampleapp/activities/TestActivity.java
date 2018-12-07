package com.weekendwars.core.mvp.sampleapp.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.weekendwars.core.mvp.activities.AbstractActivity;
import com.weekendwars.core.mvp.sampleapp.R;
import com.weekendwars.core.mvp.sampleapp.adapters.MainAdapter;
import com.weekendwars.core.mvp.sampleapp.dto.Food;
import com.weekendwars.core.mvp.sampleapp.presenters.TestPresenter;
import com.weekendwars.core.mvp.sampleapp.views.TestView;

import java.util.ArrayList;


public class TestActivity extends AbstractActivity<TestView, TestPresenter> implements TestView {
    private ArrayList<Food> mList;


    @Override
    protected TestView getMvpView() {
        return this;
    }

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.core_mvp_sampleapp_activity_test;
    }

    @Override
    public void displayRestoredState(final long savedState) {
        Toast.makeText(this, "Saved state: " + savedState, Toast.LENGTH_LONG).show();
    }

    public void goToFragmentsActivity(@NonNull final View view) {
        startActivity(new Intent(this, FragmentTestActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.run) {
            startActivity(new Intent(this, TestActivity2.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mList = new ArrayList<>();
        mList.add(new Food("Fruits", R.mipmap.apple, 35, R.color.core_mvp_sampleapp_fruits,
                R.mipmap.fruits_background, getString(R.string.core_mvp_sampleapp_fruits_description)));
        mList.add(new Food("Veggies", R.mipmap.carrot, 75, R.color.core_mvp_sampleapp_veggies,
                R.mipmap.veggies_background, getString(R.string.core_mvp_sampleapp_veggies_description)));
        mList.add(new Food("Whole grains", R.mipmap.corn, 35, R.color.core_mvp_sampleapp_grains,
                R.mipmap.grains_background, getString(R.string.core_mvp_sampleapp_grains_description)));
        mList.add(new Food("Dairies", R.mipmap.dairy, 15, R.color.core_mvp_sampleapp_dairies,
                R.mipmap.dairies_background, getString(R.string.core_mvp_sampleapp_dairies_description)));
        mList.add(new Food("Proteins", R.mipmap.steak, 95, R.color.core_mvp_sampleapp_proteins,
                R.mipmap.proteins_background, getString(R.string.core_mvp_sampleapp_proteins_description)));
        mList.add(new Food("Nuts", R.mipmap.nuts, 0, R.color.core_mvp_sampleapp_nuts,
                R.mipmap.nuts_background, getString(R.string.core_mvp_sampleapp_nuts_description)));
        mList.add(new Food("Sweets", R.mipmap.donut, 95, R.color.core_mvp_sampleapp_sweets,
                R.mipmap.sweets_background, getString(R.string.core_mvp_sampleapp_sweets_description)));
        mList.add(new Food("Fried food", R.mipmap.fries, 95, R.color.core_mvp_sampleapp_grains,
                R.mipmap.fries_background, getString(R.string.core_mvp_sampleapp_fries_description)));
        mList.add(new Food("Soft drinks", R.mipmap.soda, 95, R.color.core_mvp_sampleapp_drinks,
                R.mipmap.drinks_background, getString(R.string.core_mvp_sampleapp_soft_drinks_description)));

        final ObjectAnimator animator = ObjectAnimator.ofInt(findViewById(R.id.arc_progress),
                "progress", 30, 100);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {
                super.onAnimationEnd(animation);
                ((LottieAnimationView) findViewById(R.id.animation_view)).playAnimation();
            }
        });

        findViewById(R.id.arc_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });

        final MainAdapter adapter = new MainAdapter(mList);
        adapter.setHasStableIds(true);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.core_mvp_sampleapp_menu, menu);
        return true;
    }
}
