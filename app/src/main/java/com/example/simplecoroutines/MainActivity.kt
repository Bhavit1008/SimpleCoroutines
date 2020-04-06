package com.example.simplecoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val RESULT_1 : String = "RESULT OF TASK1"
    private val RESULT_2: String = "RESULT OF TASK2"
    var count :Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_result.setOnClickListener {
            CoroutineScope(IO).launch {
                apiCall1()
            }
        }
    }
    private suspend fun apiCall1() {
        val result1 = task1()
        setTextonMain(result1)
    }

    private suspend fun task1() :String{
        delay(2000)
        return RESULT_1
    }

    private suspend fun setTextonMain(input:String){
        withContext(Main){
            setNew(input)
        }
    }

    private suspend fun setNew(input :String){
        val str = textview.text.toString() + "\n$input"
        textview.text = str
        Log.d("hello","API called")
    }

}
