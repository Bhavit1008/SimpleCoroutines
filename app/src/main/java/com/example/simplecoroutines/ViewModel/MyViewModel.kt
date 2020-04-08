package com.example.simplecoroutines.ViewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplecoroutines.DataBase.Entity
import com.example.simplecoroutines.DataBase.PracticeDao
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext

class MyViewModel(val db:PracticeDao) :ViewModel(){

    val code = MutableLiveData<String>()

    init {
        codeData()
    }

    private fun codeData() {
        viewModelScope.launch {
            insertData()
            delay(1000)
            code.value = getData()
        }
    }

    suspend fun getData() :String{
        return db.getAllValues().toString()
    }

    suspend fun insertData() {
        db.insert(Entity(data = "example"))
    }
}