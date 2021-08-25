package com.softradix.healios.ui.fragments.posts

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.softradix.healios.HealiosApp
import com.softradix.healios.model.Comments
import com.softradix.healios.model.Posts
import com.softradix.healios.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



/** this viewModel is used to insertPosts to the Room database from api calls...
 * this viewModel will provide post details according to the post from Room DB
 * .... comments will be stored & provided according to the postId from the room db after api call
 */

class PostViewModel : ViewModel() {

    private val api = RetrofitClient.createApiService()

    fun insertPosts(context: Context) {
        api?.getPosts()?.enqueue(object : Callback<List<Posts>> {
            override fun onResponse(
                call: Call<List<Posts>>,
                response: Response<List<Posts>>
            ) {

                if (response.isSuccessful) {
                    response.body()?.forEach {
                        HealiosApp.appDatabase?.postDao()?.insertAll(it)
                    }
                } else {
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
            }
        })
    }

    fun insertComments(context: Context) {
        api?.getComments()?.enqueue(object : Callback<List<Comments>> {
            override fun onResponse(
                call: Call<List<Comments>>,
                response: Response<List<Comments>>
            ) {

                if (response.isSuccessful) {
                    response.body()?.forEach {
                        HealiosApp.appDatabase?.commentsDao()?.insertComments(it)
                    }
                } else {
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()

                }

            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.localizedMessage}")
            }

        })
    }


    fun getCommentsByPostId(postId: String): LiveData<List<Comments>>? {
        return HealiosApp.appDatabase?.commentsDao()?.getCommentsByPostId(postId)
    }

    fun getPosts(): LiveData<List<Posts>>? {
        return HealiosApp.appDatabase?.postDao()?.getPosts()
    }

}