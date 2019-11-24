package com.zkf.datascreen.view;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by guofh on 2018/1/15.
 */

public class CallCountValueFormatter extends ValueFormatter {
    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        String data = (String) entry.getData();
        Log.d("zkf data : " ,"  data"+ data);
        return data;
    }
}
