package com.example.diary_recycler

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary_recycler.databinding.FragmentHomeBinding

class MainFragment : Fragment() {
    lateinit var swipeadapter: SwipeAdapter
    val datas = mutableListOf<SwipeData>()
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(
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
        swipeadapter = SwipeAdapter(requireContext())

        binding.rvProfile.adapter = swipeadapter

        binding.rvProfile.apply {
            layoutManager = LinearLayoutManager(context)

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
