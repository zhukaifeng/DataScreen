package com.zkf.datascreen.view;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

/**
 * Created by guofh on 2018/1/25.
 */

public class BarLeftYValueFormatter extends ValueFormatter {
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Log.d("zkf","  value:" +(int)value+"");
        return (int)value+"";
    }


}
