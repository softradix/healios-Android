package com.softradix.healios.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.softradix.healios.model.Comments

@Dao
sealed interface CommentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComments(vararg comments: Comments)

    @Query("SELECT * FROM comments WHERE postId=:postId")
    fun getCommentsByPostId(postId: String): LiveData<List<Comments>>

    @Query("SELECT * FROM comments")
    fun getComments(): LiveData<List<Comments>>
}