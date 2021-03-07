package com.jskno.bingapitrackerbackend.model;

public class BingQueryContext {

    private String originalQuery;
    private boolean adultIntent;

    public BingQueryContext() {
    }

    public BingQueryContext(String originalQuery, boolean adultIntent) {
        this.originalQuery = originalQuery;
        this.adultIntent = adultIntent;
    }

    public String getOriginalQuery() {
        return originalQuery;
    }

    public void setOriginalQuery(String originalQuery) {
        this.originalQuery = originalQuery;
    }

    public boolean isAdultIntent() {
        return adultIntent;
    }

    public void setAdultIntent(boolean adultIntent) {
        this.adultIntent = adultIntent;
    }
}
