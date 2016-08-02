package com.example.romulo.studioelegancy.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.romulo.studioelegancy.ImageHelper;
import com.example.romulo.studioelegancy.R;
import com.example.romulo.studioelegancy.RecyclerViewOnClickListenerHack;
import com.example.romulo.studioelegancy.domain.Pedido;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by romulo on 28/07/16.
 */
public class PedidoAdapter  extends RecyclerView.Adapter<PedidoAdapter.MyViewHolder> {

    private Context mContext;
    private List<Pedido> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private float scale;
    private int width;
    private int height;


    public PedidoAdapter(Context c, List<Pedido> l){
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale + 0.5f);
        height = (width / 16) * 9;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View v = mLayoutInflater.inflate(R.layout.adapter_pedido, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;

    }
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {

        myViewHolder.tvModel.setText(mList.get(position).getModel());


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            myViewHolder.ivtipo.setImageResource(mList.get(position).getPhoto());
        }
        else{
            Bitmap bitmap = BitmapFactory.decodeResource( mContext.getResources(), mList.get(position).getPhoto());
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

            bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 4, width, height, false, false, true, true);
            myViewHolder.ivtipo.setImageBitmap(bitmap);
        }

        try{
            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .playOn(myViewHolder.itemView);
        }
        catch(Exception e){}
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }


    public void addListItem(Pedido c, int position){
        mList.add(c);
        notifyItemInserted(position);
    }


    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView ivtipo;
        public TextView tvModel;
        public TextView tvBrand;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivtipo = (ImageView) itemView.findViewById(R.id.iv_tipo);
            tvModel = (TextView) itemView.findViewById(R.id.tv_model);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }
        }
    }

}
