package com.suleiman.schoolmanagementsystem.database;

import java.util.List;

public interface DataBase {

    boolean addTeacher(String name, int salary);

    boolean insertStudent(String name, int grade, int feesTotal, int feesPaid);

    void payStudentFeesById(int feesPaid, int id);

    String selectStudentNameById(int id);

    int selectStudentFeesById(int id);

    List<String> selectAllStudents();

    List<String> selectAllTeachers();

    int getTotalEarned();

    int getTotalSpent();

    void disconnect();
}
