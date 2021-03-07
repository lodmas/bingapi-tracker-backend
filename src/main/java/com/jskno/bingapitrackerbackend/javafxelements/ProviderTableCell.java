package com.jskno.bingapitrackerbackend.javafxelements;

import com.jskno.bingapitrackerbackend.model.BingNews;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.time.format.DateTimeFormatter;

public class ProviderTableCell<T> extends TableCell<BingNews, BingNews> {

    DateTimeFormatter myDateFormatter;

    private VBox graphic ;
    private Label provider ;
    private Label datePublished ;
    private TextField link ;

    public ProviderTableCell() {
        myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        graphic = new VBox();
        provider = createLabel("#66BB66");
        datePublished = createLabel("#79A8D8");
        link = createText("#FF8888");
        graphic.getChildren().addAll(provider, datePublished, link);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    public static Callback<TableColumn<BingNews, BingNews>, TableCell<BingNews, BingNews>>
                                        forTableColumn() {
        return (var1) -> new ProviderTableCell<>();
    }

    private final Label createLabel(String color) {
        Label label = new Label();
        VBox.setVgrow(label, Priority.ALWAYS);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setStyle("-fx-background-color: "+color+" ;");
        label.setAlignment(Pos.CENTER);
        return label ;
    }

    private final TextField createText(String color) {
        TextField textField = new TextField();
        VBox.setVgrow(textField, Priority.ALWAYS);
        textField.setMaxWidth(Double.MAX_VALUE);
        textField.setStyle("-fx-background-color: "+color+" ;");
        textField.setAlignment(Pos.CENTER);
        return textField ;
    }

    @Override
    public void updateItem(BingNews bingNews, boolean empty) {
        if (bingNews == null) {
            setGraphic(null);
        } else {
            provider.setText(bingNews.getBingProviders().get(0).getName());
            datePublished.setText(myDateFormatter.format(bingNews.getDatePublished()));
            link.setText(bingNews.getUrl());
            setGraphic(graphic);
        }
    }
}
