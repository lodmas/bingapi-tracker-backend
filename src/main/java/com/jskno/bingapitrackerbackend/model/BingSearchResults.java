package com.jskno.bingapitrackerbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BingSearchResults {

    @JsonProperty("_type")
    private String type;

    private String readLink;

    private BingQueryContext queryContext;

    private int totalEstimatedMatches;

    private List<BingSort> sort;

    @JsonProperty("value")
    private List<BingNews> news;

    public BingSearchResults() {
    }

    public BingSearchResults(String type, String readLink, BingQueryContext queryContext,
                             int totalEstimatedMatches, List<BingSort> sort, List<BingNews> news) {
        this.type = type;
        this.readLink = readLink;
        this.queryContext = queryContext;
        this.totalEstimatedMatches = totalEstimatedMatches;
        this.sort = sort;
        this.news = news;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReadLink() {
        return readLink;
    }

    public void setReadLink(String readLink) {
        this.readLink = readLink;
    }

    public BingQueryContext getQueryContext() {
        return queryContext;
    }

    public void setQueryContext(BingQueryContext queryContext) {
        this.queryContext = queryContext;
    }

    public int getTotalEstimatedMatches() {
        return totalEstimatedMatches;
    }

    public void setTotalEstimatedMatches(int totalEstimatedMatches) {
        this.totalEstimatedMatches = totalEstimatedMatches;
    }

    public List<BingSort> getSort() {
        return sort;
    }

    public void setSort(List<BingSort> sort) {
        this.sort = sort;
    }

    public List<BingNews> getNews() {
        return news;
    }

    public void setNews(List<BingNews> news) {
        this.news = news;
    }
}
