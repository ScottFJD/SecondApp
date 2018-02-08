package com.yeapao.andorid.api;

import com.yeapao.andorid.model.FitPlanDetailModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by fujindong on 06/02/2018.
 */

public interface YeapaoFitPlanApi {
    @GET("userGenerateCourseCustomization")
    Observable<FitPlanDetailModel> getFitPlan(@Query("customizeLevel") String leves, @Query("customizeParts") String parts);

}
