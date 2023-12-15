package edu.farmingdale.individualproject3.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, ProgressLogKid::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}