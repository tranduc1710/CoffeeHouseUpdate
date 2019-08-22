package com.example.coffeehouse.nhom2.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeehouse.nhom2.R;
import com.example.coffeehouse.nhom2.model.TangModel;
import com.example.coffeehouse.nhom2.model.MySuaBan;
import com.example.coffeehouse.nhom2.model.MyXoaBA;
import com.example.coffeehouse.nhom2.unti.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QLCoffeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<TangModel> arrayList;
    private MyXoaBA myXoaBA;
    private MySuaBan mySuaBan;

    public QLCoffeeAdapter(Context context, ArrayList<TangModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setMySuaBan(MySuaBan mySuaBan) {
        this.mySuaBan = mySuaBan;
    }

    public void setMyXoaBA(MyXoaBA myXoaBA) {
        this.myXoaBA = myXoaBA;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_layout5,viewGroup,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        TangModel tangModel = arrayList.get(i);
        ViewHodel itemHodel = (ViewHodel) viewHolder;
        Picasso.get().load(Server.duongdananh + tangModel.getImgBuaAn()).placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar).into(itemHodel.imgQLBanan);
        itemHodel.tvQLNhaHangBa.setText(tangModel.getNameNH());
//        itemHodel.tvQLNameBan.setText("Loại : "+tangModel.getSoBan());
        itemHodel.tvQLSoNguoi.setText(tangModel.getSoNguoi()+" người");

            itemHodel.btnSuaBan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mySuaBan.Onclick(arrayList.get(i));
                }
            });
            itemHodel.btnXoaBan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Bạn có muốn xóa lịch sử không");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            myXoaBA.OnClick(arrayList.get(i));
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
                }
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class ViewHodel extends RecyclerView.ViewHolder {
        public ImageView imgQLBanan;
        public TextView tvQLNhaHangBa,tvQLNameBan,tvQLSoNguoi;
        public Button btnSuaBan,btnXoaBan;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            imgQLBanan = itemView.findViewById(R.id.imgQLBanan);
            tvQLNhaHangBa = itemView.findViewById(R.id.tvQLNhaHangBa);
//            tvQLNameBan = itemView.findViewById(R.id.tvQLNameBan);
            tvQLSoNguoi = itemView.findViewById(R.id.tvQLSoNguoi);
            btnSuaBan = itemView.findViewById(R.id.btnSuaBan);
            btnXoaBan = itemView.findViewById(R.id.btnXoaBan);
        }
    }
}
