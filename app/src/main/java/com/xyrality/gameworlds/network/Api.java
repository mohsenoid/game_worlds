package com.xyrality.gameworlds.network;

import com.xyrality.gameworlds.network.model.WorldsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Mohsen on 1/20/16.
 */
public interface Api {

    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("/XYRALITY/WebObjects/BKLoginServer.woa/wa/worlds")
    Call<WorldsResponse> login(
            @Field("login") String login,
            @Field("password") String password,
            @Field("deviceType") String deviceType,
            @Field("deviceId") String deviceId);

}
