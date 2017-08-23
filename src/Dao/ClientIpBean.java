package Dao;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by HeShulin on 2017/8/23.
 */
public class ClientIpBean {
    private StringProperty ClientIp;

    public ClientIpBean(ClientIp clientIp){
        this.ClientIp = new SimpleStringProperty(clientIp.getClientIp());
    }

    public String getClientIp() {
        return ClientIp.get();
    }

    public StringProperty clientIpProperty() {
        return ClientIp;
    }

    public void setClientIp(String clientIp) {
        this.ClientIp.set(clientIp);
    }

}
