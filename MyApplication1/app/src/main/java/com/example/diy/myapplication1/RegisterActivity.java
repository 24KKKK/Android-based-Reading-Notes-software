package com.example.diy.myapplication1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private Button zc;
    private EditText username;
    private EditText userpass;
    private EditText id;
    private TextView state;
    private List<User> userList;
    private List<User> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        zc=(Button)findViewById(R.id.tj);
        username=(EditText)findViewById(R.id.Username1);
        userpass=(EditText)findViewById(R.id.Userpass1);
         id=(EditText)findViewById(R.id.password1);
        state=(TextView)findViewById(R.id.state);
        zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString().trim();
                String pass=userpass.getText().toString().trim();
                String id1=id.getText().toString().trim();
                User user=new User();
                user.setUsername(name);
                user.setUserpass(pass);
                user.setId(id1);
                int result=SqliteDB.getInstance(getApplicationContext()).saveUser(user);
                if (result==1){
                    //state.setText("注册成功！");
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }else  if (result==-1)
                {
                    state.setText("用户名已经存在！");
                }
                else
                {
                    state.setText("！");
                }


            }
        });

    }
}
