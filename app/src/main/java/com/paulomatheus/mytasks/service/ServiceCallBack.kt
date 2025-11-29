package com.paulomatheus.mytasks.service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceCallBack<T>: Callback<T> {
    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        if(response.isSuccessful){
            response.body()
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        TODO("Not yet implemented")
    }
}