package com.ding.kotlin.jeptack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {

    var headerTxt = MutableLiveData<String>()
    var contentTxt = MutableLiveData<String>()

    fun setContentText(text:String?){
        text?.let {
            contentTxt.value = it
        }
    }

    fun setHeaderText(text:String?){
        text?.let {
            headerTxt.value = it
        }
    }
}