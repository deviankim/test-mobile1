package com.rsupport.mobile1.test.activity.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsupport.mobile1.test.activity.domain.model.UiGettyModel
import com.rsupport.mobile1.test.activity.domain.usecase.main.GetGettyItemUseCase
import com.rsupport.mobile1.test.activity.presentation.util.EventFlow
import com.rsupport.mobile1.test.activity.presentation.util.MutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val gettyItemUseCase: GetGettyItemUseCase
): ViewModel() {


    // 현재 page
    private var _page = MutableLiveData<Int>(1)
    val page: LiveData<Int> get() = _page

    // 검색 단어
    private var _phrase = MutableLiveData<String>("collaboration")
    val phrase: LiveData<String> get() = _phrase

    // 이미지 속성 정보들
    private var _gettyList = MutableLiveData<List<UiGettyModel>>()
    val gettyList: LiveData<List<UiGettyModel>> get() = _gettyList

    // 이미지 불러오기
    private var _getImage = MutableEventFlow<Boolean>()
    val getImage: EventFlow<Boolean> get() = _getImage


    // 검색 단어 Text
    var inputPhrase = MutableLiveData<String>("")

    init{
        doTask()
    }

    // 크롤링 하기
    fun doTask() {
        loadWebPage(buildGettyImagesUrl(_page.value!!, _phrase.value!!))
    }
    fun loadWebPage(url: String) {
        Timber.e("hello? ${url}")
        viewModelScope.launch(Dispatchers.IO) {
            gettyItemUseCase(url).onSuccess {
                _gettyList.postValue(it)
                _getImage.emit(true)
            }.onFailure {

            }
        }
    }
    fun buildGettyImagesUrl(page: Int, phrase: String): String {
        val baseUrl = "https://www.gettyimages.com/photos/collaboration"
        val assetType = "image"
        val sort = "best"

        return "$baseUrl?assettype=$assetType&sort=$sort&phrase=${phrase.replace(" ", "+")}&page=$page"
    }
    fun onClickPreviousPage()
    {
        _page.value = _page.value?.minus(1)
        doTask()
    }
    fun onClickNextPage(){
        _page.value = _page.value?.plus(1)
        doTask()
    }
    fun onClickSearch(){
        Timber.e("hello ${inputPhrase.value}")
        if(inputPhrase.value!!.isNotEmpty()) {
            _page.value = 1 // 1로 초기화
            loadWebPage(buildGettyImagesUrl(_page.value!!, inputPhrase.value!!))
        }

    }
}