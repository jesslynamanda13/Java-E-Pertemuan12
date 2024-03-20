package views;

import java.util.List;

import controller.BookController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Book;

public class HomePage {
    // declare attributes
    protected Stage stage;
    protected Scene scene;

    protected BorderPane rootNode;
    protected VBox vbox;
    protected HBox buttonHBox;
    protected Button editBtn, deleteBtn, insertBtn;
    protected TableView<Book> tableView;
    protected ObservableList<Book> bookList;

    protected void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 600, 400);
       
        List<Book> allBooks = BookController.getAllBooks();
        bookList = FXCollections.observableArrayList(allBooks);
        
        
        tableView = new TableView<>();
        
        // Create table columns
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        
        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        
        tableView.getColumns().addAll(titleColumn, authorColumn, genreColumn);
        tableView.setItems(bookList);
        
        editBtn = new Button("Edit");
        editBtn.setStyle("-fx-background-color: lightblue; -fx-border-color: white; "
                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
        
        deleteBtn = new Button("Delete");
        deleteBtn.setStyle("-fx-background-color: red; -fx-border-color: white; "
                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
        
        insertBtn = new Button("Insert");
        insertBtn.setStyle("-fx-background-color: lightgreen; -fx-border-color: white; "
                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
        
        EventHandler<MouseEvent> redirectInsert = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				InsertBookPage newpage = new InsertBookPage(stage); 
	            Scene newpageScene = newpage.getScene(); 
	            stage.setScene(newpageScene);
	            stage.show();
			}
        };
        insertBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectInsert);
        
        EventHandler<MouseEvent> redirectUpdate = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				UpdateBookPage newpage = new UpdateBookPage(stage); 
	            Scene newpageScene = newpage.getScene(); 
	            stage.setScene(newpageScene);
	            stage.show();
			}
        };
        editBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectUpdate);
        
        EventHandler<MouseEvent> redirectDelete = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				DeleteBookPage newpage = new DeleteBookPage(stage); 
	            Scene newpageScene = newpage.getScene(); 
	            stage.setScene(newpageScene);
	            stage.show();
			}
        };
        deleteBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, redirectDelete);
    }
    
    

    protected void setLayout() {
        vbox = new VBox(8);
        vbox.getChildren().addAll(tableView);
        
        buttonHBox = new HBox(10);
        buttonHBox.getChildren().addAll(editBtn, deleteBtn, insertBtn);

        rootNode.setCenter(vbox);
        rootNode.setBottom(buttonHBox);
    }

    // scene
    protected Scene getScene() {
        return this.scene;
    }

    public HomePage(Stage stage) {
        init();
        setLayout();
        this.stage = stage;
        this.stage.setTitle("Home Page");
    }
}
