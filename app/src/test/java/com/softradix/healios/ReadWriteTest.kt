package com.softradix.healios

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.softradix.healios.model.Comments
import com.softradix.healios.model.Posts
import com.softradix.healios.model.user.Address
import com.softradix.healios.model.user.Users
import com.softradix.healios.room.dao.CommentsDao
import com.softradix.healios.room.dao.PostsDao
import com.softradix.healios.room.dao.UsersDao
import com.softradix.healios.room.database.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class ReadWriteTest {
    private lateinit var postsDao: PostsDao
    private lateinit var commentsDao: CommentsDao
    private lateinit var usersDao: UsersDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries().build()
        postsDao = db.postDao()
        commentsDao = db.commentsDao()
        usersDao = db.usersDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInCommentsTable() {
        val address = Address("21 Walt", "NA", "Sydney", "245871")
        val user = Users(
            1, "Test User",
            "testBuddy21",
            "test21@gmail.com",
            address = address,
            "154613151",
        "http://www.google.com/")
        usersDao.insertUserDetails(user)
        val getUsers = usersDao.getUsers()
        assertThat(getUsers)
    }

    @Test
    @Throws(Exception::class)
    fun `writeUserAndRead In Users without the test postId`() {
        val comment = Comments(1, 11, "John", "johndoe@gmail.com", "NICE POST")
        commentsDao.insertComments(comment)
        val getComments = commentsDao.getComments()
        assertThat(getComments)
    }



    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInUsersTable() {
        val comment = Comments(1, 21, "John", "johndoe@gmail.com", "NICE POST")
        commentsDao.insertComments(comment)
        val getComments = commentsDao.getComments()
        assertThat(getComments)
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInPostsTable() {
        val user = Posts(1, 1, "Post Title", "post Body")
        postsDao.insertAll(user)
        val getPosts = postsDao.getPosts()
        assertThat(getPosts)
    }

}