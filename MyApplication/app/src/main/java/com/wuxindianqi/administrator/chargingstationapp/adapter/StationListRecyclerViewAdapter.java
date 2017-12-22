package com.wuxindianqi.administrator.chargingstationapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.bean.RecyclerViewItem.StationViewItem;
import com.wuxindianqi.administrator.chargingstationapp.utils.MapUtils.OpenNavigation;

import java.util.ArrayList;

/**
 * Created by checheng on 2017/12/18.
 */

public class StationListRecyclerViewAdapter extends RecyclerView.Adapter<StationListRecyclerViewAdapter.ViewHoler> {

    private View mView;
    private ArrayList<StationViewItem> mList = new ArrayList<StationViewItem>();
    private Context context;


    //针对每个按键具备不同功能时写法
    private OnMyItemClickListener itemClickListener;

    private interface OnMyItemClickListener {
        void OnitemClick(View view, int postion);
    }

    public StationListRecyclerViewAdapter(Context context,ArrayList<StationViewItem> mList) {
        this.mList = mList;
        this.context = context;
    }

    public void setOnMyItemClickListener(OnMyItemClickListener onMyItemClickListener) {
        itemClickListener = onMyItemClickListener;
    }

    @Override
    public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = View.inflate(parent.getContext(), R.layout.item_view_chargestation, null);
        final ViewHoler viewHolder = new ViewHoler(mView);
        viewHolder.ACslow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"暂不支持充电桩预约",Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.ACfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"暂不支持充电桩预约",Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.DCfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"暂不支持充电桩预约",Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewHolder.navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("导航点击信息","点击");
                int pos = viewHolder.getLayoutPosition();
                OpenNavigation.isAvilibaleNavi(context,mList.get(pos).getLatitude(),mList.get(pos).getLongtitude());

            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHoler holder, int position) {
        final StationViewItem viewItem = mList.get(position);
        holder.stationname.setText(viewItem.getLocationName());
        holder.distance.setText(viewItem.getDistance());
        holder.address.setText(viewItem.getLocationAddress());
        holder.ACslow.setText(viewItem.getACslowNum());
        holder.ACfast.setText(viewItem.getACfastNum());
        holder.DCfast.setText(viewItem.getDCfastNum());

        if (itemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    itemClickListener.OnitemClick(holder.itemView,pos);
                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder {
        View view;
        TextView stationname, distance, address;
        Button ACslow, ACfast, DCfast, navigate, collection;

        public ViewHoler(View itemView) {
            super(itemView);
            view = itemView;
            stationname = (TextView) itemView.findViewById(R.id.loation_name);
            distance = (TextView) itemView.findViewById(R.id.distance_for_loation);
            address = (TextView) itemView.findViewById(R.id.address_for_location);
            ACslow = (Button)itemView.findViewById(R.id.AC_slow_button);
            ACfast = (Button)itemView.findViewById(R.id.AC_fast_button);
            DCfast  = (Button)itemView.findViewById(R.id.DC_fast_button);
            navigate = (Button)itemView.findViewById(R.id.navigate_button);
            collection = (Button)itemView.findViewById(R.id.collection_button);
        }
    }

    protected void setNaviClick(Context context,double lan ,double lng){

    }
}
