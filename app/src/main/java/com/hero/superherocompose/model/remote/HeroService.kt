package com.hero.superherocompose.model.remote

import com.hero.superherocompose.model.data.HeroWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroService {

    @GET("{token}/search/batman")
    fun getHeroes(
        @Path("token") token: String = "10157703717092094"
    ): Call<HeroWrapper>
}