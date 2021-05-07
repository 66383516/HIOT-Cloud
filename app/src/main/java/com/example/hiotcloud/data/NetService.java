package com.example.hiotcloud.data;

import com.example.hiotcloud.test.mvptest.model.User;
import com.example.hiotcloud.test.networktest.LoginResultDTO;
import com.example.hiotcloud.test.networktest.ResultBase;
import com.example.hiotcloud.test.networktest.UserBean;


import io.reactivex.Observable;
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
 * 网络请求接口
 */
public interface NetService {

    /**
     * 登录
     * @param userName
     * @param password
     * @param loginCode
     * @return
     */
    @POST("/auth/login")
    Observable<ResultBase<LoginResultDTO>> login(@Query("username") String userName,
                                                 @Query("password") String password,
                                                 @Query("loginCode") String loginCode);

    /**
     * 用户信息
     * @param authorization
     * @return
     */
    @GET("/user/one")
    Observable<ResultBase<UserBean>> userinfo(@Header("Authorization") String authorization);

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     * @return
     */
    @PUT("/user/email")
    Observable<ResultBase<String>> updateemail(@Header("Authorization") String authorization,
                                   @Query("email") String email);

    /**
     * 注册
     * @return
     */
    @POST("/user/register")
    Observable<ResultBase<UserBean>> register(@Body UserBean userBean);


    /**
     * 修改密码
     */
}
