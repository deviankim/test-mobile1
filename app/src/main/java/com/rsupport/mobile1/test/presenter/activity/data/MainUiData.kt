package com.rsupport.mobile1.test.presenter.activity.data

data class MainUiData(
    val author: String?,
    val date: String?,
    val thumbnailUrl: String?
) {
    override fun toString(): String {
        return """
            Author    --> $author
            Date      --> $date
            Thumbnail --> $thumbnailUrl
        """.trimIndent()
    }
}
