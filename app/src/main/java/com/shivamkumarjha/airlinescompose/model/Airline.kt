package com.shivamkumarjha.airlinescompose.model

import com.google.gson.annotations.SerializedName

data class Airline(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("logo") val logo: String,
    @SerializedName("slogan") val slogan: String,
    @SerializedName("head_quaters") val headQuarters: String,
    @SerializedName("website") val website: String,
    @SerializedName("established") val established: Int
)