package com.expose.drive.rest;

import com.expose.drive.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vineesh.expose on 03-11-2017.
 */

public interface ApiInterface {

    @GET("files?q='{FOLDER_ID}'+in+parents&key={API_KEY}")
    Call<Model> getDrivelist();
}
