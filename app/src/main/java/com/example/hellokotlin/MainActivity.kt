package com.example.hellokotlin

import android.content.ContentValues.TAG
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {

    lateinit var myAdapter:MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view.setHasFixedSize(true)
        linearLayoutManager= LinearLayoutManager(this)
        recycler_view.layoutManager=linearLayoutManager

        getMyData()

    }

    private fun getMyData() {
        val BaseUrl = "https://jsonplaceholder.typicode.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)

        val retrofitData = retrofit.getData()

        retrofitData.enqueue(object : Callback<List<DataItem>?> {

            override fun onResponse(call: Call<List<DataItem>?>, response: Response<List<DataItem>?>) {

                val responseBody = response.body()!!

                myAdapter = MyAdapter(baseContext,responseBody)
                myAdapter.notifyDataSetChanged()

                recycler_view.adapter=myAdapter


            }

            override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure:" + t.message)

            }
        })

    }
}


