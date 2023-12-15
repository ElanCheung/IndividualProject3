package edu.farmingdale.individualproject3.database

import androidx.room.*

@Entity(tableName = "progress_logs")
data class ProgressLogKid(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "activity") val activity: String,
    @ColumnInfo(name = "score") val score: Int,
    //Add more fields as needed
)
