package com.example.diary_recycler.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diary_recycler.R
import com.example.diary_recycler.SwipeAdapter
import com.example.diary_recycler.SwipeData
import com.example.diary_recycler.WriteData
import com.example.diary_recycler.databinding.FragmentHomeBinding
import com.example.diary_recycler.view.activity.WriteActivity
import java.text.SimpleDateFormat

class HomeFragment : Fragment() {
    lateinit var swipeadapter: SwipeAdapter
    val datas = mutableListOf<WriteData>()

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

        //relate
        binding.floatingActionButton.setOnClickListener{
            activity?.let{
                val intent = Intent(context, WriteActivity::class.java)
                startActivity(intent)
            }
        }

        val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")

        datas.apply {
            add(WriteData(1, "hello", "hi", System.currentTimeMillis()))
            /*add(SwipeData(img = R.drawable.placeholder, name = "jenny", age = "24"))
            add(SwipeData(img = R.drawable.placeholder, name = "jhon", age = "24"))
            add(SwipeData(img = R.drawable.placeholder, name = "ruby", age = "24"))
            add(SwipeData(img = R.drawable.placeholder, name = "yuna", age = "24"))*/

            swipeadapter.datas = datas//put writedata into swipeadater
            Log.e("errer",swipeadapter.itemCount.toString())
            swipeadapter.notifyDataSetChanged()

        }
    }
}
