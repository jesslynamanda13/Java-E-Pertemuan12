package index;

import controller.UserController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.LoginPage;
import views.RegisterPage;

public class Main extends Application{
	protected DatabaseConnection dbConnection = new DatabaseConnection();
	public UserController userController = new UserController();
	public Main() {
		dbConnection.migrateTables();
		// migrate default users
		userController.insertDefaultUsers();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		LoginPage regist = new LoginPage(stage);
		stage.setScene(regist.getScene());
		stage.show();
		
	}

}
