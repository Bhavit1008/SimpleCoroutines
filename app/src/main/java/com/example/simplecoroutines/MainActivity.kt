package com.example.simplecoroutines

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.simplecoroutines.DataBase.MyDatabase
import com.example.simplecoroutines.ViewModel.MyViewModel
import com.example.simplecoroutines.ViewModel.SampleViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {

    var model :MyViewModel? = null
    private val RESULT_1 : String = "RESULT OF TASK1"
    private val RESULT_2: String = "RESULT OF TASK2"
    var count :Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val Source = MyDatabase.getInstance(application).sampleDao
        val viewModelFactory = SampleViewModelFactory(Source)
        val sampleViewModel =
            ViewModelProviders.of(
                this, viewModelFactory
            ).get(MyViewModel::class.java)



        btn_result.setOnClickListener {
            sampleViewModel.code.observe(this, Observer {
                textview.text = "Data from Database -> ${it} \n"
            })
//            model!!.code.observe(this,object:Observer<String>{
//                override fun onChanged(t: String?) {
//                    if (t != null) {
//                        setNew(t)
//                    }
//                }
//            })
//            CoroutineScope(IO).launch {
//                apiCall1()
//            }

        }
    }
    private suspend fun apiCall1() {
        val result1 = task1()
        //setTextonMain(result1)
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

    private fun setNew(input :String){
        val str = textview.text.toString() + "\n$input"
        textview.text = str
        Log.d("hello","API called")
    }


}
