package com.shivamkumarjha.airlinescompose.persistence

import androidx.room.*
import com.shivamkumarjha.airlinescompose.model.ParsedAirline
import kotlinx.coroutines.flow.Flow

@Dao
interface AirlineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAirline(airline: ParsedAirline)

    @Query("DELETE FROM airlines")
    suspend fun clearAirlines()

    @Delete
    suspend fun deleteAirline(airline: ParsedAirline)

    @Query("DELETE FROM airlines WHERE id = :id")
    suspend fun deleteAirline(id: Int)

    @Query("SELECT * from airlines ORDER BY established DESC")
    fun getAirlines(): Flow<List<ParsedAirline>>
}