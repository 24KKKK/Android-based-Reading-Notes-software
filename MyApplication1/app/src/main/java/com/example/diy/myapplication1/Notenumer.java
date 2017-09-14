package com.example.diy.myapplication1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Notenumer extends AppCompatActivity {
     private Button find;
    private OpenHelper sqlHelper;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notenumer);
        sqlHelper = new OpenHelper(this, "sqlite_dbname", null, 2);
        find=(Button)findViewById(R.id.notenumber_find);
        listView=(ListView)findViewById(R.id.listview4);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase db = sqlHelper.getWritableDatabase();
                    //游标查询每条数据

                   Cursor cursor=db.rawQuery("select code,count(*) from Note ",null);
                    //定义list存储数据
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    //适配器SimpleAdapter数据绑定
                    //错误:构造函数SimpleAdapter未定义 需把this修改为MainActivity.this
                    SimpleAdapter adapter = new SimpleAdapter(Notenumer.this, list, R.layout.stu_item4,
                            new String[]{"code,count(*)"},
                            new int[]{R.id.a1,R.id.b1});
                    //读取数据 游标移动到下一行
                    while(cursor.moveToNext()) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put( "code", cursor.getString(cursor.getColumnIndex("code")) );
//                        map.put( "username", cursor.getString(cursor.getColumnIndex("username")) );
                        map.put( "count(*)", cursor.getString(cursor.getColumnIndex("count(*)")) );
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
