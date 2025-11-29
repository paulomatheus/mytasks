package com.paulomatheus.mytasks.service

import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceCallBack<T>(private val liveData: MutableLiveData<T>): Callback<T> {
    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        if(response.isSuccessful){
            liveData.value = response.body()
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        TODO("Not yet implemented")
    }
}