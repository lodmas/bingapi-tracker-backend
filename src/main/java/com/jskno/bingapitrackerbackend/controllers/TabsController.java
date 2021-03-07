package com.jskno.bingapitrackerbackend.controllers;

import com.jskno.bingapitrackerbackend.controllers.newssearch.NewsSearchController;
import com.jskno.bingapitrackerbackend.controllers.newstracker.NewsTrackerController;
import com.jskno.bingapitrackerbackend.react.SearchEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TabsController {

    private NewsTrackerController newsTrackerController;
    private NewsSearchController newsSearchController;

    private TabPane tabPane;

    @Autowired
    public TabsController(NewsTrackerController newsTrackerController,
                          NewsSearchController newsSearchController) {
        this.newsTrackerController = newsTrackerController;
        this.newsSearchController = newsSearchController;
    }

    public void start(Stage stage) {
        Scene scene = new Scene(getRoot());

        stage.setScene(scene);
        stage.setTitle("News Tracker");

        stage.setMinWidth(1275);
        stage.setMinHeight(610);
        stage.setMaximized(true);

        stage.show();
    }

    private Parent getRoot() {
        this.tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Node newsTrackerNode = newsTrackerController.start();
        Tab newsTracker = new Tab("News Tracker", newsTrackerNode);
        tabPane.getTabs().addAll(newsTracker);

        return tabPane;
    }

    @EventListener
    public void onSearchEvent(SearchEvent searchEvent) {
        Node searchNode = newsSearchController.start(searchEvent.getTextSearch());
        Tab searchTab = new Tab("News Search", searchNode);
        searchTab.setClosable(true);
        tabPane.getTabs().add(searchTab);

        tabPane.getSelectionModel().select(1);
    }
}
