package com.tai06.dothetai.appmarketphone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer;
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open();
    }
    private void open(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(timerTask,5000);
    }
    private void Login(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_login);
        dialog.setCanceledOnTouchOutside(false);
        final TextInputEditText user = (TextInputEditText) dialog.findViewById(R.id.edit_user);
        final TextInputEditText pass = (TextInputEditText) dialog.findViewById(R.id.edit_pass);
        Button dangnhap =(Button) dialog.findViewById(R.id.btn_dangnhap);
        Button huy = (Button) dialog.findViewById(R.id.btn_huy);
        dialog.show();
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString().trim();
                String password = pass.getText().toString().trim();
                if (username.equals("dothetai8") && password.equals("2066")){
                    Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,MainActivity1.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}
