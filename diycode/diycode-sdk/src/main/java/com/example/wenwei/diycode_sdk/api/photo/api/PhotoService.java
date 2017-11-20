package com.example.wenwei.diycode_sdk.api.photo.api;

import com.example.wenwei.diycode_sdk.api.photo.bean.Photo;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface PhotoService {


    /**
     * 上传图片,请使用 Multipart 的方式提交图片文件
     *
     * @param img_file 图片文件
     * @return 图片地址
     */
    @POST("photos.json")
    Call<Photo> uploadPhoto(@Field("file") File img_file);

}
