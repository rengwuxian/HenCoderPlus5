package com.hencoder.threadrxjava.network;

import com.hencoder.threadrxjava.model.Repo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
  @GET("users/{username}/repos")
  Single<List<Repo>> getRepos(@Path("username") String username);
}
