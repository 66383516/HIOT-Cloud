package com.example.hiotcloud.test.networktest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * retrofit service接口
 */
public interface TestRetfitService {
    public static final String basUrl = "http://114.67.88.191:8080";

    @GET("/")
    Call<ResponseBody> test();

    @POST("/auth/login")
    Call<ResponseBody> login(@Query("username") String userName,
                             @Query("password") String password,
                             @Query("loginCode") String loginCode);

    @POST("/auth/login")
    @FormUrlEncoded
    Call<ResponseBody> login2(@Field("username") String userName,
                              @Field("password") String password,
                              @Field("loginCode") String loginCode);

    @GET("/user/one")
    Call<ResponseBody> userinfo(@Header("Authorization") String authorization);
    @GET("/user/one")
    Call<ResultBase<UserBean>> userinfo2(@Header("Authorization") String authorization);

    @PUT("/user/email")
    Call<ResponseBody> updateemail(@Header("Authorization") String authorization, @Query("email") String email);

    @POST("/user/register")
    Call<ResponseBody> register(@Body UserBean userBean);

}
