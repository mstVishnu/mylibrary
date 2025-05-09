package com.student.mylibrary.api.api_service

import android.util.Log
import com.google.gson.GsonBuilder
import com.student.mylibrary.HomeResponseModel
import com.student.mylibrary.constant.ApplicationConstants
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface APIService {



    companion object Factory {
        fun create(): APIService {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val gson = GsonBuilder().setLenient().create()

            val client = OkHttpClient.Builder()
                .readTimeout(ApplicationConstants.ApiConstants.TIMEOUT_VALUE.toLong(), TimeUnit.MILLISECONDS)
                .writeTimeout(ApplicationConstants.ApiConstants.TIMEOUT_VALUE.toLong(), TimeUnit.MILLISECONDS)
                //.addInterceptor(httpLoggingInterceptor)
                .addInterceptor { chain ->
                    val request = chain.request()

                    // Log custom info
                    Log.d("API_LOG", "Request URL: ${request.url}")
                    Log.d("API_LOG", "Headers: ${request.headers}")
                    if (request.body is FormBody) {
                        val body = request.body as FormBody
                        for (i in 0 until body.size) {
                            Log.d("API_LOG", "${body.name(i)}: ${body.value(i)}")
                        }
                    }

                    chain.proceed(request)
                }
//                .addInterceptor { chain ->
//                    val request = chain.request().newBuilder()
//                        .addHeader("langID", appPreferences.getInt(ApplicationConstants.LANGUAGE_ID).toString())
//                        .build()
//                    chain.proceed(request)
//                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(ApplicationConstants.ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

            return retrofit.create(APIService::class.java)
        }
    }

    @FormUrlEncoded
    @POST("contactlist")
    fun getContact(@FieldMap fields: HashMap<String, String>): Call<HomeResponseModel?>?
}