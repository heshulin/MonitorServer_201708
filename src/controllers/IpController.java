package controllers;

import Dao.ClientIp;
import Dao.ClientIpBean;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import util.IpCheck;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Created by HeShulin on 2017/8/22.
 */
public class IpController  implements Initializable {
    @FXML
    private AnchorPane a;
    @FXML
    private TableView<ClientIpBean> tvv1;
    @FXML
    private TableColumn<ClientIpBean,String> tcc1;
    @FXML
    private TextField tff1;
    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Text t1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }
    private void init(){
        IpDataController ipDataController = new IpDataController();
        showList();
        tcc1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientIpBean, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ClientIpBean, String> param) {
                return param.getValue().clientIpProperty();
            }
        });
        this.tvv1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ClientIpBean>() {
            @Override
            public void changed(ObservableValue<? extends ClientIpBean> observable, ClientIpBean oldValue, ClientIpBean newValue) {
                if(newValue!=null) {
                    b1.setVisible(false);
                    tff1.setText(newValue.getClientIp());
                }
            }
        });
        this.b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reset();
            }
        });
        this.b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ip = tff1.getText();
                if(!IpCheck.ipCheck(ip)){
                    t1.setText("请检查Ip的合法性");
                }else{
                    if(ipDataController.isExist(ip)){
                        t1.setText("此ip已经存在");
                    }else{
                        reset();
                        String res = ipDataController.doUpdateIp(ip);
                        t1.setText(res);

                        showList();
                    }

                }
            }
        });
        this.b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ip = tff1.getText();
                String res = ipDataController.doDelIp(ip);
                t1.setText(res);
                reset();
                showList();
            }
        });
    }
    public void reset(){
        tff1.setEditable(true);
        t1.setText("");
        tff1.setText("");
        b2.setVisible(true);
        b1.setVisible(true);

    }
    public void showList(){
        ObservableList<ClientIpBean> observableList = FXCollections.observableArrayList();
        IpDataController ipDataController = new IpDataController();
        Vector<ClientIp> clientIps = ipDataController.getAllIp();
        for(int i=0;i<clientIps.size();i++){
            System.out.println(clientIps.get(i));
            ClientIpBean clientIpBean = new ClientIpBean(clientIps.get(i));
            observableList.add(clientIpBean);
        }
        int num = clientIps.size()/10;
        if(clientIps.size()%10!=0)
            num++;
        tvv1.setItems(observableList);
    }
}
