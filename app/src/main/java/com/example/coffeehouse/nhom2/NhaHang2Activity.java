package com.example.coffeehouse.nhom2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coffeehouse.nhom2.adapter.GiaReAdapter;
import com.example.coffeehouse.nhom2.model.MyItemOnClick;
import com.example.coffeehouse.nhom2.model.NhaHang;
import com.example.coffeehouse.nhom2.unti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NhaHang2Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
    String solon,sonho;
    private Toolbar toolbar;
    ArrayList<NhaHang> arrayList;
    LinearLayoutManager manager;
    GiaReAdapter giaReAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nha_hang2);
        AnhXa();
        ThongTin();
        ActionToolbar();
        GetData();
        EventOnclick();
    }

    private void ThongTin() {
        Intent intent = getIntent();
        solon = intent.getStringExtra("SOLON");
        sonho = intent.getStringExtra("SONHO");
    }
    private void EventOnclick() {
        giaReAdapter.setMyItemOnClick(new MyItemOnClick() {
            @Override
            public void onClick(NhaHang nhaHang) {
                Intent intent = new Intent(NhaHang2Activity.this,ChitietActivity.class);
                intent.putExtra("ID",""+nhaHang.getId());
                intent.putExtra("TENNH",nhaHang.getTennhahang());
                intent.putExtra("DIACHINH",nhaHang.getDiachi());
                intent.putExtra("MONANNH",nhaHang.getMonan());
                intent.putExtra("GIANH",""+nhaHang.getGiatien());
                intent.putExtra("DANHGIANH",""+nhaHang.getDanhgia());
                intent.putExtra("IMGNH",nhaHang.getImgnhahang());
                intent.putExtra("MOTANH",nhaHang.getMota());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(NhaHang2Activity.this,YeuthichActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanbinhdanchitiet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int iD;
                String tien;
                int danhGia;
                String tennhahang;
                String imgnhahang;
                String mota;
                String diachi;
                String monan;
                if (response != null ) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            iD = jsonObject.getInt("id");
                            tennhahang = jsonObject.getString("tennhahang");
                            diachi = jsonObject.getString("diachi");
                            monan = jsonObject.getString("monan");
                            tien = jsonObject.getString("giatien");
                            danhGia = jsonObject.getInt("danhgia");
                            imgnhahang = jsonObject.getString("imgnhahang");
                            mota = jsonObject.getString("mota");
                            arrayList.add(new NhaHang(iD,tennhahang,diachi,monan,tien,danhGia,imgnhahang,mota));
                            giaReAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("gialon",solon);
                hashMap.put("gianho",sonho);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        recyclerView = findViewById(R.id.recyNhahang2);
        toolbar = findViewById(R.id.toolbarNhahang2);
        arrayList = new ArrayList<>();
        manager = new LinearLayoutManager(NhaHang2Activity.this,LinearLayoutManager.VERTICAL,false);
        giaReAdapter = new GiaReAdapter(arrayList, NhaHang2Activity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(giaReAdapter);
    }
    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
