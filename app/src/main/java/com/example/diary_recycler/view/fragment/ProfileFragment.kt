package com.example.diary_recycler.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.diary_recycler.ProfileAdapter
import com.example.diary_recycler.R
import com.example.diary_recycler.SwipeData
import com.example.diary_recycler.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var swipeadapter: ProfileAdapter
    val datas = mutableListOf<SwipeData>()
    private val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        initRecycler()
        return binding.root
    }


    private fun initRecycler() {
        swipeadapter = ProfileAdapter(requireContext())

        binding.recyclerView.adapter = swipeadapter

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context,3)

        }

        datas.apply {
            add(SwipeData(img = R.drawable.placeholder, name = "mary", age = "24"))
            add(SwipeData(img = R.drawable.placeholder, name = "jenny", age = "24"))
            add(SwipeData(img = R.drawable.placeholder, name = "jhon", age = "24"))
            add(SwipeData(img = R.drawable.placeholder, name = "ruby", age = "24"))
            add(SwipeData(img = R.drawable.placeholder, name = "yuna", age = "24"))

            swipeadapter.datas = datas
            Log.e("errer",swipeadapter.itemCount.toString())
            swipeadapter.notifyDataSetChanged()

        }
    }
}

