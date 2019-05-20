package com.exequieltiglao.retro_omdb.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    @SerializedName("Search")
    private List<SearchObjects> search;

    public List<SearchObjects> getSearch() {
        return search;
    }

    public void setSearch(List<SearchObjects> search) {
        this.search = search;
    }
}
