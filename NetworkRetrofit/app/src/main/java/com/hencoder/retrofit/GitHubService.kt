package com.hencoder.retrofit

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
  @GET("users/{user}/repos")
  fun listRepos(@Path("user") user: String?): Call<List<Repo>>

  @GET("users/{user}/repos")
  fun listReposRx(@Path("user") user: String?): Single<List<Repo>>
}