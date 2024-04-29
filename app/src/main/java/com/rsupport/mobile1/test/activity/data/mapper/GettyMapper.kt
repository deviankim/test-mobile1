package com.rsupport.mobile1.test.activity.data.mapper

import com.rsupport.mobile1.test.activity.domain.model.UiGettyModel
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

fun Element.toUiGettyModel() : List<UiGettyModel> {

    val elements: List<Element> = this.select("img").drop(2) // 2번째부터 보여지게.

    return elements.map {
        UiGettyModel(
            src = it.attr("src"),
            title = it.attr("alt"),
            link = ""
        )
    }
}