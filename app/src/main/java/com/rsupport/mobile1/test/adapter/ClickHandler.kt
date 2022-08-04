package com.rsupport.mobile1.test.adapter

interface ClickHandler<T>{
    fun onClick(viewId: Int, model : T)
}