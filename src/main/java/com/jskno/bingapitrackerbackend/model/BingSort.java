package com.jskno.bingapitrackerbackend.model;

public class BingSort {

    private String name; // "Más recientes", "Mejor coincidencia"
    private String id;  // date, relevance
    private boolean isSelected;
    private String url;

    public BingSort() {
    }

    public BingSort(String name, String id, boolean isSelected, String url) {
        this.name = name;
        this.id = id;
        this.isSelected = isSelected;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
