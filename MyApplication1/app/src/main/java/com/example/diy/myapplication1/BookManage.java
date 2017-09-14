package com.example.diy.myapplication1;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookManage extends AppCompatActivity {
    private Button add;
    private Button delete;
    private Button update;
    private Button find;
    private OpenHelper sqlHelper;
    private ListView listView;
    List<BookofManage> bookofManageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_manage);
        sqlHelper = new OpenHelper(this, "sqlite_dbname", null, 2);
      add=(Button)findViewById(R.id.btn_insert);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookManage.this);
                builder.setTitle("录入");
                //通过布局填充器获login_layout
                View view = getLayoutInflater().inflate(R.layout.bookmanage,null);
                //获取两个文本编辑框（密码这里不做登陆实现，仅演示）
                final EditText bookcode = (EditText) view.findViewById(R.id.et_bookcode);
                final EditText name = (EditText) view.findViewById(R.id.et_name);
                final EditText zname = (EditText) view.findViewById(R.id.et_Zname);
                final EditText chuban = (EditText) view.findViewById(R.id.et_publish);
                final EditText ISBN=(EditText) view.findViewById(R.id.et_ISBN);
                final EditText sort=(EditText) view.findViewById(R.id.et_sort);
                builder.setView(view);//设置login_layout为对话提示框
                builder.setCancelable(false);//设置为不可取消
                //设置正面按钮，并做事件处理
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BookofManage b=new BookofManage();
                        String boocode = bookcode.getText().toString().trim();
                        String name1 = name.getText().toString().trim();
                        String zname1=zname.getText().toString().trim();
                        String chuban1=chuban.getText().toString().trim();
                        String is=ISBN.getText().toString().trim();
                        String sort1=sort.getText().toString().trim();
                       b.setBookcode(boocode);
                        b.setName(name1);
                        b.setZname(zname1);
                        b.setPublish(chuban1);
                        b.setISBN(is);
                        b.setSort(sort1);
                        int result=SqliteDB.getInstance(getApplicationContext()).saveBoomanage(b);
                        if (result==1){
                            Toast.makeText(BookManage.this, "录入成功", Toast.LENGTH_SHORT).show();
                        }else  if (result==-1)
                        {
                            Toast.makeText(BookManage.this, "编号已存在", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(BookManage.this, "！", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                //设置反面按钮，并做事件处理
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();//显示Dialog对话框
            }
        });
          delete=(Button)findViewById(R.id.btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(BookManage.this);
                builder1.setTitle("删除");
                //通过布局填充器获login_layout
                View view = getLayoutInflater().inflate(R.layout.manage1,null);
                //获取两个文本编辑框（密码这里不做登陆实现，仅演示）
                final EditText et_username = (EditText) view.findViewById(R.id.et_username);
                builder1.setView(view);//设置login_layout为对话提示框
                builder1.setCancelable(false);//设置为不可取消
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       BookofManage bm=new BookofManage();
                        String name = et_username.getText().toString().trim();

                       bm.setBookcode(name);

                        int result=SqliteDB.getInstance(getApplicationContext()).deleteBook(bm);
                        if (result==1){
                            Toast.makeText(BookManage.this, "删除成功", Toast.LENGTH_SHORT).show();
                        }else if(result==-1)
                        {
                            Toast.makeText(BookManage.this, "编号不存在", Toast.LENGTH_SHORT).show();
                        }



                    }
                });
                //设置反面按钮，并做事件处理
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder1.show();//显示Dialog对话框
            }
        });
        update=(Button)findViewById(R.id.btn_change);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BookManage.this);
                builder.setTitle("录入");
                //通过布局填充器获login_layout
                View view = getLayoutInflater().inflate(R.layout.bookmanage,null);
                //获取两个文本编辑框（密码这里不做登陆实现，仅演示）
                final EditText bookcode = (EditText) view.findViewById(R.id.et_bookcode);
                final EditText name = (EditText) view.findViewById(R.id.et_name);
                final EditText zname = (EditText) view.findViewById(R.id.et_Zname);
                final EditText chuban = (EditText) view.findViewById(R.id.et_publish);
                final EditText ISBN=(EditText) view.findViewById(R.id.et_ISBN);
                final EditText sort=(EditText) view.findViewById(R.id.et_sort);
                builder.setView(view);//设置login_layout为对话提示框
                builder.setCancelable(false);//设置为不可取消
                //设置正面按钮，并做事件处理
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BookofManage b=new BookofManage();
                        String boocode = bookcode.getText().toString().trim();
                        String name1 = name.getText().toString().trim();
                        String zname1=zname.getText().toString().trim();
                        String chuban1=chuban.getText().toString().trim();
                        String is=ISBN.getText().toString().trim();
                        String sort1=sort.getText().toString().trim();
                        b.setBookcode(boocode);
                        b.setName(name1);
                        b.setZname(zname1);
                        b.setPublish(chuban1);
                        b.setISBN(is);
                        b.setSort(sort1);
                        int result=SqliteDB.getInstance(getApplicationContext()).updateBook(b);
                        if (result==1){
                            Toast.makeText(BookManage.this, "修改成功", Toast.LENGTH_SHORT).show();
                        }else  if (result==-1)
                        {
                            Toast.makeText(BookManage.this, "编号不存在", Toast.LENGTH_SHORT).show();
                        }



                    }
                });
                //设置反面按钮，并做事件处理
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();//显示Dialog对话框
            }
        });
        find=(Button)findViewById(R.id.btn_find);
        listView=(ListView)findViewById(R.id.listview2) ;
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                SQLiteDatabase db = sqlHelper.getWritableDatabase();
                //游标查询每条数据
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                //定义list存储数据
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                //适配器SimpleAdapter数据绑定
                //错误:构造函数SimpleAdapter未定义 需把this修改为MainActivity.this
                SimpleAdapter adapter = new SimpleAdapter(BookManage.this, list, R.layout.stu_item1,
                        new String[]{"bookcode", "bookname","bookuser","bookchuban","ISBN","sort"},
                        new int[]{R.id.boo, R.id.boo1,R.id.boo2,R.id.boo3,
                        R.id.boo4,R.id.boo5});
                //读取数据 游标移动到下一行
                while(cursor.moveToNext()) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put( "bookcode", cursor.getString(cursor.getColumnIndex("bookcode")) );
                    map.put( "bookname", cursor.getString(cursor.getColumnIndex("bookname")) );
                    map.put( "bookuser", cursor.getString(cursor.getColumnIndex("bookuser")) );
                    map.put( "bookchuban", cursor.getString(cursor.getColumnIndex("bookchuban")) );
                    map.put( "ISBN", cursor.getString(cursor.getColumnIndex("ISBN")) );
                    map.put( "sort", cursor.getString(cursor.getColumnIndex("sort")) );
                    list.add(map);
                }
                listView.setAdapter(adapter);
            }
            catch (Exception e){
                Log.i("exception", e.toString());
            }
            }
        });
    }
}
