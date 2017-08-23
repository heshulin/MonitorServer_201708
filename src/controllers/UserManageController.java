package controllers;

import Dao.UserBean;
import Dao.Users;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

/**
 * Created by 12390 on 2017/8/20.
 */
public class UserManageController implements Initializable {
    @FXML
    private TextField user_manage_username;
    @FXML
    private PasswordField user_manage_psw;
    @FXML
    private PasswordField user_manage_repsw;
    @FXML
    private Button user_manage_add;
    @FXML
    private Button user_manage_update;
    @FXML
    private Button user_manage_del;
    @FXML
    private Button user_manage_reset;
    @FXML
    private  TextField user_manage_type;
    @FXML
    private Pagination user_manage_pagination;
    @FXML
    private TableView<UserBean> user_manage_table;
    @FXML
    private TableColumn<UserBean, String> user_manage_table_col1;
    @FXML
    private TableColumn<UserBean, String> user_manage_table_col2;
    @FXML
    private TableColumn<UserBean, String> user_manage_table_col3;
    @FXML
    private TableColumn<UserBean, String> user_manage_table_col4;
    @FXML
    private TableColumn<UserBean, String> user_manage_table_col5;
    @FXML
    private Text manage_hold_text;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
    }
    public void init(){
        user_manage_table_col1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserBean, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UserBean, String> param) {
                return param.getValue().userNameProperty();
            }
        });
        user_manage_table_col2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserBean, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UserBean, String> param) {
                return param.getValue().pswProperty();
            }
        });
        user_manage_table_col3.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserBean, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UserBean, String> param) {
                return param.getValue().userTypeProperty();
            }
        });
        user_manage_table_col4.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserBean, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UserBean, String> param) {
                return param.getValue().lastLoginAddressProperty();
            }
        });
        user_manage_table_col5.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserBean, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UserBean, String> param) {
                return param.getValue().lastLoginTimeProperty();
            }
        });
        user_manage_pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                showList(param);
                return user_manage_table;
            }
        });
        reset();
        this.user_manage_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName = user_manage_username.getText();
                String psw = user_manage_psw.getText();
                String rePsw = user_manage_repsw.getText();
                String userType = user_manage_type.getText();
                if(userName==null||userName.length()==0||psw==null||psw.length()==0||!psw.equals(rePsw)){
                    manage_hold_text.setText("请检查用户名密码的合法性");
                }else{
                    String res = UserController.doInsertUser(userName,psw,userType);
                    manage_hold_text.setText(res);
                    reset();
                    showList(1);
                }
            }
        });
        this.user_manage_update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName = user_manage_username.getText();
                String psw = user_manage_psw.getText();
                String rePsw = user_manage_repsw.getText();
                String userType = user_manage_type.getText();
                if(psw==null||!psw.equals(rePsw)){
                    manage_hold_text.setText("请检查用户名密码的合法性");
                }else{
                    String res = UserController.doUpdateUser(userName,psw,userType);
                    manage_hold_text.setText(res);
                    reset();
                    showList(1);
                }
            }
        });
        this.user_manage_del.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userName = user_manage_username.getText();
                String res = UserController.doDelUser(userName);
                manage_hold_text.setText(res);
                reset();
                showList(1);
            }
        });
        this.user_manage_reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reset();
            }
        });
        this.user_manage_table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserBean>() {
            @Override
            public void changed(ObservableValue<? extends UserBean> observable, UserBean oldValue, UserBean newValue) {
                if(newValue!=null) {
                    user_manage_add.setVisible(false);
                    user_manage_username.setText(newValue.getUserName());
                    user_manage_username.setEditable(false);
                    user_manage_psw.setText(newValue.getPsw());
                    user_manage_type.setText(newValue.getUserType());
                    user_manage_del.setVisible(true);
                    user_manage_update.setVisible(true);
                }
            }
        });
    }
    public void reset(){
        user_manage_username.setEditable(true);
        user_manage_username.setText("");
        user_manage_psw.setText("");
        user_manage_repsw.setText("");
        user_manage_type.setText("");
        user_manage_update.setVisible(false);
        user_manage_del.setVisible(false);
        user_manage_add.setVisible(true);

    }
    public void showList(Integer page){
        ObservableList<UserBean> observableList = FXCollections.observableArrayList();
        Vector<Users> users = UserController.getAllUsers();
        for(int i=0;i<users.size();i++){
            UserBean userBean = new UserBean(users.get(i));
            observableList.add(userBean);
        }
        user_manage_pagination.setCurrentPageIndex(1);
        int num = users.size()/10;
        if(users.size()%10!=0)
            num++;
        user_manage_pagination.setPageCount(num);
        //user_manage_pagination.setMaxPageIndicatorCount(10);
        user_manage_table.setItems(observableList);

    }
}
