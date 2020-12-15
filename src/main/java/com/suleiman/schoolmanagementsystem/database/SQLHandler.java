package com.suleiman.schoolmanagementsystem.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLHandler {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement addTeacher;
    private static PreparedStatement insertStudent;
    private static PreparedStatement updateStudentFeesById;
    private static PreparedStatement selectStudentNameById;
    private static PreparedStatement selectStudentfeesPaidById;
    private static PreparedStatement selectAllTeachers;
    private static PreparedStatement selectAllStudents;
    private static PreparedStatement getTotalEarned;
    private static PreparedStatement getTotalSpent;
    private static ResultSet rs;

    public static boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:data_school.db");
            stmt = connection.createStatement();
            stmt.execute(sqlTeacherTable);
            stmt.execute(sqlStudentTable);
            prepareStatements();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void prepareStatements() {
        try {
            addTeacher = connection.prepareStatement("INSERT INTO teachers (name, salary) VALUES (?, ?)");
            insertStudent = connection.prepareStatement("INSERT INTO students (name, grade, feesTotal, feesPaid) VALUES (?, ?, ?, ?)");
            updateStudentFeesById = connection.prepareStatement("UPDATE students SET feesPaid = ? WHERE id = ?");
            selectStudentNameById = connection.prepareStatement("SELECT name FROM students WHERE id = ?");
            selectStudentfeesPaidById = connection.prepareStatement("SELECT feesPaid FROM students WHERE id = ?");
            selectAllTeachers = connection.prepareStatement("SELECT name FROM teachers");
            selectAllStudents = connection.prepareStatement("SELECT name FROM students");
            getTotalEarned = connection.prepareStatement("SELECT feesPaid FROM students");
            getTotalSpent = connection.prepareStatement("SELECT salary FROM teachers");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean addTeacher(String name, int salary) {
        try {
            addTeacher.setString(1, name);
            addTeacher.setInt(2, salary);
            addTeacher.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean insertStudent(String name, int grade, int feesTotal, int feesPaid) {
        try {
            insertStudent.setString(1, name);
            insertStudent.setInt(2, grade);
            insertStudent.setInt(3, feesTotal);
            insertStudent.setInt(4, feesPaid);
            insertStudent.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static void payStudentFeesById(int feesPaid, int id) {
        int alreadyPaid = selectStudentFeesById(id);
        int totalFeesPaid = alreadyPaid + feesPaid;
        try {
            updateStudentFeesById.setInt(1, totalFeesPaid);
            updateStudentFeesById.setInt(2, id);
            updateStudentFeesById.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String selectStudentNameById(int id) {
        String name = null;
        try {
            selectStudentNameById.setInt(1, id);
            rs = selectStudentNameById.executeQuery();

            if (rs.next()) {
                name = rs.getString(1);
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return name;
    }

    public static int selectStudentFeesById(int id) {
        int feesPaid = 0;
        try {
            selectStudentfeesPaidById.setInt(1, id);
            rs = selectStudentfeesPaidById.executeQuery();

            if (rs.next()) {
                feesPaid = rs.getInt(1);
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return feesPaid;
    }

    public static List<String> selectAllStudents() {
        List<String> names = new ArrayList<>();
        try {
            rs = selectAllStudents.executeQuery();

            while (rs.next()) {
                names.add(rs.getString(1));
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return names;
    }

    public static List<String> selectAllTeachers() {
        List<String> names = new ArrayList<>();
        try {
            rs = selectAllTeachers.executeQuery();

            while (rs.next()) {
                names.add(rs.getString(1));
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return names;
    }

    public static int getTotalEarned() {
        int totalEarned = 0;
        try {
            rs = getTotalEarned.executeQuery();

            while (rs.next()) {
                totalEarned += rs.getInt(1);
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return totalEarned;
    }

    public static int getTotalSpent() {
        int totalEarned = 0;
        try {
            rs = getTotalSpent.executeQuery();

            while (rs.next()) {
                totalEarned += rs.getInt(1);
            }

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return totalEarned;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private static String sqlTeacherTable = "CREATE TABLE IF NOT EXISTS teachers (\n"
            + "	id integer PRIMARY KEY UNIQUE,\n"
            + "	name text NOT NULL,\n"
            + " salary integer NOT NULL \n"
            + ");";

    private static String sqlStudentTable = "CREATE TABLE IF NOT EXISTS students (\n"
            + "	id integer PRIMARY KEY UNIQUE,\n"
            + "	name text NOT NULL,\n"
            + " grade integer NOT NULL, \n"
            + " feesTotal integer NOT NULL, \n"
            + " feesPaid integer NOT NULL \n"
            + ");";
}
