package com.example.diy.myapplication1;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件（主页面）
    private Button left;
    //声明侧滑兰
    private SlidingMenu menu;
    //声明子布局控件
    private RelativeLayout left_update;
    private RelativeLayout left_clear;
    private RelativeLayout left_lixian;
    private RelativeLayout left_about;
    private RelativeLayout left_day;
    private RelativeLayout GY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定id
        left = (Button) findViewById(R.id.btn);
        left.setOnClickListener(this);
        slidingMenu();
    }

    private void slidingMenu() {
        //初始化侧滑栏
        menu=new SlidingMenu(this);
        //设置类型
        menu.setMode(SlidingMenu.LEFT);
        //设置触屏模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置阴影宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
        //滑动主页面剩余宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        //设置渐变效果
        menu.setFadeDegree(0.4f);
        //加载到当前activity
        menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        //加载左侧布局
        View view= LinearLayout.inflate(this,R.layout.activity_leftment_activity,null);
        menu.setMenu(view);
        //绑定id
        left_update = (RelativeLayout) findViewById(R.id.manage);
        left_clear = (RelativeLayout) findViewById(R.id.bookmanage);
        left_lixian = (RelativeLayout) findViewById(R.id.readnote);
        left_about = (RelativeLayout) findViewById(R.id.number);
        left_day = (RelativeLayout) findViewById(R.id.TJ);
        GY=(RelativeLayout)findViewById(R.id.GY);
        //设置监听
        left_update.setOnClickListener(this);
        left_clear.setOnClickListener(this);
        left_lixian.setOnClickListener(this);
        left_about.setOnClickListener(this);
        left_day.setOnClickListener(this);
        GY.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                menu.toggle();//点击按钮切换到侧滑页

                break;
            case R.id.manage:

                startActivity(new Intent(MainActivity.this,Booksort.class));

                break;
            case R.id.bookmanage:

                startActivity(new Intent(MainActivity.this,BookManage.class));
                break;
            case R.id.readnote:

                startActivity(new Intent(MainActivity.this,Mynote.class));
                break;
            case R.id.number:

                startActivity(new Intent(MainActivity.this,Number.class));
                break;
            case R.id.TJ:

                startActivity(new Intent(MainActivity.this,Notenumer.class));
                break;
            case R.id.GY:

            //   startActivity(new Intent(MainActivity.this,GY.class));


        }
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