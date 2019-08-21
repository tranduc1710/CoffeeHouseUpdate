package com.example.coffeehouse.nhom2;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coffeehouse.nhom2.unti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {
    private TextInputLayout layoutDKUser;
    private EditText edtDKUser;
    private TextInputLayout layoutDKpass;
    private EditText edtDKpass;
    private TextInputLayout layoutNLpass;
    private EditText edtNLpass;
    private Button btnDangKy, btnLogin;
    private Toolbar toolbar;
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhXa();
//        ActionBar();
//        CheckPass();
        EventOnClick();
    }
//
//    private void ActionBar() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }

    private void EventOnClick() {
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = edtDKUser.getText().toString().trim();
                final String pass = edtDKpass.getText().toString().trim();
                final String nlpass = edtNLpass.getText().toString().trim();
                if (user.equals("")){
                    edtDKUser.setError("Yêu cầu nhập dữ liệu");
                }else if (pass.equals("")){
                    edtDKpass.setError("Yêu cầu nhập dữ liệu");
                }else if (nlpass.equals(pass)){
                    relativeLayout.setVisibility(View.VISIBLE);
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdandangnhap, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            String username;
                            if (response != null) {

                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        username = jsonObject.getString("taikhoan");
                                        if (user.equals(username)){
                                            Toast.makeText(DangKyActivity.this, "Tài khoản đã được đăng ký", Toast.LENGTH_SHORT).show();
                                            relativeLayout.setVisibility(View.GONE);
                                                return;
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            DangKy(user,pass);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    requestQueue.add(jsonArrayRequest);
                }else {
                    edtNLpass.setError("Password không trùng");
                }
            }
        });
    }

    private void AnhXa() {
//        layoutDKUser = (TextInputLayout) findViewById(R.id.layout_DKUser);
        edtDKUser = (EditText) findViewById(R.id.edt_DKUser);
//        layoutDKpass = (TextInputLayout) findViewById(R.id.layout_DKpass);
        edtDKpass = (EditText) findViewById(R.id.edt_DKpass);
//        layoutNLpass = (TextInputLayout) findViewById(R.id.layout_NLpass);
        edtNLpass = (EditText) findViewById(R.id.edt_NLpass);
        btnDangKy = (Button) findViewById(R.id.btn_Dang_Ky);
        relativeLayout = (RelativeLayout) findViewById(R.id.menu_progressbarDK);
//        toolbar = findViewById(R.id.toolbarDK);
    }
//    private void CheckPass() {
//        edtDKpass.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() == 0){
//                    layoutDKpass.setPasswordVisibilityToggleEnabled(false);
//                }else {
//                    layoutDKpass.setPasswordVisibilityToggleEnabled(true);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        edtNLpass.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() == 0){
//                    layoutNLpass.setPasswordVisibilityToggleEnabled(false);
//                }else {
//                    layoutNLpass.setPasswordVisibilityToggleEnabled(true);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
    private void  DangKy(final String tk, final String mk){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandangky, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(DangKyActivity.this,LoginActivity.class);
                startActivity(intent);
                relativeLayout.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("taikhoan",tk);
                hashMap.put("matkhau",mk);
                hashMap.put("email","");
                hashMap.put("hoten","");
                hashMap.put("imageok","");
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void btnLogin(View view) {
        finish();
        Intent intent5 = new Intent(DangKyActivity.this, LoginActivity.class);
        startActivity(intent5);
    }
}
