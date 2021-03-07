package com.jskno.bingapitrackerbackend.controllers.newssearch;

import com.jskno.bingapitrackerbackend.javafxelements.HeadlineTableCell;
import com.jskno.bingapitrackerbackend.javafxelements.ProviderTableCell;
import com.jskno.bingapitrackerbackend.modalmessages.ConfirmationBox;
import com.jskno.bingapitrackerbackend.model.BingNews;
import com.jskno.bingapitrackerbackend.model.BingSearchResults;
import com.jskno.bingapitrackerbackend.restservices.BingNewsRestService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class NewsSearchController {

    private BingNewsRestService bingNewsRestService;

    @Autowired
    public NewsSearchController(BingNewsRestService bingNewsRestService) {
        this.bingNewsRestService = bingNewsRestService;
    }

    private TableView<BingNews> table;

    public Node start(String searchTerm) {
        table = new TableView<>();
        initialiseData(searchTerm);
        Parent root = getRoot();
        return root;
    }

    private Parent getRoot() {

        buildTable();

        VBox paneMain = new VBox();
        paneMain.setSpacing(10);
        paneMain.setPadding(new Insets(20));
        paneMain.getChildren().addAll(table);

        return paneMain;
    }

    private TableView<BingNews> buildTable() {
//        TableView<BingNews> table = new TableView<>();
        table.setEditable(false);
        table.setPlaceholder(new Label("Ningún presupuesto que mostrar"));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY );

        TableColumn<BingNews, BingNews> deleteCol = buildDeleteColumn();
//        TableColumn<BingNews, LocalDateTime> publishedDateCol = buildPublishedDateColumn();
        TableColumn<BingNews, BingNews> providerCol = buildProviderColumn();
        TableColumn<BingNews, BingNews> headlineCol = buildHeadlineColumn();

        table.getColumns().addAll(deleteCol, providerCol, headlineCol);

        return table;
    }

    private TableColumn<BingNews, BingNews> buildProviderColumn() {
        TableColumn<BingNews, BingNews> providerCol= new TableColumn<>("Provider");
        providerCol.setMinWidth(310);

        providerCol.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<BingNews>(cellData.getValue()));
        providerCol.setCellFactory(
                ProviderTableCell.forTableColumn());

        return providerCol;
    }

    private TableColumn<BingNews, BingNews> buildHeadlineColumn() {
        TableColumn<BingNews, BingNews> headlineCol = new TableColumn<>("Provider");
        headlineCol.setMinWidth(310);

        headlineCol.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<BingNews>(cellData.getValue()));
        headlineCol.setCellFactory(
                HeadlineTableCell.forTableColumn());

        return headlineCol;
    }



    private TableColumn<BingNews, BingNews> buildDeleteColumn() {
        TableColumn<BingNews, BingNews> deleteCol = new TableColumn<>("");
        deleteCol.setMinWidth(50);
        deleteCol.setPrefWidth(50);
        deleteCol.setMaxWidth(50);
        deleteCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        deleteCol.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("", getImageView());

            @Override
            protected void updateItem(BingNews bingNews, boolean empty) {
                super.updateItem(bingNews, empty);

                if (bingNews == null) {
                    setGraphic(null);
                    return;
                }

                deleteButton.setMaxSize(1, 1);
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> onDeleteAction(bingNews));
            }
        });
        return deleteCol;
    }

    private ImageView getImageView() {
        InputStream input = this.getClass().getResourceAsStream("/images/delete.png");
        Image image = new Image(input);
        return new ImageView(image);
    }

    private TableColumn<BingNews, LocalDateTime> buildPublishedDateColumn() {
        TableColumn<BingNews, LocalDateTime> updatedDateCol =
                new TableColumn("Published Date");
        updatedDateCol.setMinWidth(300);
        updatedDateCol.setPrefWidth(300);
        updatedDateCol.setMaxWidth(500);
        updatedDateCol.setCellValueFactory(bingNews -> {
            SimpleObjectProperty<LocalDateTime> property = new SimpleObjectProperty<>();
            property.setValue(bingNews.getValue().getDatePublished());
            return property;
        });
//        updatedDateCol.setCellFactory(
//                TextFieldTableCell.forTableColumn());
        updatedDateCol.setCellFactory(column -> new TableCell<>() {
            DateTimeFormatter myDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    // Format date.
                    setText(myDateFormatter.format(item));
                }
            }
        });
        return updatedDateCol;
    }

    private void initialiseData(String searchTerm) {
        BingSearchResults bingSearchResults = bingNewsRestService.searchNews(searchTerm);
        ObservableList<BingNews> bingNewsUIList = FXCollections.observableArrayList(
                bingSearchResults.getNews()
        );
        table.setItems(bingNewsUIList);
        table.getSelectionModel().selectFirst();
    }

    private void onDeleteAction(BingNews bingNews) {
        boolean confirm;
        confirm = ConfirmationBox.show(
                "Are you sure you want to delete this news?", "Confirmation",
                "Yes", "No");
        if (confirm) {
//            budgetService.deleteById(budgetId);
//            initialiseData();
        }
    }
}
