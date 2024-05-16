package com.rsupport.mobile1.test.viewModel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.remote.UseCase
import com.rsupport.mobile1.test.model.GettyItem
import com.rsupport.mobile1.test.ui.home.HomeAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: UseCase) : ViewModel(){
    private var listGetty: MutableList<GettyItem> = arrayListOf()

    private val _homeAdapter = MutableLiveData<HomeAdapter>().apply {
        value = HomeAdapter(listGetty)
    }

    val homeAdapter: LiveData<HomeAdapter> = _homeAdapter

    fun loadData() = viewModelScope.launch {
        useCase.getGettyList().let { response ->
            val doc = Jsoup.parseBodyFragment(response.string())
            val figure = doc.select("figure img")
            val size = figure.size

            for (i in 0 until size) {
                val src = figure[i].attr("src")
                val alt = figure[i].attr("alt")
                listGetty.add(GettyItem(img = src, contents = alt))
            }

            _homeAdapter.value = HomeAdapter(listGetty)
        }
    }
}