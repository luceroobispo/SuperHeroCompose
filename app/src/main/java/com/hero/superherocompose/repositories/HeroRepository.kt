package com.hero.superherocompose.repositories

import android.util.Log
import com.hero.superherocompose.factories.HeroServiceFactory
import com.hero.superherocompose.model.data.Hero
import com.hero.superherocompose.model.data.HeroWrapper
import com.hero.superherocompose.model.remote.HeroService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class HeroRepository(private val heroService: HeroService = HeroServiceFactory.getHeroService()) {

    fun getHeroes(callback: (List<Hero>) -> Unit) {
        val getHeroes = heroService.getHeroes()

        getHeroes.enqueue(object: Callback<HeroWrapper>{
            override fun onResponse(call: Call<HeroWrapper>, response: Response<HeroWrapper>) {
                if(response.isSuccessful){
                    callback(response.body()?.heroes?: emptyList()) // if response.body() is null, return an empty list
                }
            }

            override fun onFailure(call: Call<HeroWrapper>, t: Throwable) {
                t.message?.let{
                    Log.d("HeroRepository", it)
                }
            }
        })
    }
}