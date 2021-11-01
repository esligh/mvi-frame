package com.me.mvi.demo.net

import com.me.mvi.demo.models.Joke
import com.me.mvi.demo.models.JokesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface DadJokeService {
    @Headers("Accept: application/json")
    @GET("search")
    suspend fun search(
        @Query("query") query: String? = null,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 20
    ): JokesResponse

    @Headers("Accept: application/json")
    @GET("j/{id}")
    suspend fun fetch(@Path("id") id: String): Joke

    @Headers("Accept: application/json")
    @GET("/")
    suspend fun random(): Joke
}
