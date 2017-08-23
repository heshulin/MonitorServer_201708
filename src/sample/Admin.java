package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by HeShulin on 2017/8/22.
 */
public class Admin {
    private Stage primaryStage;
    public void start() throws Exception{

        primaryStage  = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("useradmin.fxml"));
        primaryStage.setTitle("MonitorServer");
        primaryStage.setScene(new Scene(root, 690, 740));
        primaryStage.show();

    }
}
