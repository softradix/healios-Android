package com.softradix.healios.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.softradix.healios.model.Comments
import com.softradix.healios.model.Posts
import com.softradix.healios.model.user.Users
import com.softradix.healios.room.dao.CommentsDao
import com.softradix.healios.room.dao.PostsDao
import com.softradix.healios.room.dao.UsersDao
import com.softradix.healios.utils.Constants

@Database(
    entities = [Posts::class, Users::class, Comments::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostsDao
    abstract fun usersDao(): UsersDao
    abstract fun commentsDao(): CommentsDao


    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context?): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder(
                                context!!,
                                AppDatabase::class.java,
                                Constants.DB_NAME
                            )
                                .fallbackToDestructiveMigration()
                                .allowMainThreadQueries()
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }


}