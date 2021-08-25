package com.softradix.healios.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.softradix.healios.HealiosApp
import com.softradix.healios.model.user.Users
import com.softradix.healios.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/** this viewModel is used to insertUserDetails to the database...
 * this viewModel will provide user details according to the post from Room DB
 */

class UserDetailViewModel : ViewModel() {
    private val api = RetrofitClient.createApiService()

    fun insertUserDetails(context: Context) {
        api?.getUsers()?.enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {

                if (response.isSuccessful) {
                    response.body()?.forEach {
                        HealiosApp.appDatabase?.usersDao()?.insertUserDetails(it)
                    }
                } else {
                    Toast.makeText(context, "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.localizedMessage}")
            }

        })
    }

    fun getUserById(userId: String): LiveData<Users>? {
        return HealiosApp.appDatabase?.usersDao()?.getUserById(userId = userId)
    }

}