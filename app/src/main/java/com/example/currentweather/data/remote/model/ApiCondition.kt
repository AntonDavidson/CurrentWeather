package com.example.currentweather.data.remote.model

import com.google.gson.annotations.SerializedName


data class ApiCondition(
    @SerializedName("text") var text: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("code") var code: Int? = null
)