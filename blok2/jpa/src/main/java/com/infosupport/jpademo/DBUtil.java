package com.infosupport.jpademo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.infosupport.jpademo.PropertiesReader.get;

public class DBUtil {

    public static void createDatabase() {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            // statement.execute("drop table if exists person;");
            // statement.execute("drop table if exists gender;");
            // statement.execute("create table person(name varchar(255) null, age int null, genderId int null);");
            // statement.execute("create table gender(id int null, name varchar(255) null);");
            // statement.execute("insert into gender(id, name) values (1, 'Man')");
            // statement.execute("insert into gender(id, name) values (2, 'Vrouw')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(get("database.url"), get("database.user"), get("database.password"));
    }
}
