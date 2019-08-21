package com.example.coffeehouse.nhom2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coffeehouse.nhom2.unti.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChitietActivity extends AppCompatActivity {
    private Toolbar toolbarChitiet;
    private ImageView imgChitiet;
    private TextView tvNameChitiet;
    private ImageView imgsaochi1;
    private ImageView imgsaochi2;
    private ImageView imgsaochi3;
    private ImageView imgsaochi4;
    private ImageView imgsaochi5;
    private TextView tvDiachitiet;
    private TextView tvMonanchiet;
    private TextView tvbantrong;
    private TextView tvGioithieu;
    private TextView tvGiathanh;
    private Button btnDatBan;
    private int iD;
    private int tien;
    private int danhGia;
    private String tennhahang;
    private String imgnhahang;
    private String mota;
    private String diachi;
    private String monan;
    TextView tvTT;
    ImageView imgtym;
    boolean yeu = true;
    boolean yeu1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        AnhXa();
        GetThongTin();
        ActionToolbar();
        SKText();
//        CheckLove();
        btnDatBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ChitietActivity.this,DatBanActivity.class);
                intent.putExtra("IDNH",""+iD);
                intent.putExtra("IMGNH",imgnhahang);
                startActivity(intent);
            }
        });

    }
    private void XoaYT(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanyeuthich, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int IDSTATUS;
                if (response != null  ) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            IDSTATUS = jsonObject.getInt("idstatus");
                            final int   IDSTATUS1 = jsonObject.getInt("id");
                            if (iD == IDSTATUS){
                                RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.duongdanyeuthichxoa, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        HashMap<String,String> hashMap = new HashMap<>();
                                        hashMap.put("idsta",""+IDSTATUS1);
                                        return hashMap;
                                    }
                                };
                                requestQueue1.add(stringRequest1);
                                return;
                            }
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
                String IDTK = MainActivity.ID;
                hashMap.put("idtk",IDTK);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void Themlove(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanyeuthichthem, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                String IDTK = MainActivity.ID;
                hashMap.put("tennhahang",tennhahang);
                hashMap.put("diachi",diachi);
                hashMap.put("monan",monan);
                hashMap.put("giatien",""+tien);
                hashMap.put("danhgia",""+danhGia);
                hashMap.put("imgnhahang",imgnhahang);
                hashMap.put("mota",mota);
                hashMap.put("idtk",IDTK);
                hashMap.put("idstatus",""+iD);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
//    private void CheckLove() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanyeuthich, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                int IDSTATUS;
//                if (response != null  ) {
//                    try {
//                        JSONArray jsonArray = new JSONArray(response);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject = jsonArray.getJSONObject(i);
//                            IDSTATUS = jsonObject.getInt("idstatus");
//                            if (iD == IDSTATUS){
//                                imgtym.setImageResource(R.drawable.ic_love);
//                                imgtym.setVisibility(View.VISIBLE);
//                                imgtym.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        if (yeu == true){
//                                            XoaYT();
//                                            imgtym.setImageResource(R.drawable.ic_tim_den);
//                                            yeu = false;
//                                            return;
//                                        }
//                                        if (yeu == false){
//                                            Themlove();
//                                            imgtym.setImageResource(R.drawable.ic_love);
//                                            yeu = true;
//                                            return;
//                                        }
//                                    }
//                                });
//                                return;
//                            }
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                imgtym.setImageResource(R.drawable.ic_tim_den);
//                imgtym.setVisibility(View.VISIBLE);
//                imgtym.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (yeu1 == true){
//                            Themlove();
//                            imgtym.setImageResource(R.drawable.ic_love);
//                            yeu1 = false;
//                            return;
//                        }
//                        if (yeu1 == false){
//                            XoaYT();
//                            imgtym.setImageResource(R.drawable.ic_tim_den);
//                            yeu1 = true;
//                            return;
//                        }
//                    }
//                });
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> hashMap = new HashMap<>();
//                String IDTK = MainActivity.ID;
//                hashMap.put("idtk",IDTK);
//                return hashMap;
//            }
//        };
//        requestQueue.add(stringRequest);
//
//
//    }

    private void SKText() {
        Picasso.get().load(Server.duongdananh + imgnhahang)
                .placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar)
                .into(imgChitiet);
        tvNameChitiet.setMaxLines(1);
        tvNameChitiet.setEllipsize(TextUtils.TruncateAt.END);
        tvNameChitiet.setText(tennhahang);
        if (danhGia == 1){
            imgsaochi1.setImageResource(R.drawable.ic_sao);
            imgsaochi2.setImageResource(R.drawable.ic_sao_den);
            imgsaochi3.setImageResource(R.drawable.ic_sao_den);
            imgsaochi4.setImageResource(R.drawable.ic_sao_den);
            imgsaochi5.setImageResource(R.drawable.ic_sao_den);
        }
        if (danhGia == 2){
            imgsaochi1.setImageResource(R.drawable.ic_sao);
            imgsaochi2.setImageResource(R.drawable.ic_sao);
            imgsaochi3.setImageResource(R.drawable.ic_sao_den);
            imgsaochi4.setImageResource(R.drawable.ic_sao_den);
            imgsaochi5.setImageResource(R.drawable.ic_sao_den);
        }
        if (danhGia == 3){
            imgsaochi1.setImageResource(R.drawable.ic_sao);
            imgsaochi2.setImageResource(R.drawable.ic_sao);
            imgsaochi3.setImageResource(R.drawable.ic_sao);
            imgsaochi4.setImageResource(R.drawable.ic_sao_den);
            imgsaochi5.setImageResource(R.drawable.ic_sao_den);
        }
        if (danhGia == 4){
            imgsaochi1.setImageResource(R.drawable.ic_sao);
            imgsaochi2.setImageResource(R.drawable.ic_sao);
            imgsaochi3.setImageResource(R.drawable.ic_sao);
            imgsaochi4.setImageResource(R.drawable.ic_sao);
            imgsaochi5.setImageResource(R.drawable.ic_sao_den);
        }
        if (danhGia == 5){
            imgsaochi1.setImageResource(R.drawable.ic_sao);
            imgsaochi2.setImageResource(R.drawable.ic_sao);
            imgsaochi3.setImageResource(R.drawable.ic_sao);
            imgsaochi4.setImageResource(R.drawable.ic_sao);
            imgsaochi5.setImageResource(R.drawable.ic_sao);
        }
        tvDiachitiet.setText("Tầng: "+diachi);
        tvMonanchiet.setText("Loại bàn: "+monan + "người");
        tvGiathanh.setText("Máy lạnh: "+tien);
        tvbantrong.setText("Còn trống");
        tvbantrong.setTextColor(0xCC00CC00);
        tvGioithieu.setText("Mô tả: "+mota);
    }

    private void GetThongTin() {
        Intent intent = getIntent();
        iD = Integer.valueOf(intent.getStringExtra("ID"));
        tennhahang = intent.getStringExtra("TENNH");
        diachi = intent.getStringExtra("DIACHINH");
        monan = intent.getStringExtra("MONANNH");
//        tien = Integer.valueOf(intent.getStringExtra("GIANH"));
        danhGia = Integer.valueOf(intent.getStringExtra("DANHGIANH"));
        imgnhahang = intent.getStringExtra("IMGNH");
        mota = intent.getStringExtra("MOTANH");
        tvTT.setText(tennhahang);
//        Log.e("data", iD + " |ten " + tennhahang + " |dia chi " + diachi + " |mon an " + monan + " |danh gia " + danhGia + " |mo ta " + mota);
    }

    private void AnhXa() {
        toolbarChitiet = (Toolbar) findViewById(R.id.toolbarChitiet);
        imgChitiet = (ImageView) findViewById(R.id.imgChitiet);
        tvNameChitiet = (TextView) findViewById(R.id.tvNameChitiet);
        imgsaochi1 = (ImageView) findViewById(R.id.imgsaochi1);
        imgsaochi2 = (ImageView) findViewById(R.id.imgsaochi2);
        imgsaochi3 = (ImageView) findViewById(R.id.imgsaochi3);
        imgsaochi4 = (ImageView) findViewById(R.id.imgsaochi4);
        imgsaochi5 = (ImageView) findViewById(R.id.imgsaochi5);
        tvDiachitiet = (TextView) findViewById(R.id.tvDiachitiet);
        tvMonanchiet = (TextView) findViewById(R.id.tvMonanchiet);
        tvbantrong = (TextView) findViewById(R.id.tvbantrong);
        tvGioithieu = (TextView) findViewById(R.id.tvGioithieu);
        btnDatBan = (Button) findViewById(R.id.btnDatBan);
        tvGiathanh = findViewById(R.id.tvGiatien);
        tvTT = findViewById(R.id.tvTiltle);
//        imgtym = findViewById(R.id.imgTym);

    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
