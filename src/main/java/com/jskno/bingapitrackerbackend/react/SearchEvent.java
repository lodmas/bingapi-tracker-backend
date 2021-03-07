package com.jskno.bingapitrackerbackend.react;

import org.springframework.context.ApplicationEvent;

public class SearchEvent extends ApplicationEvent {

    private String textSearch;

    public SearchEvent(Object source, String textSearch) {
        super(source);
        this.textSearch = textSearch;
    }

    public String getTextSearch() {
        return textSearch;
    }
}
