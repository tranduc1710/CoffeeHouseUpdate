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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ThemNHActivity extends AppCompatActivity {
    private Toolbar toolbarThemNH;
    private ImageView imgAnhNH;
    private ImageView imgThemAnhNH;
    private TextInputLayout tiTennhahang;
    private TextInputEditText edTennhahang;
    private TextInputLayout tiDiaChi;
    private TextInputEditText edDiachi;
    private TextInputLayout tiMonAn;
    private TextInputEditText edMonAn;
    private TextInputLayout tiGiatien;
    private TextInputEditText edGiatien;
    private TextInputLayout tiDanhgia;
    private TextInputEditText edDanhgia;
    private TextInputLayout tiMota;
    private TextInputEditText edMota;
    private Button btnThemNH;
    private int REQUET_FODER = 999;
    Bitmap bitmap;
    RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nh);
        AnhXa();
        SetToobar();
        EventOnClick();
    }

    private void SetToobar() {
        setSupportActionBar(toolbarThemNH);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarThemNH.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void EventOnClick() {
        imgThemAnhNH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(ThemNHActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUET_FODER);
            }
        });
        btnThemNH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameNH = edTennhahang.getText().toString().trim();
                final String diachi = edDiachi.getText().toString().trim();
                final String monan = edMonAn.getText().toString().trim();
                final String tien = edGiatien.getText().toString().trim();
                final String danhgia = edDanhgia.getText().toString().trim();
                int danhgia1 = Integer.valueOf(danhgia);
                final String mota = edMota.getText().toString().trim();
                if (bitmap == null){
                    Toast.makeText(ThemNHActivity.this, "Yêu cầu chọn ảnh", Toast.LENGTH_SHORT).show();
                } else if (nameNH.equals("")){
                    edTennhahang.setError("Yêu cầu nhập dữ liệu");
                }else if (diachi.equals("")){
                    edDiachi.setError("Yêu cầu nhập dữ liệu");
                }else if (monan.equals("")){
                    edMonAn.setError("Yêu cầu nhập dữ liệu");
                }else if (tien.equals("")){
                    edGiatien.setError("Yêu cầu nhập dữ liệu");
                }else if (danhgia.equals("")){
                    edDanhgia.setError("Yêu cầu nhập dữ liệu");
                }else if (danhgia1 > 6){
                    edDanhgia.setError("Đánh giá 1 đến 5 sao");
                } else if (mota.equals("")){
                    edMota.setError("Yêu cầu nhập dữ liệu");
                }else {
                    relativeLayout.setVisibility(View.VISIBLE);
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanthemNH, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent intent = new Intent(ThemNHActivity.this,QuanLyNHActivity.class);
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
                            String imageData = imageToString(bitmap);
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

    private void AnhXa() {
        toolbarThemNH = (Toolbar) findViewById(R.id.toolbarThemNH);
        imgAnhNH = (ImageView) findViewById(R.id.imgAnhNH);
        imgThemAnhNH = (ImageView) findViewById(R.id.imgThemAnhNH);
        tiTennhahang = (TextInputLayout) findViewById(R.id.tiTennhahang);
        edTennhahang = (TextInputEditText) findViewById(R.id.edTennhahang);
        tiDiaChi = (TextInputLayout) findViewById(R.id.tiDiaChi);
        edDiachi = (TextInputEditText) findViewById(R.id.edDiachi);
        tiMonAn = (TextInputLayout) findViewById(R.id.tiMonAn);
        edMonAn = (TextInputEditText) findViewById(R.id.edMonAn);
        tiGiatien = (TextInputLayout) findViewById(R.id.tiGiatien);
        edGiatien = (TextInputEditText) findViewById(R.id.edGiatien);
        tiDanhgia = (TextInputLayout) findViewById(R.id.tiDanhgia);
        edDanhgia = (TextInputEditText) findViewById(R.id.edDanhgia);
        tiMota = (TextInputLayout) findViewById(R.id.tiMota);
        edMota = (TextInputEditText) findViewById(R.id.edMota);
        btnThemNH = (Button) findViewById(R.id.btnThemNH);
        relativeLayout = findViewById(R.id.menu_progressbarThemNH);
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
                imgAnhNH.setImageBitmap(bitmap);

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
