package com.softradix.healios.ui.fragments.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.softradix.healios.R
import com.softradix.healios.adapters.PostListAdapter
import com.softradix.healios.base.BaseFragment
import com.softradix.healios.databinding.FragmentPostsBinding
import com.softradix.healios.model.Posts


class PostsFragment : BaseFragment<FragmentPostsBinding>() {
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    lateinit var mViewModel: PostViewModel
    private var postListAdapter: PostListAdapter? = null
    private var list: ArrayList<Posts> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this)[PostViewModel::class.java]
    }


    private fun getPosts() {
        mViewModel.getPosts()?.observe(viewLifecycleOwner) {
            Log.e("TAG", "attachObservers: ${it.size}")
            list.addAll(it)
            postListAdapter?.notifyItemRangeChanged(0, it.size)

        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getPosts()
        insertDataToDB()

    }

    private fun insertDataToDB() {
        mViewModel.insertPosts(requireContext())
    }

    private fun setupRecyclerView() {

        postListAdapter = PostListAdapter(requireContext(), list) {
            val action = PostsFragmentDirections.actionPostsFragmentToPostDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            adapter = postListAdapter

        }
    }


    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPostsBinding {
        return FragmentPostsBinding.bind(
            layoutInflater.inflate(
                R.layout.fragment_posts,
                container,
                false
            )
        )
    }

}