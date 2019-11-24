package com.zkf.datascreen.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zkf.datascreen.R;
import com.zkf.datascreen.model.LinkedData;
import com.zkf.datascreen.model.TransactionData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinkedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{



	private List<LinkedData> dataList = new ArrayList<>();


	public LinkedAdapter(List<LinkedData> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<LinkedData> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linked_data, parent,false);

		return new CourseHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

		((CourseHolder)holder).bindData(dataList.get(position));

	}

	@Override
	public int getItemCount() {
		return dataList.size();
	}



	class CourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		@BindView(R.id.tv_name_liked)
        TextView tv_name_liked;
		@BindView(R.id.tv_booth_number)
        TextView tv_booth_number;
		@BindView(R.id.tv_complaint)
        TextView tv_complaint;
		@BindView(R.id.iv_first)
		ImageView iv_first;
		@BindView(R.id.iv_second)
		ImageView iv_second;
		@BindView(R.id.iv_third)
		ImageView iv_third;
		@BindView(R.id.iv_fourth)
		ImageView iv_fourth;
		@BindView(R.id.iv_fifth)
		ImageView iv_fifth;



		public CourseHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this,itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {

		}

		public void bindData(LinkedData data){
			tv_name_liked.setText(data.getName());
			tv_booth_number.setText(data.getNumber());
			if (data.isComplaint()){
				tv_complaint.setText("有投诉");
			}else {
				tv_complaint.setText("无投诉");
			}
			switch (data.getScore()){
				case 0:
					iv_first.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					iv_second.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					iv_third.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					iv_fourth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					iv_fifth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));

					break;
				case 1:
					iv_first.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_second.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					iv_third.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					iv_fourth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					iv_fifth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					break;
				case 2:
					iv_first.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_second.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_third.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					iv_fourth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					iv_fifth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					break;
				case 3:
					iv_first.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_second.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_third.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_fourth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					iv_fifth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					break;
				case 4:
					iv_first.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_second.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_third.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_fourth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_fifth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star_un));
					break;
				case 5:
					iv_first.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_second.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_third.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_fourth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					iv_fifth.setBackground(itemView.getContext().getDrawable(R.mipmap.ic_star));
					break;
			}


		}
	}



}


