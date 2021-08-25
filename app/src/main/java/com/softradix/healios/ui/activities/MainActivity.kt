package com.softradix.healios.ui.activities

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.softradix.healios.base.BaseActivity
import com.softradix.healios.databinding.ActivityMainBinding
import com.softradix.healios.ui.fragments.posts.PostViewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var mPostCommentModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        mPostCommentModel = ViewModelProvider(this)[PostViewModel::class.java]

    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

}