package controllers;

import SocketServer.ReceiveThread;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;


public class Controller implements Initializable {
    //管理imageview的vector
    public static Vector<ImageView> imageViews = new Vector<>();
    //管理接收文件的线程的vector
    public static Vector<ReceiveThread> threads = new Vector<>();
    //管理imageview点击的vector
    public static Vector<Integer> intImage = new Vector<>();
    //管理当前点击的listview的值
    public static StringBuilder listip = null;
    //管理imageview右键点击放大的数组
    private static boolean[] rightClick = new boolean[4];
    @FXML
    ListView<String> listview;
    @FXML
    ImageView image0;
    @FXML
    ImageView image1;
    @FXML
    ImageView image2;
    @FXML
    ImageView image3;

    public static boolean[] image = new boolean[4];
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    public static Vector<Socket> ClientVector = new Vector<>();
    public static Vector<String> IpVector = new Vector<>();

    public void onClick() {
        System.out.println("qqqqq");
        //点击换窗口
        image0.onMouseClickedProperty();
        image0.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                switch (button) {
                    case PRIMARY:
                        System.out.println("image0");
                        intImage.set(0, 1);
                        intImage.set(1, 0);
                        intImage.set(2, 0);
                        intImage.set(3, 0);
                        break;
                    case SECONDARY:
                        if (rightClick[0] != true) {
                            image0.toFront();
                            System.out.println("Right Button Pressed");
                            image0.setFitHeight(960);
                            image0.setFitWidth(1200.0);
                            image0.setLayoutX(0);
                            image0.setLayoutX(0);
                            //视觉效果
                            image2.setLayoutY(0);
                            image3.setLayoutY(0);
                            rightClick[0] = true;
                            rightClick[1] = false;
                            rightClick[2] = false;
                            rightClick[3] = false;
                            break;
                        } else {
                            image0.setFitHeight(450.0);
                            image0.setFitWidth(600.0);
                            image0.setLayoutX(0);
                            image0.setLayoutX(0);
                            rightClick[0] = false;
                            //视觉效果
                            image2.setLayoutY(400);
                            image3.setLayoutY(400);
                            break;
                        }
                    case MIDDLE:
                        System.out.println("Middle Button Pressed");
                        break;
                    default:
                        System.out.println(button);
                }

            }
        });
        image1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                switch (button) {
                    case PRIMARY:
                        System.out.println("image1");
                        intImage.set(0, 0);
                        intImage.set(1, 1);
                        intImage.set(2, 0);
                        intImage.set(3, 0);
                        break;
                    case SECONDARY:
                        if (rightClick[1] != true) {
                            image1.toFront();
                            image1.setFitHeight(960);
                            image1.setFitWidth(1200.0);
                            image1.setLayoutX(0);
                            image1.setLayoutY(0);
                            //视觉效果
                            image2.setLayoutY(0);
                            image3.setLayoutY(0);
                            rightClick[0] = false;
                            rightClick[1] = true;
                            rightClick[2] = false;
                            rightClick[3] = false;
                            System.out.println("Right Button Pressed");
                            break;
                        } else {
                            image1.setFitHeight(450.0);
                            image1.setFitWidth(600.0);
                            image1.setLayoutX(600);
                            image1.setLayoutY(0);
                            rightClick[1] = false;
                            //视觉效果
                            image2.setLayoutY(400);
                            image3.setLayoutY(400);
                            break;
                        }

                    case MIDDLE:
                        System.out.println("Middle Button Pressed");
                        break;
                    default:
                        System.out.println(button);
                }


            }
        });
        image2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                switch (button) {
                    case PRIMARY:
                        System.out.println("image2");
                        intImage.set(0, 0);
                        intImage.set(1, 0);
                        intImage.set(2, 1);
                        intImage.set(3, 0);
                        break;
                    case SECONDARY:
                        if (rightClick[2] != true) {
                            image2.toFront();
                            image2.setFitHeight(960);
                            image2.setFitWidth(1200.0);
                            image2.setLayoutX(0);
                            image2.setLayoutY(0);

                            //视觉效果
                            image3.setLayoutY(0);
                            rightClick[0] = false;
                            rightClick[1] = false;
                            rightClick[2] = true;
                            rightClick[3] = false;
                            System.out.println("Right Button Pressed");
                            break;
                        } else {
                            image2.setFitHeight(450.0);
                            image2.setFitWidth(600.0);
                            image2.setLayoutX(0);
                            image2.setLayoutY(400);
                            rightClick[2] = false;
                            //视觉效果
                            image3.setLayoutY(400);
                            break;
                        }

                    case MIDDLE:
                        System.out.println("Middle Button Pressed");
                        break;
                    default:
                        System.out.println(button);
                }

            }
        });
        image3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton button = event.getButton();
                switch (button) {
                    case PRIMARY:
                        System.out.println("image3");
                        intImage.set(0, 0);
                        intImage.set(1, 0);
                        intImage.set(2, 0);
                        intImage.set(3, 1);
                        break;
                    case SECONDARY:
                        if (rightClick[3] != true) {
                            image3.toFront();
                            image3.setFitHeight(960);
                            image3.setFitWidth(1200.0);
                            image3.setLayoutX(0);
                            image3.setLayoutY(0);

                            //视觉效果
                            image2.setLayoutY(0);

                            rightClick[0] = false;
                            rightClick[1] = false;
                            rightClick[2] = false;
                            rightClick[3] = true;
                            System.out.println("Right Button Pressed");
                            break;
                        } else {
                            image3.setFitHeight(450.0);
                            image3.setFitWidth(600.0);
                            image3.setLayoutX(600.0);
                            image3.setLayoutY(400.0);
                            //视觉效果
                            image2.setLayoutY(400);

                            rightClick[3] = false;
                            break;
                        }
                    case MIDDLE:
                        System.out.println("Middle Button Pressed");
                        break;
                    default:
                        System.out.println(button);
                }

            }
        });

        //listview点击事件
        listview.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    listip = new StringBuilder(newValue);
                });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        onClick();
        intImage.add(0);
        intImage.add(0);
        intImage.add(0);
        intImage.add(0);

        imageViews.add(image0);
        imageViews.add(image1);
        imageViews.add(image2);
        imageViews.add(image3);
        BufferedImage bufferedImage = null;
        //设置默认图片
        try {
            bufferedImage = ImageIO.read(new FileInputStream("picture/stop.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        WritableImage writableImage = null;
        writableImage = SwingFXUtils.toFXImage(bufferedImage, writableImage);
        image0.setImage(writableImage);
        image1.setImage(writableImage);
        image2.setImage(writableImage);
        image3.setImage(writableImage);
        //配置socket
        try {
            serverSocket = new ServerSocket(5417);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("开始监听");
//更新页面的线程
        Thread stage = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int tmpSet = -1;
                    for (int i = 0; i < intImage.size(); i++) {
                        if (intImage.get(i) != 0) {

                            tmpSet = i;
                        }
                    }
                    //System.out.println(tmpSet);
                    //System.out.println(intImage.get(0)+"对对对就是我错了");
                    //System.out.println(listip + "www" + tmpSet);
                    if (listip != null && tmpSet != -1) {
                        System.out.println(listip + "www" + tmpSet);
                        System.out.println(threads.size() + "没有错就是我");
                        for (int i = 0; i < threads.size(); i++) {

                            if (threads.get(i).getSocket().getInetAddress().toString().equals(listip.toString())) {
                                for (int j = 0; j < threads.size(); j++) {
                                    if (threads.get(j).getImageView() == imageViews.get(tmpSet)) {
                                        threads.get(j).setSleep();
                                        threads.get(j).setImageView(null);
                                    }
                                }
                                System.out.println(listip + "www" + tmpSet);
                                threads.get(i).setImageView(imageViews.get(tmpSet));
                                System.out.println("我开始工作了");
                                threads.get(i).go();

                            }
                        }
                        for (int i = 0; i < intImage.size(); i++) {
                            intImage.set(i, 0);

                        }
                    }
                }
            }
        });
        stage.start();

        //接收数据,更新listview的线程
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("正在等待");
                        socket = serverSocket.accept();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for(int i=0;i<ClientVector.size();i++){
                        if(socket.getInetAddress().toString().equals(ClientVector.get(i).getInetAddress().toString())){
                            try {
                                socket.close();
                                socket=null;
                                System.out.println("我在正常工作哈~");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;

                        }
                    }
                    IpDataController ipDataController = new IpDataController();
                    if(!ipDataController.isExist(socket.getInetAddress().toString().replace("/","").replace("\\",""))){
                        socket=null;
                    }
                    if(socket==null){
                        continue;
                    }
                    System.out.println("有链接" + socket.getInetAddress().toString());
                    ClientVector.add(socket);
                    IpVector.add(socket.getInetAddress().toString());

                    //记录线程
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("listview控制器正在工作");
                            List<String> list = new ArrayList();
                            for (int i = 0; i < IpVector.size(); i++) {
                                list.add(IpVector.get(i));
                            }
                            //更新listview
                            ObservableList<String> strList = FXCollections.observableList(list);
                            listview.setItems(strList);
                        }
                    });

                    for (int indexi = 0; indexi < 4; indexi++) {
                        System.out.println("imageview控制器正在工作");
                        if (image[indexi] == false) {
                            image[indexi] = true;
                            //控制imageview的线程

                            ReceiveThread receiveThread = new ReceiveThread(listview, socket);
                            receiveThread.start();
                            threads.add(receiveThread);
                            break;


                        }
                    }

                }
            }
        });
        thread.start();


    }
}
