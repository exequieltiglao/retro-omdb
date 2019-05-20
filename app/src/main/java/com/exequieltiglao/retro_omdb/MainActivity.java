package com.exequieltiglao.retro_omdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.exequieltiglao.retro_omdb.adapter.SearchAdapter;
import com.exequieltiglao.retro_omdb.api.ApiInterface;
import com.exequieltiglao.retro_omdb.model.Search;
import com.exequieltiglao.retro_omdb.model.SearchObjects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String BASE_URL = "http://www.omdbapi.com/";

    private ArrayList<SearchObjects> mSearchArrayList = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private SearchAdapter mSearchAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    TextView results;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        results = findViewById(R.id.result);

        mRecyclerView = findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(this);
        mSearchAdapter = new SearchAdapter(mSearchArrayList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        getSearch();
    }

    public void getSearch() {

        Call<Search> call = apiInterface.getSearch("iron man", "8eeefbee");

        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {

                Search searches = response.body();
                mSearchAdapter.searchObjectsArrayList(searches.getSearch());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mSearchAdapter);

                mRecyclerView.setHasFixedSize(true);
                mSearchAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                results.setText(" " + t.getMessage());
                Toast.makeText(MainActivity.this, " " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: failed.... " + t.getMessage());
            }
        });


    }

}
