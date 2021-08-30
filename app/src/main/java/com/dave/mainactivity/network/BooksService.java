package com.dave.mainactivity.network;

import com.dave.mainactivity.model.BooksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BooksService {
    @GET
    Call<BooksResponse> getListOfBooks(@Url String str);
}