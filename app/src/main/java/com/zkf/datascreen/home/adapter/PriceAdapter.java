package com.zkf.datascreen.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zkf.datascreen.R;
import com.zkf.datascreen.model.PriceData;
import com.zkf.datascreen.model.TransactionData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{



	private List<PriceData> dataList = new ArrayList<>();


	public PriceAdapter(List<PriceData> dataList) {
		this.dataList = dataList;
	}

	public void setDataList(List<PriceData> dataList) {
		this.dataList = dataList;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_price, parent,false);//解决宽度不能铺满

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


		@BindView(R.id.tv_name_price)
        TextView tv_name_price;
		@BindView(R.id.tv_price_price)
        TextView tv_price_price;
		@BindView(R.id.tv_price_unit)
        TextView tv_price_unit;
		@BindView(R.id.tv_price_time)
        TextView tv_price_time;




		public CourseHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this,itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {

		}

		public void bindData(PriceData data){
			tv_name_price.setText(data.getName());
			tv_price_unit.setText(data.getUnit());
			tv_price_price.setText(data.getPrice());
			tv_price_time.setText(data.getDate());


		}
	}



}


