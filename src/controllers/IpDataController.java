package controllers;

import Dao.ClientIp;
import Dao.Users;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by HeShulin on 2017/8/23.
 */
public class IpDataController {
    private static Connection connection;
    private  JDBCUtil jdbcUtil;
    public  Vector<ClientIp> getAllIp(){
        jdbcUtil = JDBCUtil.getInstance();
        this.connection = jdbcUtil.getConnection();
        String sql = "select * from client;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            Vector<ClientIp> clientIpV = new Vector<ClientIp>();
            if (resultSet != null) {
                //System.out.println("我不是空");
                while(resultSet.next()){
                    ClientIp clientIp = new ClientIp();
                    //System.out.println(resultSet.getString("clientip"));
                    clientIp.setClientIp(resultSet.getString("clientip"));
                    clientIpV.add(clientIp);
                }
            }
            return clientIpV;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public  String doUpdateIp(String ip) {
        String sql = "update client set clientip ='" + ip + "';";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return "修改成功";
        } catch (SQLException e) {
            e.printStackTrace();
            return "failed";
        }

    }
    public  String doDelIp(String ip){
        String sql = "delete from client where clientip='" + ip + "'";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return "删除成功";
        } catch (SQLException e) {
            e.printStackTrace();
            return "failed";
        }

    }
}
