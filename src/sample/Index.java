package sample;

import controllers.UserController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

/**
 * Created by 12390 on 2017/8/16.
 */
public class Index {
    private String userName;
    private Integer userType;
    private Text indexLTText;
    private ImageView lButton;
    private ImageView rButton;
    private final String greeting = "欢迎你，管理员 ";
    private ImageView mButton;
    private Main main;
    private Text text;
    private Text text1;
    private Text text2;
    private Admin admin;
    private Ip ip;
    Index(UserController user) throws IOException {
        String hostIp = InetAddress.getLocalHost().getHostAddress();

        main = new Main();
        this.userName = user.getUserName();
        this.userType = user.getUserType();

    }
    public void start()throws IOException{
        Stage indexStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("index.fxml"));
        indexStage.setTitle("MonitorServer");
        indexStage.setScene(new Scene(root, 800, 450));
        indexStage.show();
        indexStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        init(root,indexStage);

    }
    public void init(Parent root,Stage indexStage) throws UnknownHostException {
        this.indexLTText = (Text)root.lookup("#index_lt_text");
        this.lButton = (ImageView)root.lookup("#index_l_button");
        this.mButton = (ImageView)root.lookup("#index_m_button");
        this.rButton = (ImageView)root.lookup("#index_r_button");
        this.text = (Text)root.lookup("#r_text");
        this.text1 = (Text)root.lookup("#l_text");
        this.text2 = (Text)root.lookup("#m_text");
        admin = new Admin();
        indexLTText.setText(this.greeting+this.userName);
        ip = new Ip();
        this.lButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //Event.fireEvent(indexStage, new WindowEvent(indexStage, WindowEvent.WINDOW_CLOSE_REQUEST ));
                try {
                    //System.out.println(clientIps.size());
                    main.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        if(userType!=0){
            this.mButton.setVisible(false);
            this.text2.setVisible(false);
            this.rButton.setVisible(false);
            this.text.setVisible(false);
            this.lButton.setLayoutX(this.mButton.getLayoutX());
            this.lButton.setLayoutY(this.mButton.getLayoutY());
            this.text1.setLayoutX(this.text2.getLayoutX());
            this.text1.setLayoutY(this.text2.getLayoutY());
        }else{
            this.mButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        ip.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            this.rButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        admin.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
