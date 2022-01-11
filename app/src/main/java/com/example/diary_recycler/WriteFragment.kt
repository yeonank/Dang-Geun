package com.example.diary_recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diary_recycler.databinding.ActivityWriteBinding
import com.example.diary_recycler.databinding.FragmentHomeBinding
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase

class WriteFragment: Fragment() {

    //val database = Firebase.database
    private val binding: ActivityWriteBinding by lazy {
        ActivityWriteBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val title_et= binding.titleBtn
        val content_et=binding.contentsBtn

        binding.button.setOnClickListener{
            title_et.text
          //  database.
        }
        return binding.root

}
}