package com.example.administrator.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */

public class MyListviewAdapter extends BaseAdapter {

	private List<ListContentDB> mListContentDBs;
	private Context mContext;

	private Integer index = -1;//记录ItemID

	public MyListviewAdapter(List<ListContentDB> listContentDBs, Context context) {
		mListContentDBs = listContentDBs;
		mContext = context;
	}

	@Override
	public int getCount() {
		return mListContentDBs.size();
	}

	@Override
	public Object getItem(int position) {
		return mListContentDBs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		try {
			ViewHolder viewHolder;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
				viewHolder.theImage = (ImageView) convertView.findViewById(R.id.the_Image);
				viewHolder.theImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
				viewHolder.theLocation = (TextView) convertView.findViewById(R.id.the_Location);
				viewHolder.thePrice = (TextView) convertView.findViewById(R.id.the_Price);
				viewHolder.theGrade = (TextView) convertView.findViewById(R.id.the_Grade);
				viewHolder.theBriefIntroduction  = (TextView) convertView.findViewById(R.id.the_BriefIntroduction);
				viewHolder.theRating = (RatingBar) convertView.findViewById(R.id.ratingBar);
				viewHolder.theRating.setTag(position);
				viewHolder.theRating.setOnRatingBarChangeListener(new theRatingBarChangeListener(viewHolder));
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
				viewHolder.theRating.setTag(position);
			}
			viewHolder.theImage.setImageResource(mListContentDBs.get(position).getImage());
			viewHolder.theLocation.setText(mListContentDBs.get(position).getLocation());
			viewHolder.thePrice.setText(mListContentDBs.get(position).getPrice());
			viewHolder.theGrade.setText(mListContentDBs.get(position).getGrade());
			viewHolder.theBriefIntroduction.setText(mListContentDBs.get(position).getBriefIntroduction());
			viewHolder.theRating.setRating(mListContentDBs.get(position).getRating());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}


	private class ViewHolder {
		private TextView theLocation, theBriefIntroduction, thePrice, theGrade;
		private ImageView theImage;// GridView中的输入
		private RatingBar theRating;
	}

	class theRatingBarChangeListener implements RatingBar.OnRatingBarChangeListener {
		private ViewHolder mViewHolder;

		private theRatingBarChangeListener(ViewHolder viewHolder) {
			mViewHolder = viewHolder;
		}

		@Override
		public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
			index = (Integer)ratingBar.getTag();
		}
	}
}
