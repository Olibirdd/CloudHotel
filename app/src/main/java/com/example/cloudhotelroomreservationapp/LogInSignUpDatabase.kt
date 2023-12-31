package com.example.cloudhotelroomreservationapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LogInSignUpDatabase (context: Context): SQLiteOpenHelper(context,"Userdata" ,null, 1) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table Userdata (fullname TEXT, username TEXT primary key, password TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists Userdata")
    }

    fun insertdata(fullname: String, username: String, password: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("FullName", fullname)
        cv.put("Username", username)
        cv.put("password", password)
        val result = p0.insert("Userdata", null, cv)
        if (result == -1.toLong()) {
            return false
        }
        return true
    }

    fun checkuserpass(username: String, password: String): Boolean {
        val p0 = this.writableDatabase
        val query = "select * from Userdata where username='$username' and password='$password'"
        val cursor = p0.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true

    }
}