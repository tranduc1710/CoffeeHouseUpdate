package com.example.coffeehouse.nhom2;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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

public class ChitietQLNHActivity extends AppCompatActivity {
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
    private TextView tvGiatien;
    private TextView tvbantrong;
    private TextView tvGioithieu;
    private int iD;
    private int tien;
    private int danhGia;
    private String tennhahang;
    private String imgnhahang;
    private String mota;
    private String diachi;
    private String monan;
    RelativeLayout relativeLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_qlnh);
        AnhXa();
        GetThongTin();
        SKText();
        ActionToolbar();
    }

    private void AnhXa() {
        toolbarChitiet = (Toolbar) findViewById(R.id.toolbarChitietQL);
        imgChitiet = (ImageView) findViewById(R.id.imgChitietQL);
        tvNameChitiet = (TextView) findViewById(R.id.tvNameChitietQL);
        imgsaochi1 = (ImageView) findViewById(R.id.imgsaochi1QL);
        imgsaochi2 = (ImageView) findViewById(R.id.imgsaochi2QL);
        imgsaochi3 = (ImageView) findViewById(R.id.imgsaochi3QL);
        imgsaochi4 = (ImageView) findViewById(R.id.imgsaochi4QL);
        imgsaochi5 = (ImageView) findViewById(R.id.imgsaochi5QL);
        tvDiachitiet = (TextView) findViewById(R.id.tvDiachitietQL);
        tvMonanchiet = (TextView) findViewById(R.id.tvMonanchietQL);
        tvGiatien = (TextView) findViewById(R.id.tvGiatienQL);
        tvbantrong = (TextView) findViewById(R.id.tvbantrongQL);
        tvGioithieu = (TextView) findViewById(R.id.tvGioithieuQL);
        relativeLayout = findViewById(R.id.menu_progressbarChiTietNH);
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
        toolbarChitiet.setTitle(tennhahang);
    }
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
        tvMonanchiet.setText("Loại bàn: "+monan +"người");
        tvGiatien.setText("Máy lạnh: "+tien);
        tvbantrong.setText("Còn trống");
        tvbantrong.setTextColor(Color.parseColor("#E1DB6373"));
        tvGioithieu.setText("Mô tả: "+mota);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main4, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.itemBanAn:
                Intent intent1 = new Intent(ChitietQLNHActivity.this,Main2Activity.class);
                intent1.putExtra("IDNH",""+iD);
                intent1.putExtra("NAMENH",""+tennhahang);
                intent1.putExtra("IMGNH",imgnhahang);
                startActivity(intent1);
                break;
            case R.id.itemSuaNH:
                Intent intent = new Intent(ChitietQLNHActivity.this,SuaNHActivity.class);
                intent.putExtra("ID",""+iD);
                intent.putExtra("TENNH",tennhahang);
                intent.putExtra("DIACHINH",diachi);
                intent.putExtra("MONANNH",monan);
                intent.putExtra("GIANH",""+tien);
                intent.putExtra("DANHGIANH",""+danhGia);
                intent.putExtra("IMGNH",imgnhahang);
                intent.putExtra("MOTANH",monan);
                startActivity(intent);
                break;
            case R.id.itemXoaNH:
                AlertDialog.Builder builder = new AlertDialog.Builder(ChitietQLNHActivity.this);
                builder.setMessage("Bạn có muốn xóa bàn không");
                builder.setCancelable(false);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        XoaNH();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }
    private void XoaNH(){
        relativeLayout.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanxoaNH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(ChitietQLNHActivity.this,QuanLyNHActivity.class);
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
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);

    }
}
