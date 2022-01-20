package com.example.diary_recycler.view.fragment

import android.content.Intent
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
import com.example.diary_recycler.view.activity.SettingActivity
import com.example.diary_recycler.view.activity.WriteActivity

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
        binding.imageButton.setOnClickListener{
            val settingActivity =  SettingActivity()
            val intent = Intent(context, settingActivity::class.java)
            /////////////////////
            startActivity(intent)
        }
        return binding.root
    }


    private fun initRecycler() {
        swipeadapter = ProfileAdapter(requireContext())

        binding.recyclerView.adapter = swipeadapter

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context,3)

        }

        datas.apply {
            add(SwipeData(img =null, name = "mary", age = "24"))
            add(SwipeData(img =null, name = "jenny", age = "24"))
            add(SwipeData(img =null, name = "jhon", age = "24"))
            add(SwipeData(img =null, name = "ruby", age = "24"))
            add(SwipeData(img =null, name = "yuna", age = "24"))

            swipeadapter.datas = datas
            Log.e("errer",swipeadapter.itemCount.toString())
            swipeadapter.notifyDataSetChanged()

        }
    }
}

