package com.shivamkumarjha.airlinescompose.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("_id") val _id: String,
    @SerializedName("name") val name: String,
    @SerializedName("trips") val trips: Int?,
    @SerializedName("airline") val airline: List<Airline>,
    @SerializedName("__v") val __v: Int
)