package com.exequieltiglao.retro_omdb

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

import com.exequieltiglao.retro_omdb.adapter.SearchAdapter
import com.exequieltiglao.retro_omdb.api.ApiInterface
import com.exequieltiglao.retro_omdb.model.Search
import com.exequieltiglao.retro_omdb.model.SearchObjects

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val mSearchArrayList = ArrayList<SearchObjects>()

    private var mRecyclerView: RecyclerView? = null
    private var mSearchAdapter: SearchAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    private lateinit var search: EditText
    private lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search = findViewById(R.id.search_movie)

        mRecyclerView = findViewById(R.id.recyclerview)
        mLayoutManager = LinearLayoutManager(this)
        //  mLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false); //IF VERTICAL
        mSearchAdapter = SearchAdapter(mSearchArrayList)

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiInterface = retrofit.create(ApiInterface::class.java!!)

    }

    /* btn_search is clicked */
    fun getSearch(view: View) {

        getSearch()
        Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "getSearch: searching.... ")
    }

    fun getSearch() {

        val call = apiInterface.getSearch(search.text.toString(), "8eeefbee")

        call.enqueue(object : Callback<Search> {
            override fun onResponse(call: Call<Search>, response: Response<Search>) {

                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, " " + response.body()!!, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "onResponse: .... " + response.body()!!)
                }

                /* fetching data from internet then display */
                val searches = response.body()
                mSearchAdapter!!.searchObjectsArrayList(searches!!.search)
                getAdapterResult() //calls getAdapterResult, to be displayed in mRecyclerview

            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                Toast.makeText(this@MainActivity, " " + t.message, Toast.LENGTH_LONG).show()
                Log.d(TAG, "onFailure: failed.... " + t.message)

            }
        })

    }

    fun getAdapterResult() {
        mRecyclerView!!.layoutManager = mLayoutManager
        mRecyclerView!!.adapter = mSearchAdapter

        mRecyclerView!!.setHasFixedSize(true)
        mSearchAdapter!!.notifyDataSetChanged()
    }

    companion object {

        private const val TAG = "MainActivity"
        const val BASE_URL = "http://www.omdbapi.com/"
    }

}
