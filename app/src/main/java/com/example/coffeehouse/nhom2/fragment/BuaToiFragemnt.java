package com.example.coffeehouse.nhom2.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coffeehouse.nhom2.LichSuActivity;
import com.example.coffeehouse.nhom2.MainActivity;
import com.example.coffeehouse.nhom2.R;
import com.example.coffeehouse.nhom2.adapter.BuaAnAdapter;
import com.example.coffeehouse.nhom2.model.BanAn;
import com.example.coffeehouse.nhom2.model.MyItemDatBan;
import com.example.coffeehouse.nhom2.unti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuaToiFragemnt extends Fragment {
    private int idBuaAn = 2;
    private  String IDTK;
    private   String IDNH,IMGNH;
    private  RecyclerView recyBuaTrua;
    private  ArrayList<BanAn> arrayList;
    private  LinearLayoutManager manager;
    private  BuaAnAdapter giaReAdapter;
    private  RelativeLayout menu_progressbarBtrua;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.bua_toi,container,false);
        recyBuaTrua = view.findViewById(R.id.recyBuaToi);
        menu_progressbarBtrua = view.findViewById(R.id.menu_progressbarBtoi);
        arrayList = new ArrayList<>();
        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        giaReAdapter = new BuaAnAdapter(arrayList, getActivity());
        recyBuaTrua.setHasFixedSize(true);
//        recyBuaTrua.setLayoutManager(manager);
        recyBuaTrua.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyBuaTrua.setAdapter(giaReAdapter);
        IDTK = MainActivity.ID;
        Bundle bundle = getArguments();
        if (bundle != null) {
            IDNH = bundle.getString("IDNH");
            IMGNH = bundle.getString("IMGNH");
        }
        GetData();
        giaReAdapter.setMyItemDatBan(new MyItemDatBan() {
            @Override
            public void onClick(final BanAn banAn) {
                menu_progressbarBtrua.setVisibility(View.VISIBLE);
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdancheckbanan, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int trangThai;
                        if (response != null ) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    trangThai = jsonObject.getInt("trangthai");
                                    if (trangThai == 1){

                                        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                                        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdandatban, new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                PostLS(banAn.getNameNH(),banAn.getSoBan(),banAn.getiD());
                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        }){
                                            @Override
                                            protected Map<String, String> getParams() throws AuthFailureError {
                                                HashMap<String,String> hashMap = new HashMap<>();
                                                hashMap.put("idbanan",""+banAn.getiD());
                                                hashMap.put("trangthai",""+2);
                                                return hashMap;
                                            }
                                        };
                                        requestQueue.add(stringRequest);
                                        return;
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        menu_progressbarBtrua.setVisibility(View.GONE);
                        arrayList.clear();
                        GetData();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Bàn đã được đặt");
                        builder.setMessage("Xin vui lòng chọn bàn khác");
                        builder.setCancelable(false);
                        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("idbanan",""+banAn.getiD());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        return view;
    }

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanbuaan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int iD;
                int iDNhaHang;
                int buaAn;
                int soNguoi;
                int trangThai;
                int soBan;
                String nameNH;
                String imgBuaAn;
                if (response != null ) {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            iD = jsonObject.getInt("id");
                            iDNhaHang = jsonObject.getInt("idnhahang");
                            buaAn = jsonObject.getInt("buaan");
                            soNguoi = jsonObject.getInt("songuoi");
                            trangThai = jsonObject.getInt("trangthai");
                            soBan = jsonObject.getInt("soban");
                            nameNH = jsonObject.getString("namenh");
                            imgBuaAn = jsonObject.getString("imgbanan");
                            arrayList.add(new BanAn(iD,iDNhaHang,buaAn,soNguoi,trangThai,soBan,nameNH,imgBuaAn));
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("idbuaan",""+idBuaAn);
                hashMap.put("idnhahang",""+IDNH);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void PostLS(final String name, final int SBan,final int IDBanAn){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanpostlichsu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                menu_progressbarBtrua.setVisibility(View.GONE);
                Intent intent = new Intent(getContext(), LichSuActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("idnhahang",""+IDNH);
                hashMap.put("tennhahang",""+name);
                hashMap.put("idtk",""+IDTK);
                hashMap.put("imgnhahang",""+IMGNH);
                hashMap.put("trangthai",""+1);
                hashMap.put("soban",""+SBan);
                hashMap.put("buaan",""+idBuaAn);
                hashMap.put("idbanan",""+IDBanAn);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}

