package com.example.simplecoroutines.ViewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext

class MyViewModel :ViewModel(){

    val code = MutableLiveData<Int>()

    fun runTask(){
        viewModelScope.launch {
            codeData()
        }
    }

    private fun codeData(){
        code.value = 0xfff
    }

}