package com.suleiman.schoolmanagementsystem;

import com.suleiman.schoolmanagementsystem.database.DataBase;
import com.suleiman.schoolmanagementsystem.database.DataBaseImpl;
import com.suleiman.schoolmanagementsystem.database.SQLHandler;

public class Main {
    public static void main(String[] args) {
        /**
         * Connecting to the database.
         */
        if (SQLHandler.connect()) {
            System.out.println("Connected to database");
        } else {
            throw new RuntimeException("Couldn't connect to database");
        }


        /**
         * Creating the database object.
         */
        DataBase dataBase = new DataBaseImpl();


        /**
         * Creating and adding teachers and students.
         */
//        dataBase.addTeacher("Miwa", 1200);
//        dataBase.addTeacher("Sofa", 750);
//
//        dataBase.insertStudent("Suleiman", 5, 10000, 2500);
//        dataBase.insertStudent("Abbud", 9, 10000, 1300);


        /**
         * Using some methods to ensure that everything is
         * working properly.
         */
        System.out.println(dataBase.selectStudentNameById(2));
//        System.out.println(dataBase.selectStudentFeesById(2));
//        dataBase.payStudentFeesById(1000, 2);
        System.out.println(dataBase.selectStudentFeesById(2));

        System.out.println(dataBase.selectStudentNameById(1));
//        System.out.println(dataBase.selectStudentFeesById(1));
//        dataBase.payStudentFeesById(1400, 1);
        System.out.println(dataBase.selectStudentFeesById(1));

        System.out.println();
        System.out.println(dataBase.getTotalEarned());


        /**
         * Disconnect the database.
         */
        dataBase.disconnect();
    }
}
