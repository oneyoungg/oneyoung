package top.oneyoung;

import java.sql.*;

/**
 * Jdbc
 *
 * @author oneyoung
 * @since 2022/3/9 2:57 PM
 */
public class Jdbc {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://rm-bp16t0liiu6z611g8.mysql.rds.aliyuncs.com:3306/deveta_content_rds?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false";
        String username = "devata_content_account";
        String password = "Deveta_123456";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("select * from role where id = ?")) {
            preparedStatement.setLong(1, 46);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                System.out.println(metaData);
                System.out.println();
                System.out.println(resultSet.getString("emp_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
