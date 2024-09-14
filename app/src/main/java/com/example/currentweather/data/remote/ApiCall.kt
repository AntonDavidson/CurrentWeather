package com.example.currentweather.data.remote


import android.util.Log
import retrofit2.Response
import java.io.IOException
private const val TAG="ApiCall"
abstract class ApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    Log.i(TAG, "safeApiCall: response success $resultResponse")
                    return Resource.Success(resultResponse)
                }
                // In case body is null for a successful response
                Log.i(TAG, "safeApiCall: response body is null")
                Resource.Error("Empty Response Body")
            } else {
                Log.i(TAG, "safeApiCall: response not successful")
                Resource.Error("Error Code: ${response.code()}")  // Handle unsuccessful responses
            }
        } catch (ex: Exception) {
            Log.e(TAG, "safeApiCall: exception $ex", ex)
            when (ex) {
                is IOException -> Resource.Error("Network Failure")
                else -> Resource.Error("Conversion Error")
            }
        }
    }
}