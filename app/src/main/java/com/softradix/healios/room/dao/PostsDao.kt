package com.softradix.healios.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softradix.healios.model.Posts

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg posts: Posts)

    @Query("SELECT * FROM Posts")
    fun getPosts(): LiveData<List<Posts>>


}