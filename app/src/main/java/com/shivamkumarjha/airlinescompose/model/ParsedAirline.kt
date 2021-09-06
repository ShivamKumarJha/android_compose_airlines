package com.shivamkumarjha.airlinescompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "airlines")
data class ParsedAirline(
    @PrimaryKey val id: String,
    val name: String,
    val trips: Int?,
    val airlineName: String,
    val country: String,
    val logo: String,
    val slogan: String,
    val headQuarters: String,
    val website: String,
    val established: Int,
    val totalPassengers: Int,
    val totalPages: Int,
    var page: Int? = null
)