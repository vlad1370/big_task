package by.itclass.prepared;

import by.itclass.model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/po_2211";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";
    public static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String query = "SELECT s.name, s.age, d.name FROM staff s LEFT JOIN department d ON s.department = d.id WHERE s.age > ? AND d.name LIKE ?";

        try (Connection cn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = cn.prepareStatement(query)) {
            List<Staff> staff = new ArrayList<>();
            ps.setInt(1, 20);
            ps.setString(2, "Dev%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String name = rs.getString(1);
                int age = rs.getInt("age");
                String department = rs.getString(3);
                staff.add(new Staff(name, age, department));
            }
            staff.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
