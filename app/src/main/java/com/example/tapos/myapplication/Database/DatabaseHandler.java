package com.example.tapos.myapplication.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.tapos.myapplication.R;
import com.example.tapos.myapplication.models.Magic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tapos on 1/22/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DB_FILE_NAME = "magics.db";

    private String TABLE_CREATE = "CREATE TABLE if not EXISTS magics ( " +
            " id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " title TEXT, " +
            " description TEXT," +
            " thumbnail INTEGER, "+
            " UNIQUE (id) ON CONFLICT REPLACE )";
    private Context mContext;


    public DatabaseHandler(Context context) {
        super(context, DB_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    //Create database
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);


    }




    //Update database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == oldVersion + 1) {
            //	db.execSQL("ALTER TABLE student_info ADD COLUMN country VARCHAR(30)");
            db.execSQL("DROP TABLE if EXISTS  magics");
            db.execSQL(TABLE_CREATE);
        }
    }

//    //Insert data into table
//    public void insertData(Magic magic) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        SQLiteStatement stmt = db.compileStatement("INSERT INTO magics (title,description,thumbnail) " +
//                "VALUES (?,?,?)");
//        stmt.bindString(2, magic.getTitle());
//        stmt.bindString(3, magic.getDescription());
//        stmt.bindLong(4, magic.getThumbnail());
//        stmt.execute();
//        stmt.close();
//        db.close();
//    }
//
//    //Update data into table
//    public void updateData(Magic magic) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        SQLiteStatement stmt = db.compileStatement("UPDATE magics SET title=?, description=?, thumbnail=? " +
//                "WHERE id = ?");
//        stmt.bindString(2, magic.getTitle());
//        stmt.bindString(2, student.getClassName());
//        stmt.bindLong(3, student.getAge());
//        stmt.bindLong(3, magic.getId());
//        stmt.execute();
//        stmt.close();
//        db.close();
//    }
//
//    //Select all data from the table
//    public List getStudents() {
//        List students = new ArrayList();
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT id, name, age, class_name, city from student_info ORDER BY id ASC";
//        Cursor cursor = db.rawQuery(query, null);
//        while (cursor.moveToNext()) {
//            Student std = new Student();
//            std.setId(cursor.getInt(0));
//            std.setName(cursor.getString(1));
//            std.setAge(cursor.getInt(2));
//            std.setClassName(cursor.getString(3));
//            std.setCity(cursor.getString(4));
//            students.add(std);
//        }
//        db.close();
//        return students;
//    }
//
//    //Delete data from the table for the given id
//    public void deleteData(int stdId) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        SQLiteStatement stmt = db.compileStatement("DELETE FROM student_info WHERE id = ?");
//        stmt.bindLong(1, stdId);
//        stmt.execute();
//        stmt.close();
//        db.close();
//    }
//
//    //Select data for the given id
//    public Student getStudentById(int stdId) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT id, name, age, class_name, city FROM student_info WHERE id = ?";
//        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(stdId)});
//        cursor.moveToFirst();
//        Student std = new Student();
//        std.setId(cursor.getInt(0));
//        std.setName(cursor.getString(1));
//        std.setAge(cursor.getInt(2));
//        std.setClassName(cursor.getString(3));
//        std.setCity(cursor.getString(4));
//        db.close();
//        return std;
//    }
}
