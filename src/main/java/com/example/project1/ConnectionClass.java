package com.example.project1;

import java.sql.*;
public class ConnectionClass  {
    public Connection connection;
    public Connection getConnection()
    {
        try
        {
//          Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/USERS","root","root");
            System.out.println(connection);
        }
        catch(SQLException e)
        {
            throw new IllegalStateException("Cannot find driver in class path");
        }
        catch(Exception e1){}
        System.out.println(connection);
        return connection;
    }
    public static void main(String args[]) {}
}

