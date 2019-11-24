package com.zkf.datascreen.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.zkf.datascreen.R;
import com.zkf.datascreen.model.TransactionData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{



	private List<TransactionData> dataList = new ArrayList<>();


	public TransactionAdapter(List<TransactionData> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<TransactionData> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction_data, parent,false);//解决宽度不能铺满

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


		@BindView(R.id.tv_name)
        TextView tv_name;
		@BindView(R.id.tv_price)
        TextView tv_price;
		@BindView(R.id.tv_kg)
        TextView tv_kg;
		@BindView(R.id.tv_amount)
        TextView tv_amount;
		@BindView(R.id.tv_transaction_time)
        TextView tv_transaction_time;



		public CourseHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this,itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {

		}

		public void bindData(TransactionData data){
			tv_name.setText(data.getName());
			tv_price.setText(data.getPrice_unit());
			tv_kg.setText(data.getKg());
			tv_amount.setText(data.getPrice());
			tv_transaction_time.setText(data.getTime());


		}
	}



}


