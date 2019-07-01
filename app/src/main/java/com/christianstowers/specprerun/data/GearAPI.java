package com.christianstowers.specprerun.data;

import com.christianstowers.specprerun.data.model.GearIdModel;
import com.christianstowers.specprerun.data.model.GearModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GearAPI {

    @GET("/gear/")
//    Observable<List<GearModel>> registerGear();
    Observable<List<GearModel>> registerGear();

//    @GET("/gear/{id}")
//    Observable<List<GearIdModel>> registerGearId();

}
