package org.muieer.java.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class HikariDataSourcePoolExample {

    public static void main(String[] args) throws Exception {

        FileSystem fileSystem = FileSystem.get(new Configuration());
        Properties props = new Properties();
        props.load(fileSystem.open(new Path("./src/main/resources/hikari.properties")));
        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);

        try (
                Connection connection = ds.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.execute("insert into books values (2, 'test', 'test', 2023)");
            ResultSet resultSet = statement.executeQuery("select * from books where id=2");

            while (resultSet.next()) {
                System.out.println(resultSet.getLong("id"));
                System.out.println(resultSet.getString("title"));
                System.out.println(resultSet.getString("authors"));
                System.out.println(resultSet.getInt("year"));
            }

            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
