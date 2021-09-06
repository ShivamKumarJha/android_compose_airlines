package com.shivamkumarjha.airlinescompose.model

import com.google.gson.annotations.SerializedName

data class AirlinesResponse(
    @SerializedName("totalPassengers") val totalPassengers: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("data") val data: List<Data>,
    var page: Int? = null
)