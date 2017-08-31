package SocketServer;

import com.sun.istack.internal.NotNull;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HeShulin on 2017/8/17.
 */
public class ReceiveThread extends Thread {
    private boolean sleep = true;

    public void setSleep() {
        sleep = true;
    }

    public void go() {
        sleep = false;
    }

    private ImageView imageView = null;
    public void setImageView(ImageView imageview) {
        if(this.imageView!=null) {
            System.out.println("恩");
            ImageView tmpImageView = this.imageView;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("恩啊哈？");
                    //System.out.println("woshiindexaaaa");
                    BufferedImage bufferedImage = null;
                    try {
                        this.getClass().getResource("/");
                        bufferedImage = ImageIO.read(this.getClass().getResourceAsStream("/stop.jpg"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    WritableImage writableImage = null;
                    writableImage = SwingFXUtils.toFXImage(bufferedImage, writableImage);
                    tmpImageView.setImage(writableImage);
                    System.out.println("其实搞定了");
                    List<String> list = new ArrayList();

                    for (int i = 0; i < controllers.Controller.IpVector.size(); i++) {
                        list.add(controllers.Controller.IpVector.get(i));
                    }


                    ObservableList<String> strList = FXCollections.observableList(list);
                    listView.setItems(strList);

                }
            });
        }
        this.imageView = imageview;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    private Socket socket = null;
    private ListView<String> listView;

    public interface Listener {
        void get(BufferedImage bufferedImage, int indexi);
    }

    private Listener listener;

    public Socket getSocket() {
        return this.socket;
    }

    public ReceiveThread(ListView<String> listView, @NotNull Socket socket) {
        this.listView = listView;
        this.socket = socket;
        System.out.println(socket.getInetAddress().toString());

    }

    public String getIp() {
        return this.socket.getInetAddress().toString();
    }

    @Override
    public void run() {

        String temp = "D:\\" + socket.getInetAddress();
        temp = temp.replace(".", "");
        File fileDir = new File(temp);
        //System.out.println(temp);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            System.out.println("//不存在");
            fileDir.mkdir();
        }

        try {
            while (true) {
                //处理接受图片线程手动休眠
                System.out.print("");
                //System.out.println(Controller.listip+"+++++"+Controller.intImage.get(0));
                if (!sleep) {
                    System.out.println("我也开始工作了");

                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receiveFile(socket));
                    BufferedImage bufferedImage = null;
                    try {
                        bufferedImage = ImageIO.read(byteArrayInputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        final BufferedImage c = bufferedImage;
                        //System.out.println("我就试试");
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                WritableImage writableImage = null;
                                if(c!=null)
                                writableImage = SwingFXUtils.toFXImage(c, writableImage);
                                //System.out.println("我没错0");
                                imageView.setImage(writableImage);
                            }
                        });

                    } catch (Exception e) {

                        //处理正在接受数据时客户端强制断线
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("woshiindexaaaa");
                                BufferedImage bufferedImage = null;
                                try {
                                    this.getClass().getResource("/");
                                    bufferedImage = ImageIO.read(this.getClass().getResourceAsStream("/stop.jpg"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                ;
                                WritableImage writableImage = null;
                                writableImage = SwingFXUtils.toFXImage(bufferedImage, writableImage);
                                imageView.setImage(writableImage);
                                List<String> list = new ArrayList();

                                for (int i = 0; i < controllers.Controller.IpVector.size(); i++) {
                                    list.add(controllers.Controller.IpVector.get(i));
                                }


                                ObservableList<String> strList = FXCollections.observableList(list);
                                listView.setItems(strList);

                            }
                        });
                        for (int i = 0; i < controllers.Controller.ClientVector.size(); i++) {
                            if(controllers.Controller.ClientVector.get(i)==this.socket)
                            {
                                controllers.Controller.ClientVector.remove(i);
                            }
                        }
                        socket.close();
                        //System.out.println("我的锅");
                        this.stop();
                    }

                    //更新页面
//                    if (bufferedImage != null)
//                        try {
//                            ImageIO.write(bufferedImage, "jpg", new File(temp + "\\test1" + ".jpg"));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                }

            }

            //e.printStackTrace();

        }catch (Exception e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("woshiindexaaaa");
                    BufferedImage bufferedImage = null;
                    try {
                        this.getClass().getResource("/");
                        bufferedImage = ImageIO.read(this.getClass().getResourceAsStream("/stop.jpg"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ;
                    WritableImage writableImage = null;
                    writableImage = SwingFXUtils.toFXImage(bufferedImage, writableImage);
                    imageView.setImage(writableImage);
                }
            });


            System.out.println("我错了"+controllers.Controller.threads.size());
            List<String> list = new ArrayList();
            for (int i = 0; i < controllers.Controller.threads.size(); i++) {
                if(controllers.Controller.threads.get(i)==this)
                {
                    System.out.println("我错了"+controllers.Controller.ClientVector.size());
                    controllers.Controller.threads.remove(this);
                }
            }
            System.out.println("我错了"+controllers.Controller.threads.size());
            for (int i = 0; i < controllers.Controller.IpVector.size(); i++) {
                list.add(controllers.Controller.IpVector.get(i));
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ObservableList<String> strList = FXCollections.observableList(list);
                    listView.setItems(strList);

                }
            });
            this.stop();
            //e.printStackTrace();

        }


    }

    public byte[] receiveFile(Socket socket) {

        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //System.out.println(33);
            //e.printStackTrace();
        }
        byte[] lenbyte = new byte[4];
        try {
            int l = inputStream.read(lenbyte);
        } catch (IOException e) {

            controllers.Controller.IpVector.remove(socket.getInetAddress().toString());
            controllers.Controller.ClientVector.remove(socket);
            try {
                socket.close();
            } catch (IOException e1) {
                //e1.printStackTrace();
            }
            //System.out.println(22);
            //e.printStackTrace();
        }
        int len = ByteAndInt.byte2int(lenbyte);
        //System.out.println(l);
        int finallen = 0;
        //int len = ByteAndInt.byte2int(lenbyte);
        //System.out.println(len);
        byte[] finalbyte = new byte[len];

        //System.out.println(len+"q");
        /*while(finallen<=len)
        {
            System.out.println(finallen+"w"+len);
            byte[] tmpbyte=new byte[1024];

            int tmplen = inputStream.read(tmpbyte);
            System.out.println(tmplen);

            System.arraycopy(tmpbyte,0,finalbyte,finallen,tmplen);
            finallen = finallen+tmplen;
            if(finallen==len)
            {
                break;
            }
        }*/
        int readCount = 0;

        while (readCount < len) {
            //System.out.println(readCount + " " + len);
            try {
                readCount += inputStream.read(finalbyte, readCount, len - readCount);
            } catch (IOException e) {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println(11);
                break;
                //e.printStackTrace();
            }
            //System.out.println(in.available());
            //System.out.println("in");
        }
        //System.out.println(readCount + "*" + len);
        return finalbyte;
    }

}
