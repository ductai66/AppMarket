package com.tai06.dothetai.appmarketphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity1 extends AppCompatActivity {
    static final int MSG_Phone = 1;
    String url_local ="https://tai06dothe.000webhostapp.com/query_php/select_image.php";

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;
    RecyclerView rc_smartphone;
    Handler handler;
    List<SmartPhone> arrSmartphone;
    SmartphoneAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Grid_view();
        Anhxa();
        selectItemDrawer();
        initHandler();
        Getdata(url_local);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void Anhxa(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        rc_smartphone = findViewById(R.id.recyclerview);
        mToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setAdapter(List<SmartPhone> list) {
        adapter = new SmartphoneAdapter(this,list);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        Context context;
        GridLayoutManager layoutManager = new GridLayoutManager(this,3,LinearLayoutManager.VERTICAL,false );
        rc_smartphone.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
        rc_smartphone.addItemDecoration(dividerItemDecoration);
        rc_smartphone.setAdapter(adapter);
    }

    private void initHandler(){
        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case MSG_Phone:{
                        arrSmartphone = new ArrayList<>();
                        arrSmartphone.addAll((Collection<? extends SmartPhone>) msg.obj);
                        setAdapter(arrSmartphone);
                        break;
                    }
                }
            }
        };
    }

    private void Getdata(final String url){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity1.this);
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                List<SmartPhone> list = new ArrayList<>();
                                for (int i=0;i<response.length();i++){
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        list.add(new SmartPhone(
                                                jsonObject.getInt("IDLH"),
                                                jsonObject.getInt("IDP"),
                                                jsonObject.getString("Name"),
                                                jsonObject.getInt("Price"),
                                                jsonObject.getString("Images")
                                        ));
                                        Message msg = new Message();
                                        msg.what = MSG_Phone;
                                        msg.obj = list;
                                        handler.sendMessage(msg);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                });
                requestQueue.add(jsonArrayRequest);
            }
        });
        thread.start();
    }


    private void selectItemDrawer(){
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Message:
                        Toast.makeText(MainActivity1.this,"Messenger",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.Chat:
                        Toast.makeText(MainActivity1.this,"Chat",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.Share:
                        Toast.makeText(MainActivity1.this,"Share",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.Send:
                        Toast.makeText(MainActivity1.this,"Send",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.slide:
                        Toast.makeText(MainActivity1.this,"Slide Home",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.thoat:
                        Toast.makeText(MainActivity1.this,"Thoat",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity1.this,MainActivity.class));
                        return true;
                }
                return true;
            }
        });
    }

    public void showPhone(SmartPhone smartPhone){
        Toast.makeText(this, smartPhone.getName(), Toast.LENGTH_SHORT).show();
    }
}
