package com.example.enhaacomiclabs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.enhaacomiclabs.Interface.IRecyclerOnClick
import com.example.enhaacomiclabs.R
import com.example.enhaacomiclabs.common.Common
import com.example.enhaacomiclabs.model.ComicDto
import com.squareup.picasso.Picasso

class MyComicAdapter(internal val context: Context, internal val mangaList: ComicDto) :
    RecyclerView.Adapter<MyComicAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        internal var comic_image:ImageView
        internal var comic_title:TextView
        lateinit var iRecyclerOnClick: IRecyclerOnClick

        fun setClick(iRecyclerOnClick:IRecyclerOnClick){
            this.iRecyclerOnClick = iRecyclerOnClick;
        }

        init {
            comic_image = itemView.findViewById(R.id.comic_thumbnail) as ImageView
            comic_title = itemView.findViewById(R.id.comic_title) as TextView
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            iRecyclerOnClick.onClick(p0!!, adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyComicAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.comic_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyComicAdapter.MyViewHolder, position: Int) {

        Picasso.get().load(mangaList.data[position].thumbnailUrl).into(holder.comic_image)
        holder.comic_title.text = mangaList.data[position].title
        holder.setClick((object :IRecyclerOnClick{
            override fun onClick(view: View, position: Int) {
                Common.selected_comic = mangaList.data[position]
            }
        }))
    }

    override fun getItemCount(): Int {
        return mangaList.data.size
    }
}