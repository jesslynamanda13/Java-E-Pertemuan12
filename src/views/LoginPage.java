package views;

import controller.UserController;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginPage {
	// declare atribut
	protected Stage stage;
	protected Scene scene;
	
	protected BorderPane rootNode;
	protected VBox vbox;
	protected HBox emailHBox, passHBox;
	
	// components
	protected Label welcomeMessage, emailLabel, passLabel;
	protected TextField emailTF, passTF;
	protected Button loginBtn;
	
	protected void init() {
		rootNode = new BorderPane();
		scene = new Scene(rootNode, 500, 500);
		
		// get FontUrl
		String fontUrl = "https://fonts.googleapis.com/css2?family=Kode+Mono:wght@400..700&display=swap";
		rootNode.getStylesheets().add(fontUrl);
		// create & style components
		welcomeMessage = new Label("Welcome to Java!");
		welcomeMessage.setStyle("fx-font-size: 24; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");
		
		emailLabel = new Label("Email: ");
		emailLabel.setStyle("fx-font-size: 16; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");
		
		passLabel = new Label("Password: ");
		passLabel.setStyle("fx-font-size: 16; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");
		
		emailTF = new TextField();
		passTF = new PasswordField();
		
		loginBtn = new Button("Login");
		loginBtn.setStyle("-fx-background-color: pink; -fx-border-color: white; "
	    		+ "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
		
		EventHandler<MouseEvent> loginEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				 String email = emailTF.getText();
			     String password = passTF.getText();

			     boolean loggedIn = UserController.loginUser(email, password);

			        if (loggedIn) {
			            HomePage homepage = new HomePage(stage); 
			            Scene homepageScene = homepage.getScene(); 
			            stage.setScene(homepageScene);
			            stage.show();
			        } else {
			        	Alert alert = new Alert(Alert.AlertType.ERROR);
			            alert.setTitle("Login Error");
			            alert.setHeaderText(null);
			            alert.setContentText("Password mismatch/account doesn't exist");
			            alert.showAndWait();
			        }
			}
			
		};
		loginBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, loginEvent);
	}
	
	protected void setLayout() {
		emailHBox = new HBox(4);
		
		emailHBox.getChildren().addAll(emailLabel, emailTF);
		emailHBox.setAlignment(Pos.CENTER);
		
		passHBox = new HBox(4);
		passHBox.getChildren().addAll(passLabel, passTF);
		passHBox.setAlignment(Pos.CENTER);
		
		
		vbox = new VBox(8);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(welcomeMessage, emailHBox, passHBox, loginBtn);
		rootNode.setCenter(vbox);
	}
	
	// scene
	public Scene getScene() {
		return this.scene;
	}
	
	public LoginPage(Stage stage) {
		init();
		setLayout();
		this.stage = stage;
		this.stage.setTitle("Login");
	}

}
