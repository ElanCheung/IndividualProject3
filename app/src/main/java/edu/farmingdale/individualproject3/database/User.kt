package edu.farmingdale.individualproject3.database

import androidx.room.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    //Choose between parent or kid account
    @ColumnInfo(name = "account_type") val accountType: String
) {
    @Ignore
    constructor(firstName: String?, lastName: String?, accountType: String) : this(null, firstName, lastName, accountType)
}
