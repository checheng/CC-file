package com.wuxindianqi.administrator.chargingstationapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuxindianqi.administrator.chargingstationapp.R;
import com.wuxindianqi.administrator.chargingstationapp.bean.ViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHoler> {

	List<ViewItem> mList = new ArrayList<ViewItem>();
	private View mView;

	public MyRecyclerViewAdapter(List<ViewItem> list) {
		mList = list;
	}

	@Override
	public ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

		mView = View.inflate(parent.getContext()  ,R.layout.item_view,null);
		final ViewHoler viewHoler  = new ViewHoler(mView);

//		//item设置监听
//		viewHoler.view.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				int layoutPosition = viewHoler.getLayoutPosition();
//				//土司
//				Toast.makeText(mView.getContext(),"This is item :"+layoutPosition,Toast.LENGTH_SHORT).show();
//				notifyDataSetChanged();
//			}
//		});

	/*	//textview设置监听
		viewHoler.text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//获取到position
				int layoutPosition = viewHoler.getLayoutPosition();
				//土司
				Toast.makeText(mView.getContext(),"This is textView :"+layoutPosition,Toast.LENGTH_SHORT).show();
			}
		});*/

		return viewHoler;
	}

	public interface OnItemClickLitener {
		void onItemClick(View view, int position);
	}

	private  OnItemClickLitener mOnItemClickListener;

	public void setOnItemClickLitener(OnItemClickLitener onItemClickListener)
	{
		this.mOnItemClickListener = onItemClickListener;
	}

	@Override
	public void onBindViewHolder(final ViewHoler holder, int position) {
		ViewItem item = mList.get(position);
		//设置内容
		holder.text.setText(item.getTitle());

		if (mOnItemClickListener !=null){
			holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int pos = holder.getLayoutPosition();
					mOnItemClickListener.onItemClick(holder.itemView, pos);

				}
			});
		}

	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	class ViewHoler extends RecyclerView.ViewHolder {
		TextView text;
		View view;

		public ViewHoler(View itemView) {
			super(itemView);
			view = itemView;
			text = (TextView) itemView.findViewById(R.id.item_text);
		}
	}


}
