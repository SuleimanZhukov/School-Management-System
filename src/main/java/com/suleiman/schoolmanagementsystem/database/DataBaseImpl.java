package com.suleiman.schoolmanagementsystem.database;

import com.suleiman.schoolmanagementsystem.database.DataBase;

import java.util.List;

public class DataBaseImpl implements DataBase {

    private static final int FEES_TOTAL = 10000;

    @Override
    public boolean addTeacher(String name, int salary) {
        return SQLHandler.addTeacher(name, salary);
    }

    @Override
    public boolean insertStudent(String name, int grade, int feesTotal, int feesPaid) {
        return SQLHandler.insertStudent(name, grade, FEES_TOTAL, feesPaid);
    }

    @Override
    public void payStudentFeesById(int feesPaid, int id) {
        SQLHandler.payStudentFeesById(feesPaid, id);
    }

    @Override
    public String selectStudentNameById(int id) {
        return SQLHandler.selectStudentNameById(id);
    }

    @Override
    public int selectStudentFeesById(int id) {
        return SQLHandler.selectStudentFeesById(id);
    }

    @Override
    public List<String> selectAllStudents() {
        return SQLHandler.selectAllStudents();
    }

    @Override
    public List<String> selectAllTeachers() {
        return SQLHandler.selectAllTeachers();
    }

    @Override
    public int getTotalEarned() {
        return SQLHandler.getTotalEarned();
    }

    @Override
    public int getTotalSpent() {
        return SQLHandler.getTotalSpent();
    }

    @Override
    public void disconnect() {
        SQLHandler.disconnect();
    }
}
