package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Created by HeShulin on 2017/8/22.
 */
public class Ip{

    private Stage primaryStage;
    public void start() throws Exception{

        primaryStage  = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ip.fxml"));
        Scene scene = new Scene(root, 200, 600);
        scene.fillProperty();
        primaryStage.setScene(scene);

        primaryStage.sizeToScene();
        //ListView listView = (ListView) root.lookup("#listview");

        primaryStage.show();

    }


//    public static void main(String[] args) {
//        launch(args);
//    }

}