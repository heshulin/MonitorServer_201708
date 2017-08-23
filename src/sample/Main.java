package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class Main{

    private Stage primaryStage;
    public void start() throws Exception{

        primaryStage  = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene scene = new Scene(root, 1000, 600);
        scene.fillProperty();
        primaryStage.setScene(scene);
        ImageView imageView = (ImageView) root.lookup("#image");
        //Image image = new Image("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502960590013&di=558baaf00aadb5f44b5deb0d1e392e15&imgtype=jpg&src=http%3A%2F%2Fimg0.imgtn.bdimg.com%2Fit%2Fu%3D689488284%2C2945586013%26fm%3D214%26gp%3D0.jpg");
        //imageView.setImage(image);
        primaryStage.sizeToScene();
        //ListView listView = (ListView) root.lookup("#listview");

        primaryStage.show();

    }


//    public static void main(String[] args) {
//        launch(args);
//    }

}
