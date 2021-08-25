package com.softradix.healios.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softradix.healios.model.user.Users

@Dao
sealed interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDetails(vararg users: Users)

    @Query("SELECT * FROM Users")
    fun getUsers(): LiveData<List<Users>>

    @Query("SELECT * FROM Users WHERE id= :userId")
    fun getUserById(userId: String): LiveData<Users>


}
