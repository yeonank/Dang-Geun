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
        Log.e("I'm at HomeFragment", "1")
        return binding.root
        }


    private fun initRecycler() {
        swipeadapter = SwipeAdapter(requireContext())

        binding.rvProfile.adapter = swipeadapter

        binding.rvProfile.apply {
            layoutManager = LinearLayoutManager(context)

        }

        //+버튼 누르면
        binding.floatingActionButton.setOnClickListener{
            activity?.let{
                Log.e("you are here", "1")
                val intent = Intent(context, WriteActivity::class.java)
                startActivity(intent)
                Log.e("you are here", "2")
            }

            initRecycler()//완료 버튼 누르면 데이터 바인딩
        }

        val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")

        datas.apply {
            add(WriteData(1, "연정", "호롤롤로", System.currentTimeMillis()))
            /*add(SwipeData(img = R.drawable.placeholder, name = "jenny", age = "24"))
            add(SwipeData(img = R.drawable.placeholder, name = "jhon", age = "24"))
            add(SwipeData(img = R.drawable.placeholder, name = "ruby", age = "24"))
            add(SwipeData(img = R.drawable.placeholder, name = "yuna", age = "24"))*/

            swipeadapter.datas = datas//put writedata into swipeadater
            Log.e("at HomeFragment apply",swipeadapter.itemCount.toString())
            swipeadapter.notifyDataSetChanged()

        }
    }
}
