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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class SuaThongtinActivity extends AppCompatActivity {
    private Toolbar toolbarSuaTT;
    private CircleImageView imgadd;
    private TextInputEditText edHoten;
    private TextInputEditText edEmail;
    private Button btnSuaTT;
    private RelativeLayout relativeLayout;
    private int REQUET_FODER = 999;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thongtin);
        AnhXa();
        ActionBar();
        GetThongtin();
        EventOnClick();
    }

    private void ActionBar() {
        setSupportActionBar(toolbarSuaTT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarSuaTT.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void EventOnClick() {
        imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(SuaThongtinActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUET_FODER);
            }
        });
        btnSuaTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String hoten = edHoten.getText().toString().trim();
                final String email = edEmail.getText().toString().trim();
                if (hoten.equals("")){
                    edHoten.setError("Yêu cầu nhập dữ liệu");
                }else if (email.equals("")){
                    edEmail.setError("Yêu cầu nhập dữ liệu");
                }else {
                    relativeLayout.setVisibility(View.VISIBLE);
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdananhdaidien, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Intent intent = new Intent(SuaThongtinActivity.this,LoginActivity.class);
                            startActivity(intent);
                            relativeLayout.setVisibility(View.GONE);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdananhdaidien, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Intent intent = new Intent(SuaThongtinActivity.this,LoginActivity.class);
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
                                    hashMap.put("idtk",MainActivity.ID);
                                    hashMap.put("hoten",hoten);
                                    hashMap.put("email",email);
                                    hashMap.put("imgadd","");
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
                            hashMap.put("idtk",MainActivity.ID);
                            hashMap.put("hoten",hoten);
                            hashMap.put("email",email);
                            hashMap.put("imgadd",imageData);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    private void GetThongtin() {
        edHoten.setText(MainActivity.hoten);
        edEmail.setText(MainActivity.email);
        Picasso.get().load(Server.duongdananh+MainActivity.image).placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar).into(imgadd);

    }

    private void AnhXa() {
        toolbarSuaTT = (Toolbar) findViewById(R.id.toolbarSuaTT);
        imgadd = (CircleImageView) findViewById(R.id.imgadd);
        edHoten = (TextInputEditText) findViewById(R.id.edHoten);
        edEmail = (TextInputEditText) findViewById(R.id.edEmail);
        btnSuaTT = (Button) findViewById(R.id.btnSuaTT);
        relativeLayout = (RelativeLayout) findViewById(R.id.menu_progressbarSuaTT);
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
                imgadd.setImageBitmap(bitmap);

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
