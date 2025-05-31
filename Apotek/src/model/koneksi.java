package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static final String URL = "jdbc:mysql://localhost:3306/db_apotek";
    private static final String USER = "root"; // ganti jika user kamu beda
    private static final String PASS = "";     // ganti jika MySQL kamu pakai password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
