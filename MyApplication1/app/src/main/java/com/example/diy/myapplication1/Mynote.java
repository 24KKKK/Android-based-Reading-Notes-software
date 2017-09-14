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

public class Mynote extends AppCompatActivity {
            private Button add;
            private Button delete;
            private Button update;
            private Button find;
    private OpenHelper sqlHelper;
    private ListView listView;
    List<note> noteList;
    private EditText code,bookcode,data,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynote);
        sqlHelper = new OpenHelper(this, "sqlite_dbname", null, 2);

        add=(Button)findViewById(R.id.note_find);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Mynote.this);
                builder.setTitle("录入");
                //通过布局填充器获login_layout
                View view = getLayoutInflater().inflate(R.layout.note,null);
                //获取两个文本编辑框（密码这里不做登陆实现，仅演示）
                final EditText usercode = (EditText) view.findViewById(R.id.note_code1);
                final EditText bookcode= (EditText) view.findViewById(R.id.note_bookcode1);
                final EditText data = (EditText) view.findViewById(R.id.note_data1);
                final EditText content= (EditText) view.findViewById(R.id.note_content1);
                builder.setView(view);//设置login_layout为对话提示框
                builder.setCancelable(false);//设置为不可取消
                //设置正面按钮，并做事件处理
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      note n=new note();
                        String ucode = usercode.getText().toString().trim();
                        String bcode = bookcode.getText().toString().trim();
                        String da = data.getText().toString().trim();
                        String cont = content.getText().toString().trim();
                        n.setCode(ucode);
                        n.setBookcode(bcode);
                        n.setData(da);
                        n.setContent(cont);

                        int result=SqliteDB.getInstance(getApplicationContext()).savenote(n);
                        if (result==1){
                            Toast.makeText(Mynote.this, "录入成功", Toast.LENGTH_SHORT).show();
                        }else  if (result==-1)
                        {
                            Toast.makeText(Mynote.this, "您的编号没有完全写正确", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Mynote.this, "！", Toast.LENGTH_SHORT).show();
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
        delete=(Button)findViewById(R.id.note_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Mynote.this);
                builder1.setTitle("删除");
                //通过布局填充器获login_layout
                View view = getLayoutInflater().inflate(R.layout.note1,null);
                //获取两个文本编辑框（密码这里不做登陆实现，仅演示）
                final EditText usercode = (EditText) view.findViewById(R.id.note_code1);
                final EditText bookcode= (EditText) view.findViewById(R.id.note_bookcode1);
                final EditText data = (EditText) view.findViewById(R.id.note_data1);
                builder1.setView(view);//设置login_layout为对话提示框
                builder1.setCancelable(false);//设置为不可取消
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       note n=new note();
                        String ucode = usercode.getText().toString().trim();
                        String bcode=bookcode.getText().toString().trim();
                        String da=data.getText().toString().trim();
                      n.setCode(ucode);
                        n.setBookcode(bcode);
                        n.setData(da);


                        int result=SqliteDB.getInstance(getApplicationContext()).deletenote(n);
                        if (result==1){
                            Toast.makeText(Mynote.this, "删除成功", Toast.LENGTH_SHORT).show();
                        }else if(result==-1)
                        {
                            Toast.makeText(Mynote.this, "各种编号不存在", Toast.LENGTH_SHORT).show();
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
        update=(Button)findViewById(R.id.note_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Mynote.this);
                builder.setTitle("修改");
                //通过布局填充器获login_layout
                View view = getLayoutInflater().inflate(R.layout.note,null);
                //获取两个文本编辑框（密码这里不做登陆实现，仅演示）
                final EditText usercode = (EditText) view.findViewById(R.id.note_code1);
                final EditText bookcode= (EditText) view.findViewById(R.id.note_bookcode1);
                final EditText data = (EditText) view.findViewById(R.id.note_data1);
                final EditText content= (EditText) view.findViewById(R.id.note_content1);
                builder.setView(view);//设置login_layout为对话提示框
                builder.setCancelable(false);//设置为不可取消
                //设置正面按钮，并做事件处理
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        note n=new note();
                        String ucode = usercode.getText().toString().trim();
                        String bcode = bookcode.getText().toString().trim();
                        String da = data.getText().toString().trim();
                        String cont = content.getText().toString().trim();
                        n.setCode(ucode);
                        n.setBookcode(bcode);
                        n.setData(da);
                        n.setContent(cont);
                        int result=SqliteDB.getInstance(getApplicationContext()).updatenote(n);
                        if (result==1){
                            Toast.makeText(Mynote.this, "修改成功", Toast.LENGTH_SHORT).show();
                        }else  if (result==-1)
                        {
                            Toast.makeText(Mynote.this, "编号不存在", Toast.LENGTH_SHORT).show();
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
        find=(Button)findViewById(R.id.note_insert);
        listView=(ListView)findViewById(R.id.listview3) ;
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SQLiteDatabase db = sqlHelper.getWritableDatabase();
                    //游标查询每条数据
                    Cursor cursor = db.query("Note", null, null, null, null, null, null);

                 //数据库标的名称
                    //selection 查询条件
                    //selectionArgs:查询结果
                    //groupBy  : 分组列
                    //having  :  分组条件
                    //orderBy  :拍序列
                    //limit:  分页查询限制
                    //定义list存储数据
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    //适配器SimpleAdapter数据绑定
                    //错误:构造函数SimpleAdapter未定义 需把this修改为MainActivity.this
                    SimpleAdapter adapter = new SimpleAdapter(Mynote.this, list, R.layout.stu_item3,
                            new String[]{"code", "bookcode","data","content"},
                            new int[]{R.id.a, R.id.b,R.id.c,R.id.d
                                   });
                    //读取数据 游标移动到下一行
                    while(cursor.moveToNext()) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put( "code", cursor.getString(cursor.getColumnIndex("code")) );
                        map.put( "bookcode", cursor.getString(cursor.getColumnIndex("bookcode")) );
                        map.put( "data", cursor.getString(cursor.getColumnIndex("data")) );
                        map.put( "content", cursor.getString(cursor.getColumnIndex("content")) );

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
