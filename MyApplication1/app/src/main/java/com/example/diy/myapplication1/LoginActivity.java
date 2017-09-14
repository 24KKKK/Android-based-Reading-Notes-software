package com.example.diy.myapplication1;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private Button dl;
    private Button zc;
    private EditText username;
    private EditText userpass;
    private TextView state1;
    private List<User> userList;
    private List<User> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dl= (Button) findViewById(R.id.dl);
        zc=(Button)findViewById(R.id.zc1);
        username= (EditText) findViewById(R.id.name1);
        userpass= (EditText) findViewById(R.id.age1);
        state1= (TextView) findViewById(R.id.state1);
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString().trim();
                String pass=userpass.getText().toString().trim();

                int result=SqliteDB.getInstance(getApplicationContext()).Quer(pass,name);
                if (result==1)
                {
                    //state1.setText("登录成功！");
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
                else if (result==0){
                    state1.setText("用户名不存在！");

                }
                else if(result==-1)
                {
                    state1.setText("密码错误！");
                }
            }
        });
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    //右上角关于
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_about:
                new AlertDialog.Builder(this)
                        .setTitle("关于")
                        .setMessage("软件版本：v1.0,开发者：底云飞")
                        .setPositiveButton("确定", null)
                        .show();
                break;

            default:
        }
        return true;

    }
}
