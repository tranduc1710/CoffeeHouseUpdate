package com.example.coffeehouse.nhom2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coffeehouse.nhom2.adapter.GiaReAdapter;
import com.example.coffeehouse.nhom2.adapter.NhaHangAdapter;
import com.example.coffeehouse.nhom2.model.MyItemOnClick;
import com.example.coffeehouse.nhom2.model.NhaHang;
import com.example.coffeehouse.nhom2.unti.Server;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView, recyclerView2, recyclerView3, recyclerView4;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ImageView facebook, callphone, website;
    private TextView tvName;
    private CircleImageView imgName;
    private TextView tvxemTC1, tvxemTC2, tvxemTC3, tvxemTC4;
    public static String ID, username, password, email, hoten, image;
    ArrayList<NhaHang> mangstatus, mangstatus2, mangstatus3, mangstatus4;
    LinearLayoutManager manager, manager2, manager3, manager4;
    NhaHangAdapter statusAdapter, statusAdapter2, statusAdapter3;
    GiaReAdapter statusAdapter4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetThongTin();
        AnhXa();
        GetData();
        GetData1();
        GetData2();
        GetData3();
        EventOnclick();
        imageclick();
    }

    private void EventOnclick() {
        tvxemTC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NhaHang2Activity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("HOTEN", hoten);
                intent.putExtra("EMAIL", email);
                intent.putExtra("TAIKHOAN", username);
                intent.putExtra("MATKHAU", password);
                intent.putExtra("IMAGE", image);
                intent.putExtra("SOLON", "" + 999999999);
                intent.putExtra("SONHO", "" + 0);
                startActivity(intent);
            }
        });
        tvxemTC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NhaHang2Activity.class);
                intent.putExtra("SOLON", "" + 999999999);
                intent.putExtra("SONHO", "" + 400000);
                startActivity(intent);
            }
        });
        tvxemTC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NhaHang2Activity.class);
                intent.putExtra("SOLON", "" + 399999);
                intent.putExtra("SONHO", "" + 200000);
                startActivity(intent);
            }
        });
        tvxemTC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NhaHang2Activity.class);
                intent.putExtra("SOLON", "" + 199999);
                intent.putExtra("SONHO", "" + 0);
                startActivity(intent);
            }
        });
        statusAdapter.setMyItemOnClick(new MyItemOnClick() {
            @Override
            public void onClick(NhaHang nhaHang) {
                Intent intent = new Intent(MainActivity.this, ChitietActivity.class);
                intent.putExtra("ID", "" + nhaHang.getId());
                intent.putExtra("TENNH", nhaHang.getTennhahang());
                intent.putExtra("DIACHINH", nhaHang.getDiachi());
                intent.putExtra("MONANNH", nhaHang.getMonan());
                intent.putExtra("GIANH", "" + nhaHang.getGiatien());
                intent.putExtra("DANHGIANH", "" + nhaHang.getDanhgia());
                intent.putExtra("IMGNH", nhaHang.getImgnhahang());
                intent.putExtra("MOTANH", nhaHang.getMota());
                startActivity(intent);
            }
        });
        statusAdapter2.setMyItemOnClick(new MyItemOnClick() {
            @Override
            public void onClick(NhaHang nhaHang) {
                Intent intent = new Intent(MainActivity.this, ChitietActivity.class);
                intent.putExtra("ID", "" + nhaHang.getId());
                intent.putExtra("TENNH", nhaHang.getTennhahang());
                intent.putExtra("DIACHINH", nhaHang.getDiachi());
                intent.putExtra("MONANNH", nhaHang.getMonan());
                intent.putExtra("GIANH", "" + nhaHang.getGiatien());
                intent.putExtra("DANHGIANH", "" + nhaHang.getDanhgia());
                intent.putExtra("IMGNH", nhaHang.getImgnhahang());
                intent.putExtra("MOTANH", nhaHang.getMota());
                startActivity(intent);
            }
        });
        statusAdapter3.setMyItemOnClick(new MyItemOnClick() {
            @Override
            public void onClick(NhaHang nhaHang) {
                Intent intent = new Intent(MainActivity.this, ChitietActivity.class);
                intent.putExtra("ID", "" + nhaHang.getId());
                intent.putExtra("TENNH", nhaHang.getTennhahang());
                intent.putExtra("DIACHINH", nhaHang.getDiachi());
                intent.putExtra("MONANNH", nhaHang.getMonan());
                intent.putExtra("GIANH", "" + nhaHang.getGiatien());
                intent.putExtra("DANHGIANH", "" + nhaHang.getDanhgia());
                intent.putExtra("IMGNH", nhaHang.getImgnhahang());
                intent.putExtra("MOTANH", nhaHang.getMota());
                startActivity(intent);
            }
        });
        statusAdapter4.setMyItemOnClick(new MyItemOnClick() {
            @Override
            public void onClick(NhaHang nhaHang) {
                Intent intent = new Intent(MainActivity.this, ChitietActivity.class);
                intent.putExtra("ID", "" + nhaHang.getId());
                intent.putExtra("TENNH", nhaHang.getTennhahang());
                intent.putExtra("DIACHINH", nhaHang.getDiachi());
                intent.putExtra("MONANNH", nhaHang.getMonan());
                intent.putExtra("GIANH", "" + nhaHang.getGiatien());
                intent.putExtra("DANHGIANH", "" + nhaHang.getDanhgia());
                intent.putExtra("IMGNH", nhaHang.getImgnhahang());
                intent.putExtra("MOTANH", nhaHang.getMota());
                startActivity(intent);
            }
        });

    }

    private void GetData3() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdangiare, new Response.Listener<String>() {
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
                if (response != null) {
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
                            mangstatus4.add(new NhaHang(iD, tennhahang, diachi, monan, tien, danhGia, imgnhahang, mota));
                            statusAdapter4.notifyDataSetChanged();
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

    private void GetData2() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanbinhdan, new Response.Listener<String>() {
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
                if (response != null) {
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
                            mangstatus3.add(new NhaHang(iD, tennhahang, diachi, monan, tien, danhGia, imgnhahang, mota));
                            statusAdapter3.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                shimmerFrameLayout.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void GetData1() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdansangtrong, new Response.Listener<String>() {
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
                if (response != null) {
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
                            mangstatus2.add(new NhaHang(iD, tennhahang, diachi, monan, tien, danhGia, imgnhahang, mota));
                            statusAdapter2.notifyDataSetChanged();
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

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanlayout1, new Response.Listener<String>() {
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
                if (response != null) {
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
                            mangstatus.add(new NhaHang(iD, tennhahang, diachi, monan, tien, danhGia, imgnhahang, mota));
                            statusAdapter.notifyDataSetChanged();
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

    private void GetThongTin() {
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        username = intent.getStringExtra("TAIKHOAN");
        password = intent.getStringExtra("MATKHAU");
        email = intent.getStringExtra("EMAIL");
        hoten = intent.getStringExtra("HOTEN");
        image = intent.getStringExtra("IMAGE");
    }

    private void imageclick(){
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/nghiamvit"));
                startActivity(intent);
            }
        });
        callphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "0969978411";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/nghiamvit"));
                startActivity(intent2);
            }
        });
    }

    private void AnhXa() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.inflateHeaderView(R.layout.nav_header_main);

        facebook = findViewById(R.id.facebook);
        callphone = findViewById(R.id.callphone);
        website = findViewById(R.id.website);


        tvName = view.findViewById(R.id.tvName);
        imgName = view.findViewById(R.id.imgName);
        tvName.setText(hoten);
        Picasso.get().load(Server.duongdananh + image)
                .placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar)
                .into(imgName);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);

        tvxemTC1 = findViewById(R.id.tvXemTC1);
        tvxemTC2 = findViewById(R.id.tvXemTC2);
        tvxemTC3 = findViewById(R.id.tvXemTC3);
        tvxemTC4 = findViewById(R.id.tvXemTC4);

        recyclerView = findViewById(R.id.recyclerView1);
        mangstatus = new ArrayList<>();
        manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        statusAdapter = new NhaHangAdapter(mangstatus, MainActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(statusAdapter);

        recyclerView2 = findViewById(R.id.recyclerView2);
        mangstatus2 = new ArrayList<>();
        manager2 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        statusAdapter2 = new NhaHangAdapter(mangstatus2, MainActivity.this);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(manager2);
        recyclerView2.setAdapter(statusAdapter2);

        recyclerView3 = findViewById(R.id.recyclerView3);
        mangstatus3 = new ArrayList<>();
        manager3 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        statusAdapter3 = new NhaHangAdapter(mangstatus3, MainActivity.this);
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setLayoutManager(manager3);
        recyclerView3.setAdapter(statusAdapter3);

        recyclerView4 = findViewById(R.id.recyclerView4);
        mangstatus4 = new ArrayList<>();
        manager4 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        statusAdapter4 = new GiaReAdapter(mangstatus4, MainActivity.this);
        recyclerView4.setHasFixedSize(true);
        recyclerView4.setLayoutManager(manager4);
        recyclerView4.setAdapter(statusAdapter4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, YeuthichActivity.class);
                startActivity(intent);
                break;
        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nhahang:
                Intent intent = new Intent(MainActivity.this, NhaHang2Activity.class);
                intent.putExtra("ID", ID);
                intent.putExtra("HOTEN", hoten);
                intent.putExtra("EMAIL", email);
                intent.putExtra("TAIKHOAN", username);
                intent.putExtra("MATKHAU", password);
                intent.putExtra("IMAGE", image);
                intent.putExtra("SOLON", "" + 999999999);
                intent.putExtra("SONHO", "" + 0);
                startActivity(intent);
                break;
            case R.id.datban:
                Intent intent5 = new Intent(MainActivity.this, DatBanActivity.class);
                startActivity(intent5);
                break;
            case R.id.lichsu:
                Intent intent1 = new Intent(MainActivity.this, LichSuActivity.class);
                startActivity(intent1);
                break;
            case R.id.trogiup:
                Intent intent4 = new Intent(MainActivity.this, SuaThongtinActivity.class);
                startActivity(intent4);
                break;
            case R.id.caidat:

                break;
            case R.id.doimk:
                Intent intent3 = new Intent(MainActivity.this, DoiMKActivity.class);
                startActivity(intent3);
                break;
            case R.id.dangxuat:
                Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent2);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
