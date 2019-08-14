package com.example.coffeehouse.nhom2.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.coffeehouse.nhom2.R;
import com.example.coffeehouse.nhom2.model.BanAn;
import com.example.coffeehouse.nhom2.model.MyItemDatBan;
import com.example.coffeehouse.nhom2.unti.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BuaAnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  ArrayList<BanAn> arrayList;
    private   Context context;
    private  MyItemDatBan myItemDatBan;

    public BuaAnAdapter(ArrayList<BanAn> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public void setMyItemDatBan(MyItemDatBan myItemDatBan) {
        this.myItemDatBan = myItemDatBan;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_layout3,viewGroup,false);
        return new ViewHodelok(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final BanAn banAn = arrayList.get(i);
        final ViewHodelok itemHoldel = (ViewHodelok) viewHolder;
        Picasso.get().load(Server.duongdananh + banAn.getImgBuaAn())
                .placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar)
                .into(itemHoldel.imgNH);
        itemHoldel.tvNameNH.setText(""+banAn.getNameNH());
        itemHoldel.tvSoBan.setText("Loại : "+banAn.getSoBan());
        itemHoldel.tvSonguoi.setText(banAn.getSoNguoi()+" người");

        if (banAn.getTrangThai() == 1){
            itemHoldel.btnNH.setBackgroundColor(0xCC00CC00);
            itemHoldel.btnNH.setText("Đặt bàn");
            itemHoldel.btnNH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myItemDatBan.onClick(arrayList.get(i));
                }
            });

        }
        if (banAn.getTrangThai() == 2){
            itemHoldel.btnNH.setBackgroundColor(0xFFFF0000);
            itemHoldel.btnNH.setText("Đã Đặt");
            itemHoldel.btnNH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHodelok extends RecyclerView.ViewHolder{
        private ImageView imgNH;
        private TextView tvNameNH,tvSoBan,tvSonguoi;
        private Button btnNH;
        public ViewHodelok(@NonNull View itemView) {
            super(itemView);
            tvSonguoi = itemView.findViewById(R.id.tvSoNguoi);
            tvSoBan = itemView.findViewById(R.id.tvNameBan);
            tvNameNH = itemView.findViewById(R.id.tvNhaHangBa);
            imgNH = itemView.findViewById(R.id.imgBanan);
            btnNH = itemView.findViewById(R.id.btnDatbanan);
        }
    }
}
