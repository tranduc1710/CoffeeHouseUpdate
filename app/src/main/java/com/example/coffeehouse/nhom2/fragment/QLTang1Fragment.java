package com.example.coffeehouse.nhom2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.coffeehouse.nhom2.Main2Activity;
import com.example.coffeehouse.nhom2.MainActivity;
import com.example.coffeehouse.nhom2.R;
import com.example.coffeehouse.nhom2.SuaBanActivity;
import com.example.coffeehouse.nhom2.adapter.QLCoffeeAdapter;
import com.example.coffeehouse.nhom2.model.TangModel;
import com.example.coffeehouse.nhom2.model.MySuaBan;
import com.example.coffeehouse.nhom2.model.MyXoaBA;
import com.example.coffeehouse.nhom2.unti.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QLTang1Fragment extends Fragment {
    private int idBuaAn = 1;
    private  String IDTK;
    private   String IDNH,IMGNH;
    private  RecyclerView recyBuaTrua;
    private  ArrayList<TangModel> arrayList;
    private  LinearLayoutManager manager;
    private QLCoffeeAdapter giaReAdapter;
    private  RelativeLayout menu_progressbarBtrua;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ql_bua_trua,container,false);
        menu_progressbarBtrua = view.findViewById(R.id.menu_progressbarQLBtrua);
        recyBuaTrua = view.findViewById(R.id.recyQLBuaTrua);
        arrayList = new ArrayList<>();
        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        giaReAdapter = new QLCoffeeAdapter(getActivity(),arrayList);
        recyBuaTrua.setHasFixedSize(true);
//        recyBuaTrua.setLayoutManager(manager);
        recyBuaTrua.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyBuaTrua.setAdapter(giaReAdapter);
        IDTK = MainActivity.ID;
        Bundle bundle = getArguments();
        if (bundle != null) {
            IDNH = Main2Activity.IDNH;
            IMGNH = Main2Activity.IMGNH;
        }
        giaReAdapter.setMyXoaBA(new MyXoaBA() {
            @Override
            public void OnClick(final TangModel tangModel) {
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanxoaban, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        XoaLSTheoIDNH(tangModel.getiD());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("idbanan",""+ tangModel.getiD());
                        return hashMap;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        GetData();
        giaReAdapter.setMySuaBan(new MySuaBan() {
            @Override
            public void Onclick(TangModel tangModel) {
                Intent intent = new Intent(getActivity(), SuaBanActivity.class);
                intent.putExtra("IDBA",""+ tangModel.getiD());
                intent.putExtra("IMGBA", tangModel.getImgBuaAn());
                intent.putExtra("NAMENH", tangModel.getNameNH());
                intent.putExtra("SONGUOI",""+ tangModel.getSoNguoi());
                intent.putExtra("SOBAN",""+ tangModel.getSoBan());
                intent.putExtra("BUAAN",""+ tangModel.getBuaAn());
                intent.putExtra("IDNH",""+ tangModel.getiDNhaHang());
                startActivity(intent);
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
                            arrayList.add(new TangModel(iD,iDNhaHang,buaAn,soNguoi,trangThai,soBan,nameNH,imgBuaAn));
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
    private void XoaLSTheoIDNH(final int ID){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanxoalSN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arrayList.clear();
                GetData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("idbanan",""+ID);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }
}
