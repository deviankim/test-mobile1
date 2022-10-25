package com.rsupport.mobile1.test.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rsupport.mobile1.test.databinding.ActivityMainBinding
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.URL


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Single.create<Bitmap> {
            var url = URL("https://www.gettyimages.com/detail/photo/colleagues-problem-solving-at-computer-together-royalty-free-image/1028772240?adppopup=true")
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            it.onSuccess(bmp)
        }// 생산자 : 데이터를 생산하여 전달
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                { bmp ->
                    binding.imageView.setImageBitmap(bmp)
                },
                { error ->
                    Log.e("Main", error.toString())
                })// 소비자 : 데이터를 받아서 처리 (println)
    }
}