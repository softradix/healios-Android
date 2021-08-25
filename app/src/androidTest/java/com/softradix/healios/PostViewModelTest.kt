package com.softradix.healios

import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.softradix.healios.ui.fragments.posts.PostViewModel
import com.softradix.healios.ui.fragments.posts.PostsFragment
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostViewModelTest : TestCase(){
    private lateinit var postViewModel: PostViewModel

    @Before
    fun setupViewModel(){
        postViewModel = ViewModelProvider(getApplicationContext())[PostViewModel::class.java]
    }

    @Test
    fun changingViewModelValue_ShouldSetListViewItems() {
        val scenario = launchFragment<PostsFragment>()
        scenario.onFragment { fragment ->
            fragment.mViewModel.getPosts()?.observe(fragment.viewLifecycleOwner){
//                assertThat(fragment.mViewModel.getPosts()?.value?.size.toString())
            }
        }
    }

}