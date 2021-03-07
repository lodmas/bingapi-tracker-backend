package com.jskno.bingapitrackerbackend.javafxelements;

import com.jskno.bingapitrackerbackend.model.BingNews;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.time.format.DateTimeFormatter;

public class HeadlineTableCell<T> extends TableCell<BingNews, BingNews> {

    DateTimeFormatter myDateFormatter;

    private VBox graphic ;
    private Label name ;
    private Label description ;

    public HeadlineTableCell() {
        graphic = new VBox();
        name = createLabel("#66BB66");
        description = createLabel("#79A8D8");
        graphic.getChildren().addAll(name, description);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    public static Callback<TableColumn<BingNews, BingNews>, TableCell<BingNews, BingNews>>
            forTableColumn() {

        return (var1) -> new HeadlineTableCell<>();
    }

    private final Label createLabel(String color) {
        Label label = new Label();
        VBox.setVgrow(label, Priority.ALWAYS);
        label.setMaxWidth(Double.MAX_VALUE);
        label.setStyle("-fx-background-color: "+color+" ;");
        label.setAlignment(Pos.CENTER);
        return label ;
    }

    @Override
    public void updateItem(BingNews bingNews, boolean empty) {
        if (bingNews == null) {
            setGraphic(null);
        } else {
            name.setText(bingNews.getName());
            description.setText(bingNews.getDescription());
            setGraphic(graphic);
        }
    }
}
