package com.jskno.bingapitrackerbackend.controllers.newstracker;

import com.jskno.bingapitrackerbackend.react.SearchEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NewsTrackerController {

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public NewsTrackerController(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    TextField searchText;

    public Node start() {
        initializeObjects();
        initialiseData();
        VBox root = getRoot();
        return root;
    }

    private VBox getRoot() {

        HBox actionSection = buildActionSection();

        VBox paneMain = new VBox();
        paneMain.setSpacing(10);
        paneMain.setPadding(new Insets(40, 40, 40, 40));
        paneMain.getChildren().addAll(actionSection);

        return paneMain;
    }

    private HBox buildActionSection() {

        Button btnSearch = new Button("Launch Search");
        btnSearch.setMinWidth(60);
        btnSearch.setOnAction(e -> btnSearch_Clicked());

        Button btnCustomSearch = new Button("Launch Custom Search");
        btnCustomSearch.setMinWidth(60);
        btnCustomSearch.setOnAction(e -> btnCustomSearch_Clicked());

        Button btnReport = new Button("Generate Report");
        btnReport.setMinWidth(60);
        btnReport.setOnAction(e -> btnReport_Clicked());

        Label searchLabel = new Label();
        searchText = new TextField();

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        HBox actionHBox = new HBox();
        actionHBox.setSpacing(8);
        actionHBox.getChildren().addAll(btnSearch, btnCustomSearch, btnReport,
                searchLabel, searchText);

        return actionHBox;
    }

    private void initialiseData() {
    }

    private void initializeObjects() {
    }


    private void btnSearch_Clicked() {
        applicationEventPublisher.publishEvent(new SearchEvent("NEWS_TRACKER_CONTROLLER",
                searchText.getText()));
    }

    private void btnCustomSearch_Clicked() {
    }

    private void btnReport_Clicked() {
    }
}
