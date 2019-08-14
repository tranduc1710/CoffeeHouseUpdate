package com.example.coffeehouse.nhom2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coffeehouse.nhom2.adapter.YeuThichAdapter;
import com.example.coffeehouse.nhom2.model.MyItemOnClickYeuThich;
import com.example.coffeehouse.nhom2.model.YeuThich;
import com.example.coffeehouse.nhom2.unti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YeuthichActivity extends AppCompatActivity {
    String IDTK;
    private Toolbar toolbarYeuThich;
    private RecyclerView recyYeuThich;
    ArrayList<YeuThich> arrayList;
    LinearLayoutManager manager;
    YeuThichAdapter giaReAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeuthich);
        AnhXa();
        ActionToolbar();
        GetData();
        EventOnClik();
    }

    private void EventOnClik() {
        giaReAdapter.setMyItemOnClick(new MyItemOnClickYeuThich() {
            @Override
            public void onClick(YeuThich nhaHang) {
                Intent intent = new Intent(YeuthichActivity.this,ChitietActivity.class);
                intent.putExtra("ID",""+nhaHang.getIdstatus());
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

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanyeuthich, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int iD;
                int tien;
                int danhGia;
                String tennhahang;
                String imgnhahang;
                String mota;
                String diachi;
                String monan;
                int idtk;
                int idstatus;
                if (response != null ) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            iD = jsonObject.getInt("id");
                            tennhahang = jsonObject.getString("tennhahang");
                            diachi = jsonObject.getString("diachi");
                            monan = jsonObject.getString("monan");
                            tien = jsonObject.getInt("giatien");
                            danhGia = jsonObject.getInt("danhgia");
                            imgnhahang = jsonObject.getString("imgnhahang");
                            mota = jsonObject.getString("mota");
                            idtk = jsonObject.getInt("idtk");
                            idstatus = jsonObject.getInt("idstatus");
                            arrayList.add(new YeuThich(iD,tennhahang,diachi,monan,tien,danhGia,imgnhahang,mota,idtk,idstatus));
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
                hashMap.put("idtk",IDTK);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        IDTK = MainActivity.ID;
        toolbarYeuThich = (Toolbar) findViewById(R.id.toolbarYeuThich);
        recyYeuThich = (RecyclerView) findViewById(R.id.recyYeuThich);
        arrayList = new ArrayList<>();
        manager = new LinearLayoutManager(YeuthichActivity.this,LinearLayoutManager.VERTICAL,false);
        giaReAdapter = new YeuThichAdapter(arrayList, YeuthichActivity.this);
        recyYeuThich.setHasFixedSize(true);
        recyYeuThich.setLayoutManager(manager);
        recyYeuThich.setAdapter(giaReAdapter);


    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarYeuThich);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarYeuThich.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
