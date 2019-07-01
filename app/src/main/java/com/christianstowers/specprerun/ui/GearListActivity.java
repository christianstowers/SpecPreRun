package com.christianstowers.specprerun.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.christianstowers.specprerun.R;
import com.christianstowers.specprerun.data.GearAPI;
import com.christianstowers.specprerun.data.model.GearModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//https://www.learn2crack.com/2016/11/android-rxjava-2-and-retrofit.html

public class GearListActivity extends AppCompatActivity {

    public static final String BASE_URL = " insert api url here ";

    private RecyclerView mRecyclerView;

    private CompositeDisposable mCompositeDisposable;

    private GearListAdapter mGearListAdapter;

    private ArrayList<GearModel> mGearList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gear_recycler);

        mCompositeDisposable = new CompositeDisposable();
        initRecylerView();
        loadJSON();
    }

    private void initRecylerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.gear_recyler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void loadJSON() {
        GearAPI gearAPI = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GearAPI.class);

        mCompositeDisposable.add(gearAPI.registerGear()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));


    }

    private void handleResponse(List<GearModel> gearList) {
        mGearList = new ArrayList<>(gearList);
        mGearListAdapter = new GearListAdapter(mGearList);
        mRecyclerView.setAdapter(mGearListAdapter);
    }

    private void handleError(Throwable error) {
        Toast.makeText(this, "Oops. Error: " + error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

}
