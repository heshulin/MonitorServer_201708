package Dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by 12390 on 2017/8/21.
 */
public class UserBean {
    private StringProperty userName;
    private StringProperty psw;
    private StringProperty userType;
    private StringProperty lastLoginTime;
    private StringProperty lastLoginAddress;

    public UserBean(Users user){
        this.userName = new SimpleStringProperty(user.getUserName());
        this.psw = new SimpleStringProperty(user.getPsw());
        this.userType = new SimpleStringProperty(user.getUserType());
        this.lastLoginAddress = new SimpleStringProperty(user.getLastLoginAddress());
        this.lastLoginTime = new SimpleStringProperty(user.getLastLoginTime());
    }
    public String getUserName() {
        return userName.get();
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getPsw() {
        return psw.get();
    }

    public StringProperty pswProperty() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw.set(psw);
    }

    public String getUserType() {
        return userType.get();
    }

    public StringProperty userTypeProperty() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType.set(userType);
    }

    public String getLastLoginTime() {
        return lastLoginTime.get();
    }

    public StringProperty lastLoginTimeProperty() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime.set(lastLoginTime);
    }

    public String getLastLoginAddress() {
        return lastLoginAddress.get();
    }

    public StringProperty lastLoginAddressProperty() {
        return lastLoginAddress;
    }

    public void setLastLoginAddress(String lastLoginAddress) {
        this.lastLoginAddress.set(lastLoginAddress);
    }
}
