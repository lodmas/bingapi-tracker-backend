package com.jskno.bingapitrackerbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class BingNews {

    private String name;

    private String url;

    private BingImage image;

    private String description;

    @JsonProperty("provider")
    private List<BingProvider> bingProviders;

    private LocalDateTime datePublished;

    public BingNews() {
    }

    public BingNews(String name, String url, BingImage image, String description,
                    List<BingProvider> bingProviders, LocalDateTime datePublished) {
        this.name = name;
        this.url = url;
        this.image = image;
        this.description = description;
        this.bingProviders = bingProviders;
        this.datePublished = datePublished;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BingImage getImage() {
        return image;
    }

    public void setImage(BingImage image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BingProvider> getBingProviders() {
        return bingProviders;
    }

    public void setBingProviders(List<BingProvider> bingProviders) {
        this.bingProviders = bingProviders;
    }

    public LocalDateTime getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDateTime datePublished) {
        this.datePublished = datePublished;
    }
}
