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
import models.User;

public class RegisterPage {
    protected Stage stage;
    protected Scene scene;

    protected BorderPane rootNode;
    protected VBox vbox;
    protected HBox emailHBox, passHBox, confirmPassHBox;

    // components
    protected Label welcomeMessage, emailLabel, passLabel, confirmPassLabel;
    protected TextField emailTF;
    protected PasswordField passTF, confirmPassTF;
    protected Button registerBtn;

    protected void init() {
        rootNode = new BorderPane();
        scene = new Scene(rootNode, 500, 500);

        // create & style components
        welcomeMessage = new Label("Create an Account");
        welcomeMessage.setStyle("-fx-font-size: 24; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");

        emailLabel = new Label("Email: ");
        emailLabel.setStyle("-fx-font-size: 16; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");

        passLabel = new Label("Password: ");
        passLabel.setStyle("-fx-font-size: 16; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");

        confirmPassLabel = new Label("Confirm Password: ");
        confirmPassLabel.setStyle("-fx-font-size: 16; -fx-font-family: 'Kode Mono'; -fx-text-fill: black;");

        emailTF = new TextField();
        passTF = new PasswordField();
        confirmPassTF = new PasswordField();

        registerBtn = new Button("Register");
        registerBtn.setStyle("-fx-background-color: pink; -fx-border-color: white; "
                + "-fx-font-family: 'Kode Mono'; -fx-font-size: 14px; -fx-text-fill: #4E3434;");
        EventHandler<MouseEvent> registerEvent = new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
		       
		        User newUser = new User(emailTF.getText(), passTF.getText());
		        boolean registrationSuccessful = UserController.insertUser(newUser);

		        if (registrationSuccessful) {
		            // Registration successful, redirect to login page
		            LoginPage loginPage = new LoginPage(stage);
		            stage.setScene(loginPage.getScene());
		            stage.show();
		        } else {
		            // Registration failed, show an alert
		            Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Registration Error");
		            alert.setHeaderText(null);
		            alert.setContentText("User already exists or registration failed. Please try again.");
		            alert.showAndWait();
		        }
        	
			}
        };
        registerBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, registerEvent);

    }

    protected void setLayout() {
        emailHBox = new HBox(4);
        emailHBox.getChildren().addAll(emailLabel, emailTF);
        emailHBox.setAlignment(Pos.CENTER);

        passHBox = new HBox(4);
        passHBox.getChildren().addAll(passLabel, passTF);
        passHBox.setAlignment(Pos.CENTER);

        confirmPassHBox = new HBox(4);
        confirmPassHBox.getChildren().addAll(confirmPassLabel, confirmPassTF);
        confirmPassHBox.setAlignment(Pos.CENTER);

        vbox = new VBox(8);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(welcomeMessage, emailHBox, passHBox, confirmPassHBox, registerBtn);
        rootNode.setCenter(vbox);
    }

    // scene
    public Scene getScene() {
        return this.scene;
    }

    public RegisterPage(Stage stage) {
        init();
        setLayout();
        this.stage = stage;
        this.stage.setTitle("Register");
    }
}