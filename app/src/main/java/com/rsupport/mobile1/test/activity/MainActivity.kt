package com.rsupport.mobile1.test.activity

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rsupport.mobile1.test.R
import com.rsupport.mobile1.test.activity.ui.GettyImageViewModel
import com.rsupport.mobile1.test.activity.ui.UiAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: GettyImageViewModel by viewModels()

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
        private var cachedQuery = ""

        override fun onQueryTextSubmit(p0: String?): Boolean {
            if (cachedQuery != p0) {
                p0?.trim()?.also { viewModel.uiActionCallback(UiAction.Search(it)) }
            }

            cachedQuery = p0 ?: ""

            return true
        }

        override fun onQueryTextChange(p0: String?): Boolean = true
    }
}