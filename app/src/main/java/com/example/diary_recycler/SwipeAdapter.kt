package com.example.diary_recycler

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diary_recycler.view.activity.PostActivity

class SwipeAdapter (private val context: Context) : RecyclerView.Adapter<SwipeAdapter.ViewHolder>() {

    var helper:SqliteHelper? = null
    var datas = mutableListOf<WriteData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_swipelist,parent,false)
        return ViewHolder(view)
        Log.e("*******************", "I'm here!1")
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bind(datas[position])
        val article:WriteData = datas.get(position)
        holder.setArticle(article)
        Log.e("*******************", "I'm here!2")
        holder.itemView.setOnClickListener{

        val intent = Intent(holder.itemView?.context, PostActivity::class.java)
        ContextCompat.startActivity(holder.itemView.context, intent, null)
    }}

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = itemView.findViewById(R.id.tv_rv_name)
        private val txtAge: TextView = itemView.findViewById(R.id.tv_rv_age)
        private val imgProfile: ImageView = itemView.findViewById(R.id.img_rv_photo)

        /*fun bind(item: WriteData) {
            txtName.text = item.name
            txtAge.text = item.age.toString()
            Glide.with(itemView).load(item.img).into(imgProfile)

        }*/

        fun setArticle(article:WriteData){
            txtName.text = article.title
            txtAge.text = article.content
            Log.e("*******************", "I'm here!3" + txtAge.text)
        }

    }


}