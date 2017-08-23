package sample;

import controllers.UserController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class Login extends Application {
    private Button loginButton ;
    private TextField userNameTextField;
    private PasswordField pswField;
    private Text holdText;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("MonitorServer");
        primaryStage.setScene(new Scene(root, 480, 270));
        primaryStage.show();
        init(root, primaryStage);
    }
    public void init(Parent root, Stage primaryStage) {
        this.loginButton = (Button) root.lookup("#button_login");
        this.userNameTextField = (TextField) root.lookup("#username_filed");
        this.pswField = (PasswordField) root.lookup("#psw_filed");
        this.holdText = (Text) root.lookup("#login_hold_text");
        this.loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserController user = new UserController(userNameTextField.getText(),pswField.getText());
                String loginResult = null;
                try {
                    loginResult = user.doLogin();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if(!loginResult.equals("successful")) {
                    holdText.setText(loginResult);
                }else{
                    Event.fireEvent(primaryStage, new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST ));
                    Index index = null;
                    try {
                        index = new Index(user);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        index.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }

}
