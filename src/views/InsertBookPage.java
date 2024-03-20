package views;

import controller.BookController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Book;

public class InsertBookPage {
    private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label titleLabel, authorLabel, genreLabel;
    private TextField titleField, authorField, genreField;
    private Button insertBtn;

    public InsertBookPage(Stage stage) {
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

        titleField = new TextField();
        authorField = new TextField();
        genreField = new TextField();

        insertBtn = new Button("Insert");
    }

    private void setLayout() {
        formGrid.add(titleLabel, 0, 0);
        formGrid.add(titleField, 1, 0);
        formGrid.add(authorLabel, 0, 1);
        formGrid.add(authorField, 1, 1);
        formGrid.add(genreLabel, 0, 2);
        formGrid.add(genreField, 1, 2);
        formGrid.add(insertBtn, 1, 3);

        rootNode.setCenter(formGrid);
    }

    private void setEventHandlers() {
        insertBtn.setOnAction(event -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();

            if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                System.out.println("Please fill in all fields.");
                return;
            }

            Book newBook = new Book(title, author, genre);
            boolean inserted = BookController.insertBook(newBook);

            if (inserted) {
            	HomePage homepage = new HomePage(stage); 
	            Scene homepageScene = homepage.getScene(); 
	            stage.setScene(homepageScene);
	            stage.show();
            } else {
                System.out.println("Failed to insert book.");
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
