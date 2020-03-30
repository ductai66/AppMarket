package com.tai06.dothetai.appmarketphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    TextInputEditText username,password;
    Button dangnhap,huy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        process();
    }
    private void process(){
        username = (TextInputEditText) findViewById(R.id.edit_user);
        password = (TextInputEditText) findViewById(R.id.edit_pass);
        dangnhap = (Button) findViewById(R.id.btn_dangnhap);
        huy = (Button) findViewById(R.id.btn_huy);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                if (user.equals("dothetai8") && pass.equals("2066")){
                    Toast.makeText(Login.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,MainActivity1.class));
                }
                else{
                    Toast.makeText(Login.this,"Đăng nhập thất bại!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
//                Intent startMain = new Intent(Intent.ACTION_MAIN);
//                startMain.addCategory(Intent.CATEGORY_HOME);
//                startActivity(startMain);
//                finish();
                finish();
            }
        });
    }
}
