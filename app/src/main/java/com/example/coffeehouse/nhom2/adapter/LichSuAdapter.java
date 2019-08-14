package com.example.coffeehouse.nhom2.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coffeehouse.nhom2.R;
import com.example.coffeehouse.nhom2.model.LichSu;
import com.example.coffeehouse.nhom2.model.OnClickHuyDB;
import com.example.coffeehouse.nhom2.model.OnClickXoaLS;
import com.example.coffeehouse.nhom2.unti.Server;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LichSuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<LichSu> arrayList;
    Context context;
    OnClickHuyDB onClickHuyDB;
    OnClickXoaLS onClickXoaLS;

    public LichSuAdapter(ArrayList<LichSu> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public void setOnClickHuyDB(OnClickHuyDB onClickHuyDB) {
        this.onClickHuyDB = onClickHuyDB;
    }

    public void setOnClickXoaLS(OnClickXoaLS onClickXoaLS) {
        this.onClickXoaLS = onClickXoaLS;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_layout4,viewGroup,false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
         final LichSu lichSu = arrayList.get(i);
         ViewHodel itemHolder = (ViewHodel) viewHolder;
         Picasso.get().load(Server.duongdananh + lichSu.getImgNH())
                .placeholder(R.drawable.ic_avatar)
                .error(R.drawable.ic_avatar)
                .into(itemHolder.imgLichSu);
         itemHolder.tvNameLS.setText(lichSu.getNameNH());
         if (lichSu.getBuaAn() == 1){
             itemHolder.tvBuaAnLS.setTextColor(0xFFFF6600);
             itemHolder.tvBuaAnLS.setText("Đồ uống");
         }
         if (lichSu.getBuaAn() == 2){
             itemHolder.tvBuaAnLS.setTextColor(0xCC00CCFF);
             itemHolder.tvBuaAnLS.setText("Đồ ăn");
         }
             itemHolder.tvSoBanLS.setText("Tầng "+lichSu.getSoBan());
         if (lichSu.getTrangThai() == 1){
             itemHolder.tvTrangThaiLS.setTextColor(0xCC00CC00);
             itemHolder.tvTrangThaiLS.setText("Đang đặt bàn");
             itemHolder.tvTrangThaiLS.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     AlertDialog.Builder builder = new AlertDialog.Builder(context);
                     builder.setMessage("Bạn có muốn hủy đặt bàn không");
                     builder.setCancelable(false);
                     builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             onClickHuyDB.OnClick(arrayList.get(i));
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
         if (lichSu.getTrangThai() == 2){
             itemHolder.tvTrangThaiLS.setTextColor(0xCC00CC00);
             itemHolder.tvTrangThaiLS.setText("Hoàn thành");
             itemHolder.tvTrangThaiLS.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                 }
             });
         }
         if (lichSu.getTrangThai() == 3){
             itemHolder.tvTrangThaiLS.setTextColor(0xFFFF0000);
             itemHolder.tvTrangThaiLS.setText("Đã hủy");
             itemHolder.tvTrangThaiLS.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     AlertDialog.Builder builder = new AlertDialog.Builder(context);
                     builder.setMessage("Bạn có muốn xóa lịch sử không");
                     builder.setCancelable(false);
                     builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             onClickXoaLS.onClick(arrayList.get(i));
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

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder{
        private ImageView imgLichSu;
        private TextView tvNameLS,tvBuaAnLS,tvSoBanLS,tvTrangThaiLS;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            imgLichSu = itemView.findViewById(R.id.imgLichSu);
            tvNameLS = itemView.findViewById(R.id.tvNameNHLS);
            tvBuaAnLS = itemView.findViewById(R.id.tvBuaAnLS);
            tvSoBanLS = itemView.findViewById(R.id.tvSoBanLS);
            tvTrangThaiLS = itemView.findViewById(R.id.tvTrangThaiLS);

        }
    }
}
