package com.rsupport.mobile1.test.activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.rsupport.mobile1.test.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView
        searchView.setOnQueryTextListener(OnQueryTextListenerImpl())
        searchView.queryHint = "이미지 검색어 입력"

        return super.onCreateOptionsMenu(menu)
    }

    private inner class OnQueryTextListenerImpl : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            // TODO: 해당 검색어로 Getty 이미지 크롤링 요청
            Log.d(TAG, "onQueryTextSubmit: $p0")
            return true
        }

        override fun onQueryTextChange(p0: String?): Boolean = true
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}