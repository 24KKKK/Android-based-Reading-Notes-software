package com.example.diy.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/11/10.
 */
public class SqliteDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "sqlite_dbname";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static SqliteDB sqliteDB;

    private SQLiteDatabase db;

    private SqliteDB(Context context) {
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取SqliteDB实例
     *
     * @param context
     */
    public synchronized static SqliteDB getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new SqliteDB(context);
        }
        return sqliteDB;
    }

    /**
     * 将User实例存储到数据库。
     */
    public int saveUser(User user) {
        if (user != null) {
           /* ContentValues values = new ContentValues();
            values.put("username", user.getUsername());
            values.put("userpwd", user.getUserpwd());
            db.insert("User", null, values);*/

            Cursor cursor = db.rawQuery("select * from User where username=?", new String[]{user.getUsername().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into User(code,username,userpass) values(?,?,?)", new String[]{user.getId().toString(),user.getUsername().toString(), user.getUserpass().toString()});
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }
        } else {
            return 0;
        }
    }
    //储存
public int saveBoomanage(BookofManage bookofManage)
{
    if(bookofManage!=null)
    {
        Cursor cursor=db.rawQuery("select * from Book where bookcode=?",new String[]{bookofManage.getBookcode().toString()});
        if(cursor.getCount()>0)
        {
            return -1;
        }
        else
        {
            try
            {
                db.execSQL("insert into Book(bookcode,bookname,bookuser,bookchuban,ISBN,sort)values(?,?,?,?,?,?)",new String[]{bookofManage.getBookcode().toString(),
                        bookofManage.getName().toString(),bookofManage.getZname().toString(),bookofManage.getPublish().toString(),
                        bookofManage.getISBN().toString(),bookofManage.getSort().toString()});
            }catch(Exception e)
            {
                Log.d("错误", e.getMessage().toString());
            }

            return 1;
        }


    }

    else
    {
        return 0;
    }

}
    //储存
    public int saveBooksort(BookofSort booksort) {
        if (booksort != null) {
            Cursor cursor = db.rawQuery("select * from  Booksort where bookid=?", new String[]{booksort.getBookid().toString()});
            if (cursor.getCount() > 0) {
                return -1;
            } else {
                try {
                    db.execSQL("insert into Booksort(bookid,sortname) values(?,?) ", new String[]{booksort.getBookid().toString(), booksort.getSortname().toString()});
                } catch (Exception e) {
                    Log.d("错误", e.getMessage().toString());
                }
                return 1;
            }

        } else {
            return 0;
        }

    }
    //储存
  public int savenote(note n)
  {
      if(n!=null)
      {
          Cursor cursor = db.rawQuery("select * from  User where code=?", new String[]{n.getCode().toString()});
          Cursor cursor1=db.rawQuery("select * from  Book where bookcode=?", new String[]{n.getBookcode()});

          if (cursor.getCount()<1||(cursor1.getCount())<1) {
              return -1;
          } else {
              try {
                  db.execSQL("insert into Note(code,bookcode,data,content) values(?,?,?,?) ", new String[]{n.getCode().toString(),
                          n.getBookcode().toString(),n.getData().toString(),n.getContent().toString()});
              } catch (Exception e) {
                  Log.d("错误", e.getMessage().toString());
              }
              return 1;
          }

      } else {
          return 0;
      }



      }




   //删除
    public int updateBooksort(BookofSort bookofSort) {

        Cursor cursor = db.rawQuery("select * from  Booksort where bookid=?", new String[]{bookofSort.getBookid().toString()});
        if (cursor.getCount() < 0 || cursor.getCount() == 0) {
            return -1;
        } else {
            try {
                db.delete("Booksort", "bookid=?", new String[]{bookofSort.getBookid().toString()});

            } catch (Exception e) {
                Log.d("错误", e.getMessage().toString());
            }
            return 1;
        }


    }
public int deleteBook(BookofManage bookofManage)
{
    Cursor cursor = db.rawQuery("select * from  Book where bookcode=?", new String[]{bookofManage.getBookcode().toString()});
    if (cursor.getCount() < 0 || cursor.getCount() == 0) {
        return -1;
    } else {
        try {
            db.delete("Book", "bookcode=?", new String[]{bookofManage.getBookcode().toString()});

        } catch (Exception e) {
            Log.d("错误", e.getMessage().toString());
        }
        return 1;
    }


}
   public int deletenote(note n)
   {
       Cursor cursor=db.rawQuery("select * from Note where code=?",new String[]{n.getCode().toString()});
       Cursor cursor1=db.rawQuery("select * from Note where bookcode=?", new String[]{n.getBookcode().toString()});
       Cursor cursor2=db.rawQuery("select * from Note where data=?",new String[]{n.getData().toString()});
       if (cursor.getCount() < 0 || cursor1.getCount()<1||cursor2.getCount()<1) {
           return -1;
       } else {
           try {
               db.delete("Note", "code=? and bookcode=? and data=?", new String[]{n.getCode().toString(),n.getBookcode().toString(),n.getData().toString()});

           } catch (Exception e) {
               Log.d("错误", e.getMessage().toString());
           }
           return 1;
       }



   }
    //修改
    public int changeBooksort(BookofSort bookofSort) {
        ContentValues values = new ContentValues();
        Cursor cursor1 = db.rawQuery("select * from  Booksort where bookid=?", new String[]{bookofSort.getBookid().toString()});
        if (cursor1.getCount() < 0 || cursor1.getCount() == 0) {
            return -1;
        }
else{
        try {
            db.update("Booksort", values, "sortname= ?", new String[]{bookofSort.getSortname().toString()});

        } catch (Exception e) {
            Log.d("错误", e.getMessage().toString());
        }
        return 1;
    }

}
    public int updatenote(note n) {
        ContentValues values = new ContentValues();
        Cursor cursor = db.rawQuery("select * from Note where code=?", new String[]{n.getCode().toString()});
        Cursor cursor1 = db.rawQuery("select * from Note where bookcode=?", new String[]{n.getBookcode().toString()});
        Cursor cursor2 = db.rawQuery("select * from Note where data=?", new String[]{n.getData().toString()});
        if (cursor1.getCount() < 1 || cursor.getCount() < 1 || cursor2.getCount() < 1) {
            return -1;
        } else {
            try {
                db.update("Note", values, "content= ?", new String[]{n.getContent().toString()});

            } catch (Exception e) {
                Log.d("错误", e.getMessage().toString());
            }
            return 1;
        }
    }
    //更新
public int updateBook(BookofManage bookofManage)
{
    ContentValues values = new ContentValues();
    Cursor cursor1 = db.rawQuery("select * from  Book where bookcode=?", new String[]{bookofManage.getBookcode().toString()});
    if (cursor1.getCount() < 0 || cursor1.getCount() == 0) {
        return -1;
    }
    else{
        try {
            db.update("Book", values, "bookname= ?", new String[]{bookofManage.getName().toString()});
            db.update("Book", values, "bookuser= ?", new String[]{bookofManage.getZname().toString()});
            db.update("Book", values, "bookchuban= ?", new String[]{bookofManage.getPublish().toString()});
            db.update("Book", values, "ISBN= ?", new String[]{bookofManage.getISBN().toString()});
            db.update("Book", values, "sort= ?", new String[]{bookofManage.getSort().toString()});

        } catch (Exception e) {
            Log.d("错误", e.getMessage().toString());
        }
        return 1;
    }





}




//    public List<BookofSort>loadSort()
//    {
//        List<BookofSort> list = new ArrayList<BookofSort>();
//        Cursor cursor = db
//                .query("Booksort", null, null, null, null, null, null);
//        if (cursor.moveToFirst()) {
//            do {
//                BookofSort sort=new BookofSort();
//
//        sort.setBookid(cursor.getString(cursor
//                        .getColumnIndex("bookid")));
//                sort.setSortname(cursor.getString(cursor.getColumnIndex("sortname")));
//                list.add(sort);
//            } while (cursor.moveToNext());
//        }
//        return list;
//
//
//    }
    /**
     * 从数据库读取User信息。
     */
    public List<User> loadUser() {
        List<User> list = new ArrayList<User>();
        Cursor cursor = db
                .query("User", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getString(cursor.getColumnIndex("code")));
                user.setUsername(cursor.getString(cursor
                        .getColumnIndex("username")));
                user.setUserpass(cursor.getString(cursor

                        .getColumnIndex("userpass")));
                list.add(user);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public int Quer(String pwd,String name)
    {


        HashMap<String,String> hashmap=new HashMap<String,String>();
        Cursor cursor =db.rawQuery("select * from User where username=?", new String[]{name});

       // hashmap.put("name",db.rawQuery("select * from User where name=?",new String[]{name}).toString());
        if (cursor.getCount()>0)
        {
            Cursor pwdcursor =db.rawQuery("select * from User where userpass=? and username=?",new String[]{pwd,name});
            if (pwdcursor.getCount()>0)
            {
                return 1;
            }
            else {
                return -1;
            }
        }
        else {
            return 0;
        }


    }
}
