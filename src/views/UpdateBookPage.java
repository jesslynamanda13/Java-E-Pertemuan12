package views;

import controller.BookController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Book;

import java.util.List;

public class UpdateBookPage {
    private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label titleLabel, authorLabel, genreLabel;
    private ComboBox<String> titleComboBox;
    private TextField authorField, genreField;
    private Button updateBtn;

    public UpdateBookPage(Stage stage) {
        this.stage = stage;
        init();
        setLayout();
        setEventHandlers();
    }

    private void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 400, 200);
        
        formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.setPadding(new Insets(20));

        titleLabel = new Label("Title:");
        authorLabel = new Label("Author:");
        genreLabel = new Label("Genre:");

        titleComboBox = new ComboBox<>();
        authorField = new TextField();
        genreField = new TextField();

        updateBtn = new Button("Update");

        // Fetch all titles from the BookController and populate the ComboBox
        List<String> titles = BookController.getAllTitles();
        titleComboBox.setItems(FXCollections.observableArrayList(titles));
    }

    private void setLayout() {
        formGrid.add(titleLabel, 0, 0);
        formGrid.add(titleComboBox, 1, 0);
        formGrid.add(authorLabel, 0, 1);
        formGrid.add(authorField, 1, 1);
        formGrid.add(genreLabel, 0, 2);
        formGrid.add(genreField, 1, 2);
        formGrid.add(updateBtn, 1, 3);

        rootNode.setCenter(formGrid);
    }

    private void setEventHandlers() {
        updateBtn.setOnAction(event -> {
            String title = titleComboBox.getValue();
            String author = authorField.getText();
            String genre = genreField.getText();

            boolean updated = BookController.updateBook(title, author, genre);

            if (updated) {
            	HomePage homepage = new HomePage(stage); 
	            Scene homepageScene = homepage.getScene(); 
	            stage.setScene(homepageScene);
	            stage.show();
            } else {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Update Error");
	            alert.setHeaderText(null);
	            alert.setContentText("Something wrong happens.");
	            alert.showAndWait();
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
