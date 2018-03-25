package com.example.wenwei.diycode_sdk.api.likes.api;

import com.example.wenwei.diycode_sdk.api.base.bean.State;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LikesService {

    /**
     * 赞
     *
     * @param obj_type ["topic", "reply", "news"]
     * @param obj_id   id
     * @return 是否成功
     */
    @POST("likes.json")
    @FormUrlEncoded
    Call<State> like(@Field("obj_type") String obj_type, @Field("obj_id") Integer obj_id);

    /**
     * 取消赞
     *
     * @param obj_type ["topic", "reply", "news"]
     * @param obj_id   id
     * @return 是否成功
     */
    @DELETE("likes.json")
    Call<State> unLike(@Field("obj_type") String obj_type, @Field("obj_id") Integer obj_id);

}
