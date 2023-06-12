package com.rsupport.mobile1.test.activity.repository

import com.rsupport.mobile1.test.activity.data.Images
import io.reactivex.Observable
import io.reactivex.Single
import org.jsoup.Jsoup

/**
 * 게티이미지 페이지에서 이미지들을 가져온다.
 * Rx사용해 비동기처리, 네트워크 작업
 */
class ImageRepositoryImpl() : ImageListRepository {
    override fun requestImageElements(): Single<ArrayList<Images>> {
        return Single.fromObservable(
            Observable.create {
                val imageList = ArrayList<Images>()
                val doc = Jsoup.connect("https://www.gettyimages.com/photos/collaboration").get()
                var elementDatas = doc.select(".zF13TZBfnku4pwItorUU").select("div")
                elementDatas = elementDatas.select("article[class=KMczDbsqNlj9Y2shxCNZ] a picture source")

                for (element in elementDatas) {
                    val aTag = element.attr("srcset")
                    imageList.add(Images(aTag))
                }

                it.onNext(imageList)
                it.onComplete()
            }
        )
    }
}