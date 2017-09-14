package com.example.diy.myapplication1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    //建表语句
    public static final String CREATE_USER = "create table User ("
            + "code text primary key, "
            + "username text, "
            + "userpass text)";
    //分类表
    public static final String CREATE_BOOKSORT=
            "create table Booksort("
    +"bookid text primary key,"
            +"sortname text)";
    //书籍表
    public static final String CREATE_BOOK=
            "create table Book("
         +"bookcode text primary key,"
            +"bookname text,"
            +"bookuser text,"
            +"bookchuban text,"
            +"ISBN text,"
            +"sort text)";
//    //笔记表
    public static final String CREATE_NOTE=
            "create table Note("
         +"code text ,"
            +"bookcode text,"
            +"data text,"
                    +"content text,"
                    +"primary key(code,bookcode,data),"
    +" FOREIGN KEY(code) REFERENCES User(code),"
    + "FOREIGN KEY(bookcode) REFERENCES Book(bookcode))";
    public OpenHelper(Context context, String name, CursorFactory factory,
                      int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(CREATE_USER);//创建用户表
       db.execSQL(CREATE_BOOK);//创建书籍表
        db.execSQL(CREATE_BOOKSORT);//创建书籍分类表
      db.execSQL(CREATE_NOTE);//创建笔记表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
