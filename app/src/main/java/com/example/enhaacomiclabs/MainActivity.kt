package com.example.enhaacomiclabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.enhaacomiclabs.adapter.MainSliderAdapter
import com.example.enhaacomiclabs.adapter.MyComicAdapter
import com.example.enhaacomiclabs.common.Common
import com.example.enhaacomiclabs.retrofit.IComicAPI
import com.example.enhaacomiclabs.service.PicassoImageLoadingService
import dmax.dialog.SpotsDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import ss.com.bannerslider.Slider
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    internal var compositeDisposable = CompositeDisposable()
    internal lateinit var iComicAPI: IComicAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init api
        iComicAPI = Common.api

        Slider.init(PicassoImageLoadingService(this))

        //view setup
        recycler_comic.setHasFixedSize(true)
        recycler_comic.layoutManager = GridLayoutManager(this,2)

        swipe_refresh.setColorSchemeResources(R.color.green,android.R.color.holo_orange_dark,android.R.color.background_dark)
        swipe_refresh.setOnRefreshListener {
            if(Common.isConnectedToInternet(baseContext)) {
                fetchBanner()
                fetchComic()
            }else{
                Toast.makeText(baseContext,"Please Check Your Internet Collection",Toast.LENGTH_SHORT).show()
            }
        }

        //Default, load first time
        swipe_refresh.post {
            if(Common.isConnectedToInternet(baseContext)) {
//                fetchBanner()
                fetchComic()
            }else{
                Toast.makeText(baseContext,"Please Check Your Internet Collection",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchBanner() {
        compositeDisposable.add(iComicAPI.popularComic
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ comic ->
                banner_slider.setAdapter(MainSliderAdapter(comic))
            }, {
                Toast.makeText(baseContext, "Error when load banner", Toast.LENGTH_SHORT).show()
            }))

    }

    private fun fetchComic() {
        var dialog = SpotsDialog.Builder()
            .setContext(this)
            .setMessage("Please wait...")
            .build()

        if(!swipe_refresh.isRefreshing){
            dialog.show()
            compositeDisposable.add(iComicAPI.listComic
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ comicList ->
                    txt_comic.text = StringBuilder("List Comic (")
                        .append(comicList.data.size)
                        .append(")")
                    recycler_comic.adapter = MyComicAdapter(baseContext,comicList)
                    if(!swipe_refresh.isRefreshing)
                        dialog.dismiss()
                    swipe_refresh.isRefreshing = false
                }, {
                    thr ->
                    Toast.makeText(baseContext, ""+thr.message, Toast.LENGTH_SHORT).show()
                    if(!swipe_refresh.isRefreshing)
                        dialog.dismiss()
                    swipe_refresh.isRefreshing = false
                }))
        }
    }
}