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


    private var _page = MutableLiveData<Int>()
    val page: LiveData<Int> get() = page


    private var _getExtData = MutableLiveData<ExtData>()
    val getExtData: LiveData<ExtData> get() = _getExtData
    
    private var _gettyList = MutableLiveData<List<UiGettyModel>>()
    val gettyList: LiveData<List<UiGettyModel>> get() = _gettyList

    // 설정 페이지
    private var _getImage = MutableEventFlow<Boolean>()
    val getImage: EventFlow<Boolean> get() = _getImage

    init{
        doTask()
    }

    // 크롤링 하기
    fun doTask() {
        loadWebPage(buildGettyImagesUrl(page, phrase))
    }
    fun loadWebPage(url: String) {
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
        val license = "rf,rm"

        return "$baseUrl?assettype=$assetType&sort=$sort&phrase=${phrase.replace(" ", "+")}&license=$license&page=$page"
    }
}