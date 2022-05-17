package com.example.enhaacomiclabs.adapter

import com.example.enhaacomiclabs.model.ComicDto
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class MainSliderAdapter(private val bannerList:ComicDto):SliderAdapter() {
    override fun getItemCount(): Int {
        return bannerList.data.size
    }

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) {
        imageSlideViewHolder!!.bindImageSlide(bannerList.data[position].thumbnailUrl)
    }

}