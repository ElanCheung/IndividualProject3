package edu.farmingdale.individualproject3.database

import androidx.room.*
import androidx.room.OnConflictStrategy.*

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<User>

    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM users WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Query("SELECT * FROM progress_logs WHERE user_id = :userId")
    fun getProgressLogsForUser(userId: Int): List<ProgressLogKid>

    @Insert
    fun insertAll(vararg users: User)

    @Insert(onConflict = REPLACE)
    fun addUser(user: User): Long

    @Insert
    fun addProgressLog(log: ProgressLogKid)

    @Query("SELECT * FROM users WHERE uid = :id")
    fun getUser(id: Long): User?

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}