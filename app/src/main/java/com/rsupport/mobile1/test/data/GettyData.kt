package com.rsupport.mobile1.test.data

data class GettyList(
    val list: ArrayList<GettyData>
)

data class GettyData (
    val alt : String, // 내용
    val src : String,  // 이미지 주소
    val width : Int,  //이미지 넓이
    val height : Int //이미지 높이
)