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

public class Booksort extends AppCompatActivity {
      private Button  add;
      private Button  delete;
      private Button  update;
      private Button  find;
    private OpenHelper sqlHelper;
     private ListView  listView;

    List<BookofSort> bookofSortList;

    private EditText et_username,et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booksort);
        sqlHelper = new OpenHelper(this, "sqlite_dbname", null, 2);
        add=(Button)findViewById(R.id.btn_find);
        //添加
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Booksort.this);
                builder.setTitle("录入");
                //通过布局填充器获login_layout
                View view = getLayoutInflater().inflate(R.layout.booksort,null);
                //获取两个文本编辑框（密码这里不做登陆实现，仅演示）
                final EditText et_username = (EditText) view.findViewById(R.id.et_username);
                final EditText et_password = (EditText) view.findViewById(R.id.et_password);
                builder.setView(view);//设置login_layout为对话提示框
                builder.setCancelable(false);//设置为不可取消
                //设置正面按钮，并做事件处理
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BookofSort bookofSort=new BookofSort();
                        String name = et_username.getText().toString().trim();
                        String pass = et_password.getText().toString().trim();
                    bookofSort.setBookid(name);
                        bookofSort.setSortname(pass);
                        int result=SqliteDB.getInstance(getApplicationContext()).saveBooksort(bookofSort);
                        if (result==1){
                            Toast.makeText(Booksort.this, "录入成功", Toast.LENGTH_SHORT).show();
                        }else  if (result==-1)
                        {
                            Toast.makeText(Booksort.this, "编号已存在", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Booksort.this, "！", Toast.LENGTH_SHORT).show();
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
        //查询
        listView=(ListView)findViewById(R.id.listview1) ;
        find=(Button)findViewById(R.id.btn_insert);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase db = sqlHelper.getWritableDatabase();
                    //游标查询每条数据
                    Cursor cursor = db.query("Booksort", null, null, null, null, null, null);
                    //定义list存储数据
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    //适配器SimpleAdapter数据绑定
                    //错误:构造函数SimpleAdapter未定义 需把this修改为MainActivity.this
                    SimpleAdapter adapter = new SimpleAdapter(Booksort.this, list, R.layout.stu_item,
                            new String[]{"bookid", "sortname"},
                            new int[]{R.id.book, R.id.book1});
                    //读取数据 游标移动到下一行
                    while(cursor.moveToNext()) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put( "bookid", cursor.getString(cursor.getColumnIndex("bookid")) );
                        map.put( "sortname", cursor.getString(cursor.getColumnIndex("sortname")) );

                        list.add(map);
                    }
                    listView.setAdapter(adapter);
                }
                catch (Exception e){
                    Log.i("exception", e.toString());
                }

            }
        });

         //删除
        delete=(Button)findViewById(R.id.btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Booksort.this);
                builder1.setTitle("删除");
                //通过布局填充器获login_layout
                View view = getLayoutInflater().inflate(R.layout.boosort1,null);
                //获取两个文本编辑框（密码这里不做登陆实现，仅演示）
                final EditText et_username = (EditText) view.findViewById(R.id.et_username);
                builder1.setView(view);//设置login_layout为对话提示框
                builder1.setCancelable(false);//设置为不可取消
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BookofSort bookofSort=new BookofSort();
                        String name = et_username.getText().toString().trim();

                        bookofSort.setBookid(name);

                        int result=SqliteDB.getInstance(getApplicationContext()).updateBooksort(bookofSort);
                        if (result==1){
                            Toast.makeText(Booksort.this, "删除成功", Toast.LENGTH_SHORT).show();
                        }else if(result==-1)
                        {
                            Toast.makeText(Booksort.this, "编号不存在", Toast.LENGTH_SHORT).show();
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
        //修改
        update=(Button)findViewById(R.id.btn_change);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Booksort.this);
                builder.setTitle("修改");
                //通过布局填充器获login_layout
                View view = getLayoutInflater().inflate(R.layout.booksort,null);
                //获取两个文本编辑框（密码这里不做登陆实现，仅演示）
                final EditText et_username = (EditText) view.findViewById(R.id.et_username);
                final EditText et_password = (EditText) view.findViewById(R.id.et_password);
                builder.setView(view);//设置login_layout为对话提示框
                builder.setCancelable(false);//设置为不可取消
                //设置正面按钮，并做事件处理
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        BookofSort bookofSort=new BookofSort();
                        String name = et_username.getText().toString().trim();
                        String pass = et_password.getText().toString().trim();
                        bookofSort.setBookid(name);
                        bookofSort.setSortname(pass);
                        int result=SqliteDB.getInstance(getApplicationContext()).updateBooksort(bookofSort);
                        if (result==1){
                            Toast.makeText(Booksort.this, "修改成功", Toast.LENGTH_SHORT).show();
                        }else  if (result==-1)
                        {
                            Toast.makeText(Booksort.this, "编号不存在", Toast.LENGTH_SHORT).show();
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
    }

}
