package com.example.diary_recycler

import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diary_recycler.databinding.ActivityMainBinding
import com.example.diary_recycler.databinding.ItemSwipelistBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {
    lateinit var swipeadapter: SwipeAdapter
    val datas = mutableListOf<SwipeData>()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        binding.bottomNavigationView.selectedItemId = R.id.menu_name
        // HOME as default tab
        supportFragmentManager.beginTransaction().add(R.id.frame_layout,
            MainFragment(),"home"
        ).commit()
        // Launch app with HOME selected as default start tab
    }
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        when(p0.itemId){
            R.id.menu_name ->{
                val fragmentA = MainFragment()
                transaction.replace(R.id.frame_layout,fragmentA, "home")
            }
            R.id.menu_tag -> {
                val fragmentB = WriteFragment()
                transaction.replace(R.id.frame_layout,fragmentB, "chat")
            }
            R.id.menu_cal -> {
                val fragmentC = ProfileFragment()
                transaction.replace(R.id.frame_layout,fragmentC, "profile")
            }

        }
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
        return true
    }

}
