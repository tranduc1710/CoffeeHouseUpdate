package com.example.coffeehouse.nhom2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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

public class QuanLyNHActivity extends AppCompatActivity {
    private Toolbar toolbarQLNH;
    private RecyclerView recyQLNH;
    ArrayList<NhaHang> arrayList;
    LinearLayoutManager manager;
    GiaReAdapter giaReAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nh);
        toolbarQLNH = (Toolbar) findViewById(R.id.toolbarQLNH);
        recyQLNH = (RecyclerView) findViewById(R.id.recyQLNH);
        setSupportActionBar(toolbarQLNH);
        arrayList = new ArrayList<>();
        manager = new LinearLayoutManager(QuanLyNHActivity.this,LinearLayoutManager.VERTICAL,false);
        giaReAdapter = new GiaReAdapter(arrayList, QuanLyNHActivity.this);
        recyQLNH.setHasFixedSize(true);
        recyQLNH.setLayoutManager(manager);
        recyQLNH.setAdapter(giaReAdapter);
        GetData();
        giaReAdapter.setMyItemOnClick(new MyItemOnClick() {
            @Override
            public void onClick(NhaHang nhaHang) {
                Intent intent = new Intent(QuanLyNHActivity.this,ChitietQLNHActivity.class);
                intent.putExtra("ID",""+nhaHang.getId());
                intent.putExtra("TENNH",""+nhaHang.getTennhahang());
                intent.putExtra("DIACHINH",""+nhaHang.getDiachi());
                intent.putExtra("MONANNH",""+nhaHang.getMonan());
                intent.putExtra("GIANH",nhaHang.getGiatien());
                intent.putExtra("DANHGIANH",""+nhaHang.getDanhgia());
                intent.putExtra("IMGNH",nhaHang.getImgnhahang());
                intent.putExtra("MOTANH",nhaHang.getMota());
                startActivity(intent);
            }
        });
    }

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanlayout1, new Response.Listener<String>() {
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
        });
        requestQueue.add(stringRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.itemThemNH:
                Intent intent = new Intent(QuanLyNHActivity.this,ThemNHActivity.class);
                startActivity(intent);
                break;
        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }
}
