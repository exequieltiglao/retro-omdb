package com.exequieltiglao.retro_omdb.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.exequieltiglao.retro_omdb.R;
import com.exequieltiglao.retro_omdb.model.SearchObjects;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private static final String TAG = "SearchAdapter";

    private List<SearchObjects> searchObjectsArrayList;

    private Context context;

    public SearchAdapter(List<SearchObjects> mSearchObjectArrayList) {
        if (mSearchObjectArrayList == null) {
            this.searchObjectsArrayList = new ArrayList<>();
            Log.d(TAG, "SearchAdapter: .... make not null");
        } else {
            this.searchObjectsArrayList = mSearchObjectArrayList;
            Log.d(TAG, "SearchAdapter: .... null");
        }

    }

    public void searchObjectsArrayList(List<SearchObjects> searchArrayList) {
        this.searchObjectsArrayList = searchArrayList;
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView title, year, imdbID, type;
        ImageView poster;

        SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.search_title);
            year = itemView.findViewById(R.id.year);
            imdbID = itemView.findViewById(R.id.imdbID);
            type = itemView.findViewById(R.id.type);
            poster = itemView.findViewById(R.id.poster);

        }
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list, parent, false);
        return new SearchViewHolder(view);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        SearchObjects item = searchObjectsArrayList.get(position);
        holder.title.setText("Title: \t " + item.getTitle());
        holder.year.setText("Year: \t " + item.getYear());
        holder.imdbID.setText("imdbID: \t" + item.getImdbID());
        holder.type.setText("Type: \t" + item.getType());
//        holder.poster.setText(item.getPoster());

        /* NOT WORKING */
//        Glide.with(context)
//                .load(item.getPoster())
//                .into(holder.poster);

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + searchObjectsArrayList.size());
        return searchObjectsArrayList.size();
    }

}
