package com.example.currentweather.data.remote


import android.util.Log
import retrofit2.Response
import java.io.IOException
private const val TAG="ApiCall"
abstract class ApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        val response = apiCall()
        try {
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    Log.i(TAG, "safeApiCall: response success $resultResponse")
                    return Resource.Success(resultResponse)
                }
            }

        } catch (ex: Exception) {
            return when (ex) {
                is IOException -> Resource.Error("Network Failure")
                else -> Resource.Error("Conversion Error")
            }
        }
        Log.i(TAG, "safeApiCall: error")
        return Resource.Error("Unknown Error")
    }
}