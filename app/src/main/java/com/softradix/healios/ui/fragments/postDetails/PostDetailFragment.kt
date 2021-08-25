package com.softradix.healios.ui.fragments.postDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.softradix.healios.R
import com.softradix.healios.adapters.CommentListAdapter
import com.softradix.healios.base.BaseFragment
import com.softradix.healios.databinding.FragmentPostDetailBinding
import com.softradix.healios.model.Comments
import com.softradix.healios.model.Posts
import com.softradix.healios.model.user.Users
import com.softradix.healios.ui.fragments.posts.PostViewModel
import com.softradix.healios.viewModel.UserDetailViewModel


class PostDetailFragment : BaseFragment<FragmentPostDetailBinding>() {
    private lateinit var mViewModel: UserDetailViewModel
    private lateinit var mPostCommentModel: PostViewModel
    private var post: Posts? = null
    private var commentListAdapter: CommentListAdapter? = null
    private var list: ArrayList<Comments>? = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this)[UserDetailViewModel::class.java]
        mPostCommentModel = ViewModelProvider(this)[PostViewModel::class.java]
        insertPostDetailsIntoDB()
        insertCommentsIntoDB()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        post = arguments?.getSerializable("post") as Posts

        setupRecyclerView()

        setPostDetails(post)
        getCommentsByPostId(postId = post?.id.toString())
        getUserById(post?.userId.toString())

    }

    private fun setupRecyclerView() {
        commentListAdapter = CommentListAdapter(requireContext(), list = list)
        binding.recyclerView.apply {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            layoutManager = LinearLayoutManager(requireContext())
            adapter = commentListAdapter
        }
    }

    private fun insertCommentsIntoDB() {
        mPostCommentModel.insertComments(requireContext())
    }

    private fun setPostDetails(post: Posts?) {

        val postTitle = "${post?.title}"
        val postDescription = "${post?.body}"

        binding.tvPostHeading.text = postTitle
        binding.tvPostDescription.text = postDescription
    }

    private fun getUserById(userId: String) {
        mViewModel.getUserById(userId)?.observe(viewLifecycleOwner) {

            it?.let { setUserDetails(it) }

        }
    }

    private fun getCommentsByPostId(postId: String) {
        mPostCommentModel.getCommentsByPostId(postId)?.observe(viewLifecycleOwner) {
            it?.let {
                Log.e("TAG", "getCommentsByPostId: ${it.size}")

                list?.clear()
                list?.addAll(it)
                val totalComments = "Comments: ${list?.size}"
                binding.tvCommentsNo.text = totalComments
                commentListAdapter?.notifyItemRangeChanged(0, it.size)
            }
        }
    }

    private fun setUserDetails(users: Users) {
        binding.tvName.text = users.name
        binding.tvEmail.text = users.email
        val address = "${users.address.street}, ${users.address.city}, ${users.address.zipcode}"
        binding.tvAddress.text = address

    }

    private fun insertPostDetailsIntoDB() {
        mViewModel.insertUserDetails(requireContext())
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPostDetailBinding {
        return FragmentPostDetailBinding.bind(
            layoutInflater.inflate(
                R.layout.fragment_post_detail,
                container,
                false
            )
        )
    }
}