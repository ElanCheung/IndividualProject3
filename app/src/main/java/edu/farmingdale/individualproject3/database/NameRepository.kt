package edu.farmingdale.individualproject3.database
import android.content.Context
import androidx.room.Room


class NameRepository private constructor(context: Context) {
    private val database: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "game_database.db"
    ).build()

    private val userDao = database.userDao()

    companion object {
        @Volatile
        private var instance: NameRepository? = null

        fun getInstance(context: Context): NameRepository =
            instance ?: synchronized(this) {
                instance ?: NameRepository(context).also { instance = it }
            }
    }

    fun addUser(user: User) {
        userDao.addUser(user)
    }

    fun getUserById(id: Int): User? {
        return userDao.getUser(id.toLong())
    }

    fun getAllUsers(): List<User> {
        return userDao.getAll()
    }

    fun getProgressLogsForUser(userId: Int): List<ProgressLogKid> {
        return userDao.getProgressLogsForUser(userId)
    }

    fun findByName(fname: String, lname: String): User? {
        return userDao.findByName(fname, lname)
    }

    fun updateUser(usr: User) = userDao.update(usr)

    fun deleteUser(usr: User) = userDao.delete(usr)
}