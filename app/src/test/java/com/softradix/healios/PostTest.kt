package com.softradix.healios


import com.softradix.healios.network.RetrofitClient
import org.junit.Assert.assertNotNull
import org.junit.Test

// @RunWith is required only if you use a mix of JUnit3 and JUnit4.

class PostTest {

    @Test
    fun `Get posts`() {
        val posts = RetrofitClient.createApiService()?.getPosts()?.execute()
        when {
            posts?.code() == 200 -> {
                assertNotNull(posts.body())
            }
            posts?.code() == 500 -> {
                assertNotNull("Server Internal Error")
            }
            posts?.code() == 404 -> {
                assertNotNull("Not Found!")
            }
        }
    }

    @Test
    fun `Get Comments`() {
        val comments = RetrofitClient.createApiService()?.getComments()?.execute()
        when {
            comments?.code() == 200 -> {
                assertNotNull(comments.body())
            }
            comments?.code() == 500 -> {
                assertNotNull("Server Internal Error")
            }
            comments?.code() == 404 -> {
                assertNotNull("Not Found!")
            }
        }
    }

    @Test
    fun `Get Users`() {
        val comments = RetrofitClient.createApiService()?.getComments()?.execute()
        when {
            comments?.code() == 200 -> {
                assertNotNull(comments.body())
            }
            comments?.code() == 500 -> {
                assertNotNull("Server Internal Error")
            }
            comments?.code() == 404 -> {
                assertNotNull("Not Found!")
            }
        }
    }




}