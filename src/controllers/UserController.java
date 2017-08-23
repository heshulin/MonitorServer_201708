package controllers;

import Dao.Users;
import util.JDBCUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class UserController {


    private String userName;
    private String psw;
    private Integer userType;
    private static Connection connection;
    private JDBCUtil jdbcUtil;
    public UserController(String userName, String psw){
        this.userName = userName;
        this.psw = psw;
        jdbcUtil = JDBCUtil.getInstance();
        this.connection = jdbcUtil.getConnection();
    }
    public String doLogin() throws SQLException, UnknownHostException {
        String sql = "select * from users where username='" + this.userName + "' and password='" + this.psw + "';";
        PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){


                this.userType = rs.getInt("usertype");
                System.out.println("是我是我"+this.userType);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql = "update users set lastloginipaddress='"
                    + InetAddress.getLocalHost().getHostAddress()
                    + "',lastlogintime='" +df.format(new Date()) +"' where username= '"
                    + this.userName + "';";
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            PreparedStatement preparedStatement1 = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement1.executeUpdate();
            return "successful";
        }else{
            return "useename or password error";
        }
    }

    public static String doInsertUser(String userName,String psw, String userType) {
        String sql1 = "select * from users where username='" + userName  + "';";
        PreparedStatement preparedStatement1 = null;
        try {
            preparedStatement1 = connection.prepareStatement(sql1);
            ResultSet rs = preparedStatement1.executeQuery();
            if(!rs.next()) {
                String sql = "insert into users(username,password,usertype) values ('" + userName + "','" + psw + "'," + new Integer(userType) + ");";
                //System.out.println(sql);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                return "success";
            }else{
                return "该用户名已存在";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "failed";
        }

    }

    public static Vector<Users> getAllUsers() {
        String sql = "select * from users;";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            Vector<Users> users = new Vector<Users>();
            if (resultSet != null) {

                while(resultSet.next()){
                    Users users1 = new Users();
                    users1.setUserName(resultSet.getString("username"));
                    users1.setPsw(resultSet.getString("password"));
                    Integer type = resultSet.getInt("usertype");

                    users1.setUserType(type.toString());
                    users1.setLastLoginAddress(resultSet.getString("lastloginipaddress"));
                    users1.setLastLoginTime(resultSet.getString("lastlogintime"));
                    users.add(users1);
                }
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String doUpdateUser(String userName,String psw, String userType) {
        String sql = "update users set password ='" + psw + "',usertype='" +new Integer(userType) + "'where username='" + userName + "'";
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

    public static String doDelUser(String userName){
        String sql = "delete from users where username='" + userName + "'";
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
    public Integer getUserType() {
        return userType;
    }
    public String getUserName() {
        return userName;
    }
}
