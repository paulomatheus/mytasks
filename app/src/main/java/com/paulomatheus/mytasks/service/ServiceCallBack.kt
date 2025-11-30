package com.paulomatheus.mytasks.service

import android.util.Log
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paulomatheus.mytasks.repository.ResponseDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceCallBack<T>(private val liveData: MutableLiveData<ResponseDTO<T>>): Callback<T> {
    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        if(response.isSuccessful){
            liveData.value = ResponseDTO(response.body())
        }else {
            liveData.value = ResponseDTO(error = true)
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        liveData.value = ResponseDTO(error = true)
    }
}