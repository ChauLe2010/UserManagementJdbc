package com.codegym.dao;

import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{

    public static final String JDBC_MYSQL_LOCALHOST_3306_USERDEMO = "jdbc:mysql://localhost:3306/userdemo";
    public static final String USERNAME = "test";
    public static final String PASSWORD = "test";
    public static final String SELECT_FROM_USERS = "select * from users";
    public static final String INSERT_INTO_USERS = "insert into users (name,email,country) values (?,?,?)";
    public static final String SELECT_FROM_USERS_WHERE_ID = "select * from users where id=?";
    public static final String UPDATE_USERS_SET_NAME_EMAIL_COUNTRY_WHERE_ID = "update users set name =?,email=?,country=?  where id=?";
    public static final String DELETE_FROM_USERS_WHERE_ID = "delete from users where id=?";

    public UserDAO() {
    }

    private Connection getConnectDB(){
        Connection connection=null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(JDBC_MYSQL_LOCALHOST_3306_USERDEMO,USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {


            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertUser(User user) {
        try {
            Connection connection = getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public User selectUser(int id) {
        User user=new User();

        try {
            Connection connection=getConnectDB();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_FROM_USERS_WHERE_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                int idUser=resultSet.getInt(1);
                String nameUser=resultSet.getString(2);
                String emaiUser=resultSet.getString(3);
                String countryUser=resultSet.getString(4);
                user=new User(idUser,nameUser,emaiUser,countryUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> users=new ArrayList<>();

        try {
            Connection connection=getConnectDB();
            PreparedStatement preparedStatement=connection.prepareStatement(SELECT_FROM_USERS);
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String email=resultSet.getString("email");
                String country=resultSet.getString("country");
                users.add(new User(id,name,email,country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean updateUser(User user)  {
        boolean rowUpdated=false;
        try {
            Connection connection = getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SET_NAME_EMAIL_COUNTRY_WHERE_ID);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setInt(4, user.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }catch(SQLException ex){
            ex.printStackTrace();

        }

        return rowUpdated;
    }
    @Override
    public boolean deleteUser(int id) {
        boolean rowDeleted=false;
        try {
            Connection connection=getConnectDB();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_USERS_WHERE_ID);
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }


}
