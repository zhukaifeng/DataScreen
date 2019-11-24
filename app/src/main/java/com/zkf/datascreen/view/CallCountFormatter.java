package com.zkf.datascreen.view;

import android.util.Log;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import static android.content.ContentValues.TAG;

/**
 * Created by guofh on 2018/1/15.
 */

public class CallCountFormatter extends ValueFormatter {
    private final BarLineChartBase<?> mChart;

    public CallCountFormatter(BarLineChartBase<?> chart) {
        this.mChart = chart;

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Log.i("zkf  ", "getFormattedValue-------------" + value);

        Log.i("zkf", "mChart.getVisibleXRange()-------------" + mChart.getVisibleXRange());
        if (value == 0.0 || value == 6.0) {

            return "";
        } else {
            return "";
        }
    }
}
