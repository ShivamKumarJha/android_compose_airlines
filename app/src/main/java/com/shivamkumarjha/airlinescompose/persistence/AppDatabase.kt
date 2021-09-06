package com.shivamkumarjha.airlinescompose.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shivamkumarjha.airlinescompose.model.ParsedAirline

@Database(
    entities = [
        ParsedAirline::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun airlineDao(): AirlineDao
}