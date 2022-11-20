package com.example.challangechapter6.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challangechapter6.model.ResponseProductItem
import com.example.challangechapter6.network.RestfullAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private var api : RestfullAPI) : ViewModel(){

    var liveDataCar: MutableLiveData<List<ResponseProductItem>> = MutableLiveData()


    fun getliveDataCar() : MutableLiveData<List<ResponseProductItem>> {
        return  liveDataCar
    }

    fun callApiCar(){
        api.getAllCar().enqueue(object : Callback<List<ResponseProductItem>> {
            override fun onResponse(
                call: Call<List<ResponseProductItem>>,
                response: Response<List<ResponseProductItem>>
            ) {
                if (response.isSuccessful){
                    liveDataCar.postValue(response.body())
                }else{
                    liveDataCar.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ResponseProductItem>>, t: Throwable) {
                liveDataCar.postValue(null)
            }

        })

        api.getAllCar().enqueue(object : Callback<List<ResponseProductItem>> {
            override fun onResponse(
                call: Call<List<ResponseProductItem>>,
                response: Response<List<ResponseProductItem>>
            ) {
                if (response.isSuccessful){
                    liveDataCar.postValue(response.body())
                }else{
                    liveDataCar.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ResponseProductItem>>, t: Throwable) {
                liveDataCar.postValue(null)
            }

        })
    }

    fun favoriteCar(){
        api.favoriteCar().enqueue(object : Callback<List<ResponseProductItem>> {
            override fun onResponse(
                call: Call<List<ResponseProductItem>>,
                response: Response<List<ResponseProductItem>>
            ) {
                if (response.isSuccessful){
                    liveDataCar.postValue(response.body())
                }else{
                    liveDataCar.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ResponseProductItem>>, t: Throwable) {
                liveDataCar.postValue(null)
            }

        })

        api.getAllCar().enqueue(object : Callback<List<ResponseProductItem>> {
            override fun onResponse(
                call: Call<List<ResponseProductItem>>,
                response: Response<List<ResponseProductItem>>
            ) {
                if (response.isSuccessful){
                    liveDataCar.postValue(response.body())
                }else{
                    liveDataCar.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<ResponseProductItem>>, t: Throwable) {
                liveDataCar.postValue(null)
            }

        })
    }

}