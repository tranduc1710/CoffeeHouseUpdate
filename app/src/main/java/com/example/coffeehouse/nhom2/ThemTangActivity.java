package com.example.coffeehouse.nhom2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThemTangActivity extends AppCompatActivity {
    private ImageView imgAnhNHTBA;
    private TextView tvNameNHTBA;
    private TextInputLayout tiSoban;
    private TextInputEditText edSoban;
    private TextInputLayout tiSonguoi;
    private TextInputEditText edSonguoi;
    private RadioButton radioBuatrua;
    private RadioButton radioBuatoi;
    private Button btnThemBan;
    private String IMGNH,IDNH,NAMENH;
    private Toolbar toolbarTBA;
    private int buaan;
    private RelativeLayout menu_progressbarThemBan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ban);
        AnhXa();
        AcctionBar();
        Thongtin();
        EventOnclick();
    }

    private void EventOnclick() {
        btnThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String songuoi = edSonguoi.getText().toString().trim();
                final String soban =  edSoban.getText().toString().trim();
                if (radioBuatrua.isChecked()){
                    buaan = 1;
                }
                if (radioBuatoi.isChecked()){
                    buaan = 2;
                }
                if (soban.equals("")){
                    edSoban.setError("Yêu cầu nhập dữ liệu");
                }else if (songuoi.equals("")){
                    edSonguoi.setError("Yêu cầu nhập dữ liệu");
                }else {
                    menu_progressbarThemBan.setVisibility(View.VISIBLE);
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanbuaan, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String sobanan;
                            if (response != null ) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        sobanan = jsonObject.getString("soban");
                                        if (soban.equals(sobanan)){
                                            final AlertDialog.Builder builder = new AlertDialog.Builder(ThemTangActivity.this);
                                            builder.setTitle("Bàn ăn không được trùng nhau");
                                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            });
                                            menu_progressbarThemBan.setVisibility(View.GONE);
                                            builder.show();
                                            return;
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            GetData(soban,songuoi);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                           HashMap<String,String> hashMap = new HashMap<>();
                           hashMap.put("idbuaan",""+buaan);
                           hashMap.put("idnhahang",IDNH);
                           return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    private void AcctionBar() {
        setSupportActionBar(toolbarTBA);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTBA.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Thongtin() {
        IMGNH = Main2Activity.IMGNH;
        IDNH = Main2Activity.IDNH;
        NAMENH = Main2Activity.NAMENH;
        Picasso.get().load(Server.duongdananh+IMGNH).placeholder(R.drawable.ic_avatar).error(R.drawable.ic_avatar)
                .into(imgAnhNHTBA);
        tvNameNHTBA.setText(NAMENH);


    }

    private void AnhXa() {
        imgAnhNHTBA = (ImageView) findViewById(R.id.imgAnhNHTBA);
        tvNameNHTBA = (TextView) findViewById(R.id.tvNameNHTBA);
        tiSoban = (TextInputLayout) findViewById(R.id.tiSoban);
        edSoban = (TextInputEditText) findViewById(R.id.edSoban);
        tiSonguoi = (TextInputLayout) findViewById(R.id.tiSonguoi);
        edSonguoi = (TextInputEditText) findViewById(R.id.edSonguoi);
        radioBuatrua = (RadioButton) findViewById(R.id.radioBuatrua);
        radioBuatoi = (RadioButton) findViewById(R.id.radioBuatoi);
        btnThemBan = (Button) findViewById(R.id.btnThemBan);
        toolbarTBA = findViewById(R.id.toolbarTBA);
        menu_progressbarThemBan = findViewById(R.id.menu_progressbarThemBan);
    }
    private void GetData(final String soban, final String songuoi){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanthemban, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(ThemTangActivity.this,Main2Activity.class);
                intent.putExtra("IDNH",""+IDNH);
                intent.putExtra("NAMENH",""+NAMENH);
                intent.putExtra("IMGNH",IMGNH);
                startActivity(intent);
                menu_progressbarThemBan.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("idnhahang", IDNH);
                hashMap.put("buaan", "" + buaan);
                hashMap.put("songuoi",songuoi);
                hashMap.put("trangthai",""+1);
                hashMap.put("soban",soban);
                hashMap.put("namenh",NAMENH);
                hashMap.put("imgbanan",IMGNH);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
