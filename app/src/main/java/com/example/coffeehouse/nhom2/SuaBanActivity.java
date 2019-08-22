package com.example.coffeehouse.nhom2;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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

import java.util.HashMap;
import java.util.Map;

public class SuaBanActivity extends AppCompatActivity {
    private Toolbar toolbarSuaBA;
    private ImageView imgAnhNHSBA;
    private TextView tvNameNHSBA;
    private TextInputLayout tiSoban;
    private TextInputEditText edSuaSoban;
    private TextInputLayout tiSonguoi;
    private TextInputEditText edSuaSonguoi;
    private RadioButton radioSuaBuatrua;
    private RadioButton radioSuaBuatoi;
    private Button btnSuaBan;
    private RelativeLayout relativeLayout;
    private String IDNH,IDBA,IMGBA,NAMENH,SONGUOI,SOBAN,BUAAN;
    int buaan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ban_an);
        AnhXa();
        ActionBar();
        GetThongtin();
        EventOnCick();
    }

    private void EventOnCick() {
        btnSuaBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String songuoi = edSuaSonguoi.getText().toString().trim();
                final String soban = edSuaSoban.getText().toString().trim();
                if (radioSuaBuatrua.isChecked()) {
                    buaan = 1;
                }
                if (radioSuaBuatoi.isChecked()) {
                    buaan = 2;
                }
                if (soban.equals("")) {
                    edSuaSoban.setError("Yêu cầu nhập dữ liệu");
                } else if (songuoi.equals("")) {
                    edSuaSonguoi.setError("Yêu cầu nhập dữ liệu");
                } else {
                    relativeLayout.setVisibility(View.VISIBLE);
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdansuabanan, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            SuaLStheoID(soban);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("idbanan",IDBA);
                            hashMap.put("buaan", ""+buaan);
                            hashMap.put("songuoi", songuoi);
                            hashMap.put("soban", soban);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    private void ActionBar() {
        setSupportActionBar(toolbarSuaBA);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSuaBA.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetThongtin() {
        Intent intent = getIntent();
        IDNH = intent.getStringExtra("IDNH");
        IDBA = intent.getStringExtra("IDBA");
        IMGBA = intent.getStringExtra("IMGBA");
        NAMENH = intent.getStringExtra("NAMENH");
        SONGUOI = intent.getStringExtra("SONGUOI");
        SOBAN = intent.getStringExtra("SOBAN");
        BUAAN =  intent.getStringExtra("BUAAN");
        Picasso.get().load(Server.duongdananh + IMGBA).placeholder(R.drawable.ic_avatar).error(R.drawable.ic_avatar)
                .into(imgAnhNHSBA);
        tvNameNHSBA.setText(NAMENH);
        edSuaSoban.setText(SOBAN);
        edSuaSonguoi.setText(SONGUOI);
        if (BUAAN.equals("1")){
            radioSuaBuatrua.setChecked(true);
        }
        if (BUAAN.equals("2")){
            radioSuaBuatoi.setChecked(true);
        }
    }

    private void AnhXa() {
        toolbarSuaBA = (Toolbar) findViewById(R.id.toolbarSuaBA);
        imgAnhNHSBA = (ImageView) findViewById(R.id.imgAnhNHSBA);
        tvNameNHSBA = (TextView) findViewById(R.id.tvNameNHSBA);
        tiSoban = (TextInputLayout) findViewById(R.id.tiSoban);
        edSuaSoban = (TextInputEditText) findViewById(R.id.edSuaSoban);
        tiSonguoi = (TextInputLayout) findViewById(R.id.tiSonguoi);
        edSuaSonguoi = (TextInputEditText) findViewById(R.id.edSuaSonguoi);
        radioSuaBuatrua = (RadioButton) findViewById(R.id.radioSuaBuatrua);
        radioSuaBuatoi = (RadioButton) findViewById(R.id.radioSuaBuatoi);
        btnSuaBan = (Button) findViewById(R.id.btnSuaBan);
        relativeLayout = (RelativeLayout) findViewById(R.id.menu_progressbarSuaBan);
    }
    private void SuaLStheoID(final String soban){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdansuaLSnhieu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(SuaBanActivity.this, Main2Activity.class);
                intent.putExtra("IDNH", "" + IDNH);
                intent.putExtra("NAMENH", "" + NAMENH);
                intent.putExtra("IMGNH", IMGBA);
                startActivity(intent);
                relativeLayout.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idbanan",IDBA);
                hashMap.put("buaan", ""+buaan);
                hashMap.put("soban", soban);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
