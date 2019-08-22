package com.example.coffeehouse.nhom2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coffeehouse.nhom2.unti.Server;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SuaTangActivity extends AppCompatActivity {
    private Toolbar toolbarSuaNH;
    private ImageView imgAnhSuaNH;
    private ImageView imgThemAnhSuaNH;
    private TextInputLayout tiSuaTenNH;
    private TextInputEditText edSuaTenNH;
    private TextInputLayout tiSuaDiaChi;
    private TextInputEditText edSuaDiachi;
    private TextInputLayout tiSuaMonAn;
    private TextInputEditText edSuaMonAn;
    private TextInputLayout tiSuaGiatien;
    private TextInputEditText edSuaGiatien;
    private TextInputLayout tiSuaDanhgia;
    private TextInputEditText edSuaDanhgia;
    private TextInputLayout tiSuaMota;
    private TextInputEditText edSuaMota;
    private Button btnSuaNH;
    private RelativeLayout relativeLayout;
    private int iD;
    private int tien;
    private int danhGia;
    private String tennhahang;
    private String imgnhahang;
    private String mota;
    private String diachi;
    private String monan;
    private int REQUET_FODER = 999;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_nh);
        AnhXa();
        GetThongTin();
        SetToobar();
        EventOnclick();
    }

    private void EventOnclick() {
        imgThemAnhSuaNH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(SuaTangActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUET_FODER);
            }
        });
        btnSuaNH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameNH = edSuaTenNH.getText().toString().trim();
                final String diachi = edSuaDiachi.getText().toString().trim();
                final String monan = edSuaMonAn.getText().toString().trim();
                final String tien = edSuaGiatien.getText().toString().trim();
                final String danhgia = edSuaDanhgia.getText().toString().trim();
                int danhgia1 = Integer.valueOf(danhgia);
                final String mota = edSuaMonAn.getText().toString().trim();
                 if (nameNH.equals("")){
                    edSuaTenNH.setError("Yêu cầu nhập dữ liệu");
                }else if (diachi.equals("")){
                    edSuaDiachi.setError("Yêu cầu nhập dữ liệu");
                }else if (monan.equals("")){
                    edSuaMonAn.setError("Yêu cầu nhập dữ liệu");
                }else if (tien.equals("")){
                    edSuaGiatien.setError("Yêu cầu nhập dữ liệu");
                }else if (danhgia.equals("")){
                    edSuaDanhgia.setError("Yêu cầu nhập dữ liệu");
                }else if (danhgia1 > 6){
                    edSuaDanhgia.setError("Đánh giá 1 đến 5 sao");
                } else if (mota.equals("")){
                    edSuaMota.setError("Yêu cầu nhập dữ liệu");
                }else {
                    relativeLayout.setVisibility(View.VISIBLE);
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdansuaNH, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent intent = new Intent(SuaTangActivity.this, QuanLyCoffeeActivity.class);
                            startActivity(intent);
                            relativeLayout.setVisibility(View.GONE);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdansuaNH, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Intent intent = new Intent(SuaTangActivity.this, QuanLyCoffeeActivity.class);
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
                                    hashMap.put("idnhahang",""+iD);
                                    hashMap.put("tennhahang",nameNH);
                                    hashMap.put("diachi",diachi);
                                    hashMap.put("monan",monan);
                                    hashMap.put("giatien",tien);
                                    hashMap.put("danhgia",danhgia);
                                    hashMap.put("imgnhahang","");
                                    hashMap.put("mota",mota);
                                    return hashMap;
                                }
                            };
                            requestQueue.add(stringRequest);
                        }

                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<>();
                            String imageData = imageToString(bitmap);
                            hashMap.put("idnhahang",""+iD);
                            hashMap.put("tennhahang",nameNH);
                            hashMap.put("diachi",diachi);
                            hashMap.put("monan",monan);
                            hashMap.put("giatien",tien);
                            hashMap.put("danhgia",danhgia);
                            hashMap.put("imgnhahang",imageData);
                            hashMap.put("mota",mota);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }

            }
        });
    }

    private void GetThongTin() {
        Intent intent = getIntent();
        iD = Integer.valueOf(intent.getStringExtra("ID"));
        tennhahang = intent.getStringExtra("TENNH");
        diachi = intent.getStringExtra("DIACHINH");
        monan = intent.getStringExtra("MONANNH");
        tien = Integer.valueOf(intent.getStringExtra("GIANH"));
        danhGia = Integer.valueOf(intent.getStringExtra("DANHGIANH"));
        imgnhahang = intent.getStringExtra("IMGNH");
        mota = intent.getStringExtra("MOTANH");
        Picasso.get().load(Server.duongdananh + imgnhahang)
                .placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar)
                .into(imgAnhSuaNH);
        edSuaTenNH.setText(tennhahang);
        edSuaDiachi.setText(diachi);
        edSuaMonAn.setText(monan);
        edSuaGiatien.setText(""+tien);
        edSuaDanhgia.setText(""+danhGia);
        edSuaMota.setText(mota);
    }

    private void SetToobar() {
        setSupportActionBar(toolbarSuaNH);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSuaNH.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarSuaNH = (Toolbar) findViewById(R.id.toolbarSuaNH);
        imgAnhSuaNH = (ImageView) findViewById(R.id.imgAnhSuaNH);
        imgThemAnhSuaNH = (ImageView) findViewById(R.id.imgThemAnhSuaNH);
        tiSuaTenNH = (TextInputLayout) findViewById(R.id.tiSuaTenNH);
        edSuaTenNH = (TextInputEditText) findViewById(R.id.edSuaTenNH);
        tiSuaDiaChi = (TextInputLayout) findViewById(R.id.tiSuaDiaChi);
        edSuaDiachi = (TextInputEditText) findViewById(R.id.edSuaDiachi);
        tiSuaMonAn = (TextInputLayout) findViewById(R.id.tiSuaMonAn);
        edSuaMonAn = (TextInputEditText) findViewById(R.id.edSuaMonAn);
        tiSuaGiatien = (TextInputLayout) findViewById(R.id.tiSuaGiatien);
        edSuaGiatien = (TextInputEditText) findViewById(R.id.edSuaGiatien);
        tiSuaDanhgia = (TextInputLayout) findViewById(R.id.tiSuaDanhgia);
        edSuaDanhgia = (TextInputEditText) findViewById(R.id.edSuaDanhgia);
        tiSuaMota = (TextInputLayout) findViewById(R.id.tiSuaMota);
        edSuaMota = (TextInputEditText) findViewById(R.id.edSuaMota);
        btnSuaNH = (Button) findViewById(R.id.btnSuaNH);
        relativeLayout = (RelativeLayout) findViewById(R.id.menu_progressbarSuaNH);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUET_FODER){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                  Intent intent = new  Intent(Intent.ACTION_PICK);
//                Intent intent = new  Intent(Intent.ACTION_ATTACH_DATA);
                Intent intent = new  Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),REQUET_FODER);
            }
            else{
                Toast.makeText(this, "Bạn ko có quyền truy cập thư viện", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    // hiện thị ảnh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==REQUET_FODER && resultCode == RESULT_OK && data!= null ){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgAnhSuaNH.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        byte[]  imageBytes = outputStream.toByteArray();
        String edcodeImage = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return  edcodeImage;
    }
}
