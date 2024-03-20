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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class DeleteBookPage {
    private Stage stage;
    private Scene scene;
    private BorderPane rootNode;
    private GridPane formGrid;
    private Label titleLabel;
    private ComboBox<String> titleComboBox;
    private Button deleteBtn;

    public DeleteBookPage(Stage stage) {
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

        titleComboBox = new ComboBox<>();

        deleteBtn = new Button("Delete");

        // Fetch all titles from the BookController and populate the ComboBox
        List<String> titles = BookController.getAllTitles();
        titleComboBox.setItems(FXCollections.observableArrayList(titles));
    }

    private void setLayout() {
        formGrid.add(titleLabel, 0, 0);
        formGrid.add(titleComboBox, 1, 0);
        formGrid.add(deleteBtn, 1, 1);

        rootNode.setCenter(formGrid);
    }

    private void setEventHandlers() {
        deleteBtn.setOnAction(event -> {
            String title = titleComboBox.getValue();

            boolean deleted = BookController.deleteBook(title);

            if (deleted) {
            	HomePage homepage = new HomePage(stage); 
	            Scene homepageScene = homepage.getScene(); 
	            stage.setScene(homepageScene);
	            stage.show();
            } else {
            	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Fail to delete");
	            alert.setHeaderText(null);
	            alert.setContentText("Someting wrong happens.");
	            alert.showAndWait();
            }
        });
    }

    public Scene getScene() {
        return scene;
    }
}
