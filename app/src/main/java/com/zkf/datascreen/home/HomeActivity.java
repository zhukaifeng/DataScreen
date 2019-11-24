package com.zkf.datascreen.home;


import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.zkf.datascreen.R;
import com.zkf.datascreen.base.BaseActivity;
import com.zkf.datascreen.home.adapter.LinkedAdapter;
import com.zkf.datascreen.home.adapter.PriceAdapter;
import com.zkf.datascreen.home.adapter.TransactionAdapter;
import com.zkf.datascreen.model.LineChartBaseBean;
import com.zkf.datascreen.model.LinkedData;
import com.zkf.datascreen.model.MyBean;
import com.zkf.datascreen.model.PriceData;
import com.zkf.datascreen.model.TransactionData;
import com.zkf.datascreen.utils.DayAxisValueFormatter;
import com.zkf.datascreen.utils.LineAxisValueFormatter;
import com.zkf.datascreen.utils.LineChartManager;
import com.zkf.datascreen.utils.MyValueBottomFormatter;
import com.zkf.datascreen.utils.MyValueFormatter;
import com.zkf.datascreen.utils.NameAxisValueFormatter;
import com.zkf.datascreen.utils.PieChartUtil;
import com.zkf.datascreen.utils.WeekAxisValueFormatter;
import com.zkf.datascreen.view.BarLeftYValueFormatter;
import com.zkf.datascreen.view.CallCountFormatter;
import com.zkf.datascreen.view.CallCountValueFormatter;
import com.zkf.datascreen.view.XYMarkerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindBitmap;
import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.rv_transaction_data)
    RecyclerView rv_transaction_data;
    @BindView(R.id.rv_liked)
    RecyclerView rv_liked;
    @BindView(R.id.rv_price)
    RecyclerView rv_price;
    @BindView(R.id.chart1)
    LineChart chart;//客流量
    @BindView(R.id.chat_pie)
    PieChart chat_pie;
    @BindView(R.id.chart_top)
    BarChart chart_top;
    @BindView(R.id.chart_bottom)
    BarChart chart_bottom;
    @BindView(R.id.chart2)
    LineChart chart2;
    @BindView(R.id.iv_middle)
    ImageView iv_middle;



    private List<TransactionData> transactionDatas = new ArrayList<>();
    private TransactionAdapter transactionAdapter = new TransactionAdapter(transactionDatas);

    private List<LinkedData> linkedDatas = new ArrayList<>();
    private LinkedAdapter linkedAdapter = new LinkedAdapter(linkedDatas);

    private List<PriceData> priceDatas = new ArrayList<>();
    private PriceAdapter priceAdapter = new PriceAdapter(priceDatas);

    @Override
    public int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    public void init() {
        initView();
        initChart1();
        initChart2();

        initPieChat();
        initBarchat();
        initBarchatBottom();

        initTransactionData();
        initLinkedData();
        initPriceData();

        iv_middle.setBackground(getDrawable(R.mipmap.bg_middle));


    }

    private void initChart2() {
        List<LineChartBaseBean> list= new ArrayList<>();
        list.add(new LineChartBaseBean("星期一", 3.8f));
        list.add(new LineChartBaseBean("星期二", 3.8f));
        list.add(new LineChartBaseBean("星期三", 6.8f));
        list.add(new LineChartBaseBean("星期四", 7.8f));
        list.add(new LineChartBaseBean("星期五", 5.4f));
        list.add(new LineChartBaseBean("星期六", 0f));
        list.add(new LineChartBaseBean("星期日", 6f));
        list.add(new LineChartBaseBean("星期一", 3.8f));
        list.add(new LineChartBaseBean("星期二", 3.8f));
        list.add(new LineChartBaseBean("星期三", 6.8f));
        list.add(new LineChartBaseBean("星期四", 7.8f));
        list.add(new LineChartBaseBean("星期五", 5.4f));
        list.add(new LineChartBaseBean("星期六", 0f));
        list.add(new LineChartBaseBean("星期日", 6f));
        List<Float> m = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            float t = list.get(i).getValue();
            m.add(t);
        }
        List<Entry> entries = new ArrayList<>();
        Float mMin = m.get(0);
        Float mMax = m.get(0);
        for (int i = 0; i < m.size(); i++) {
            if (mMin > m.get(i)) {
                mMin = m.get(i);
            }
            if (mMax < m.get(i)) {
                mMax = m.get(i);
            }
            entries.add(new Entry(i, m.get(i)));
        }
        List<Entry> entries2 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            float mult = 10;
            float val = (float) (Math.random() * mult);
            entries2.add(new Entry(i, val));
        }
        WeekAxisValueFormatter formatter = new WeekAxisValueFormatter(list);
        chart2.setTouchEnabled(true);//可接触
        chart2.setDragEnabled(false);//可拖拽
        chart2.setScaleEnabled(false);//可缩放
        chart2.setDoubleTapToZoomEnabled(false);
        chart2.setScaleYEnabled(false);
        chart2.setDrawGridBackground(false);//画网格背景
        chart2.setDrawBorders(false);  //是否在折线图上添加边框
        chart2.setPinchZoom(false);//设置少量移动


        //图表注解
        chart2.getLegend().setEnabled(false);
        chart2.getDescription().setEnabled(false);

        //x轴坐标
        XAxis xAxis = chart2.getXAxis();
        xAxis.setTextColor(Color.BLACK);//x轴文字颜色
        xAxis.setTextSize(8f);

        xAxis.setEnabled(true);//显示x轴
        xAxis.setGridLineWidth(0f);
        xAxis.setLabelCount(14, false);
        xAxis.setDrawAxisLine(true);//是否绘制轴线
        xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
        xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
        xAxis.setGranularity(1);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setValueFormatter(formatter);
        LimitLine ll2 = new LimitLine(8f, "");
        ll2.setEnabled(false);

        //Y轴坐标
        YAxis yAxis = chart2.getAxisLeft();
        yAxis.setDrawGridLines(false);//取消网格线
        yAxis.setEnabled(true);//不显示Y轴
        yAxis.addLimitLine(ll2);
        yAxis.setLabelCount(7, true);
        yAxis.setTextColor(Color.WHITE);
        yAxis.setAxisMinimum(0f);

        chart2.getAxisRight().setEnabled(false);//不显示右侧
        LineDataSet lineDataSet = new LineDataSet(entries, "aaaa");
        LineDataSet lineDataSet2 = new LineDataSet(entries2, "bbbb");



        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);//设置折线图的显示模式，可以自行设置上面的值进行查看不同之处
        lineDataSet.setColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置线的颜色
        lineDataSet.setLineWidth(1.5f);//设置线的宽度
        lineDataSet.setCircleColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置圆圈的颜色
        //lineDataSet.setCircleColorHole(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置圆圈内部洞的颜色
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);//设置线数据依赖于左侧y轴
        lineDataSet.setDrawFilled(true);//设置不画数据覆盖的阴影层
        lineDataSet.setDrawValues(true);//不绘制线的数据
        lineDataSet.setValueTextSize(15f);//如果不绘制线的数据 这句代码也不用设置了
        lineDataSet.setFormLineWidth(10f);//只有lineDataSet.setForm(Legend.LegendForm.LINE);时才有作用 这里我们设置的是圆所以这句代码直接注释
        lineDataSet.setCircleRadius(4f);//设置每个折线点的大小
        lineDataSet.setFormSize(15.f);//设置当前这条线的图例的大小
        lineDataSet.setForm(Legend.LegendForm.LINE);//设置图例显示为线
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);

        lineDataSet2.setMode(LineDataSet.Mode.CUBIC_BEZIER);//设置折线图的显示模式，可以自行设置上面的值进行查看不同之处
        lineDataSet2.setColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));//设置线的颜色
        lineDataSet2.setLineWidth(1.5f);//设置线的宽度
        lineDataSet2.setCircleColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置圆圈的颜色
        //lineDataSet.setCircleColorHole(ContextCompat.getColor(this, android.R.color.holo_blue_light));//设置圆圈内部洞的颜色
        lineDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);//设置线数据依赖于左侧y轴
        lineDataSet2.setDrawFilled(true);//设置不画数据覆盖的阴影层
        lineDataSet2.setDrawValues(true);//不绘制线的数据
        lineDataSet2.setValueTextSize(15f);//如果不绘制线的数据 这句代码也不用设置了
        lineDataSet2.setFormLineWidth(10f);//只有lineDataSet.setForm(Legend.LegendForm.LINE);时才有作用 这里我们设置的是圆所以这句代码直接注释
        lineDataSet2.setCircleRadius(4f);//设置每个折线点的大小
        lineDataSet2.setFormSize(15.f);//设置当前这条线的图例的大小
        lineDataSet2.setForm(Legend.LegendForm.LINE);//设置图例显示为线
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setDrawValues(false);
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setDrawValues(false);


        LineData data = new LineData(lineDataSet,lineDataSet2);//创建图表数据源
        chart2.setData(data);//设置图表数据
    }

    private void initBarchatBottom() {

        chart_bottom.setDrawBarShadow(false);
        chart_bottom.setDrawValueAboveBar(true);

        chart_bottom.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart_bottom.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart_bottom.setPinchZoom(false);

        chart_bottom.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);

        ValueFormatter xAxisFormatter = new NameAxisValueFormatter(chart);

        XAxis xAxis = chart_bottom.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setLabelRotationAngle(60);


        ValueFormatter custom = new MyValueBottomFormatter("");

        YAxis leftAxis = chart_bottom.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setDrawGridLines(false);

        YAxis rightAxis = chart_bottom.getAxisRight();
        rightAxis.setEnabled(false);

        Legend l = chart_bottom.getLegend();
        l.setEnabled(false);
        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
        mv.setEnabled(false);


        setBottomData(10,50);

    }

    private void initBarchat() {

        chart_top.setDrawBarShadow(false);
        chart_top.setDrawValueAboveBar(true);

        chart_top.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart_top.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart_top.setPinchZoom(false);

        chart_top.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);

        ValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);

        XAxis xAxis = chart_top.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setValueFormatter(xAxisFormatter);
        xAxis.setLabelRotationAngle(60);

        ValueFormatter custom = new MyValueFormatter("");

        YAxis leftAxis = chart_top.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setDrawGridLines(false);

        YAxis rightAxis = chart_top.getAxisRight();
        rightAxis.setEnabled(false);

        Legend l = chart_top.getLegend();
        l.setEnabled(false);
        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
        mv.setEnabled(false);


        setData(10,50);

    }

    private void setBottomData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = (int) start; i < start + count; i++) {
            float val = (float) (Math.random() * (range + 1));

            if (Math.random() * 100 < 25) {
                Log.d("zkf I:" ," "+i +"  val:" + val);
            } else {
                Log.d("zkf I:" ," "+i +"  val:" + val);

                values.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;

        if (chart_bottom.getData() != null &&
                chart_bottom.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart_bottom.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart_bottom.getData().notifyDataChanged();
            chart_bottom.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "The year 2017");
            set1.setDrawIcons(false);

//            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            /*int startColor = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor = ContextCompat.getColor(this, android.R.color.holo_blue_bright);
            set1.setGradientColor(startColor, endColor);*/

            int startColor = ContextCompat.getColor(this, R.color.start_color);

            int endColor = ContextCompat.getColor(this, R.color.end_color);

            List<GradientColor> gradientColors = new ArrayList<>();
            gradientColors.add(new GradientColor(startColor, endColor));
            gradientColors.add(new GradientColor(startColor, endColor));
            gradientColors.add(new GradientColor(startColor, endColor));
            gradientColors.add(new GradientColor(startColor, endColor));
            gradientColors.add(new GradientColor(startColor, endColor));

            set1.setGradientColors(gradientColors);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            chart_bottom.setData(data);
        }
    }

    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = (int) start; i < start + count; i++) {
            float val = (float) (Math.random() * (range + 1));

            if (Math.random() * 100 < 25) {
                Log.d("zkf I:" ," "+i +"  val:" + val);
            } else {
                Log.d("zkf I:" ," "+i +"  val:" + val);

                values.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;

        if (chart_top.getData() != null &&
                chart_top.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart_top.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart_top.getData().notifyDataChanged();
            chart_top.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "The year 2017");

            set1.setDrawIcons(false);

//            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            /*int startColor = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor = ContextCompat.getColor(this, android.R.color.holo_blue_bright);
            set1.setGradientColor(startColor, endColor);*/

            int startColor = ContextCompat.getColor(this, R.color.start_color);

            int endColor = ContextCompat.getColor(this, R.color.end_color);

            List<GradientColor> gradientColors = new ArrayList<>();
            gradientColors.add(new GradientColor(startColor, endColor));
            gradientColors.add(new GradientColor(startColor, endColor));
            gradientColors.add(new GradientColor(startColor, endColor));
            gradientColors.add(new GradientColor(startColor, endColor));
            gradientColors.add(new GradientColor(startColor, endColor));

            set1.setGradientColors(gradientColors);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            chart_top.setData(data);
        }
    }




    private void initPieChat() {

        HashMap dataMap = new HashMap();
        dataMap.put("蔬菜", "15");
        dataMap.put("海鲜", "20");
        dataMap.put("肉", "30");
        dataMap.put("水产", "35");
        PieChartUtil.getPitChart().setPieChart(chat_pie, dataMap, "", true);

    }

    protected final String[] parties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    private void setPieData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    parties[i % parties.length],
                    getResources().getDrawable(R.mipmap.ic_star)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        chat_pie.setData(data);

        // undo all highlights
        chat_pie.highlightValues(null);

        chat_pie.invalidate();
    }


    private void initChart1() {
        LineChartManager lineChartManager1 = new LineChartManager(chart);
        //设置x轴的数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            xValues.add((float) i);
        }
        //设置y轴的数据()
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 0; j <= 10; j++) {
                yValue.add((float) (Math.random() * 80));
            }
            yValues.add(yValue);
        }
        //颜色集合
        List<Integer> colours = new ArrayList<>();
        colours.add(Color.GREEN);
        colours.add(Color.BLUE);
        colours.add(Color.RED);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("折线一");
        names.add("折线二");
        names.add("折线三");

        lineChartManager1.showLineChart(xValues, yValues, names, colours);
        lineChartManager1.setDescription("");

    }

    private void setLineData(int count, float range) {

    }


    private void initPriceData() {
        priceDatas.add(new PriceData("嫩姜", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("粉皮", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("酱黄瓜", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("萝卜", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("嫩姜", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("粉皮", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("酱黄瓜", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("萝卜", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("嫩姜", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("粉皮", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("酱黄瓜", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("萝卜", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("嫩姜", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("粉皮", "8.00", "元/斤", "2019-11-24"));
        priceDatas.add(new PriceData("酱黄瓜", "8.00", "元/斤", "2019-11-24"));
        priceAdapter.notifyDataSetChanged();
    }

    private void initLinkedData() {
        linkedDatas.add(new LinkedData("杨银花", "F43", 3, false));
        linkedDatas.add(new LinkedData("杨银花", "F43", 3, false));
        linkedDatas.add(new LinkedData("杨银花", "F43", 3, false));
        linkedAdapter.notifyDataSetChanged();
    }

    private void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeActivity.this,
                RecyclerView.VERTICAL, false);
        rv_transaction_data.setLayoutManager(linearLayoutManager);
        rv_transaction_data.setAdapter(transactionAdapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(HomeActivity.this,
                RecyclerView.VERTICAL, false);
        rv_liked.setLayoutManager(linearLayoutManager1);
        rv_liked.setAdapter(linkedAdapter);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(HomeActivity.this,
                RecyclerView.VERTICAL, false);
        rv_price.setLayoutManager(linearLayoutManager2);
        rv_price.setAdapter(priceAdapter);


    }

    private void initTransactionData() {
        transactionDatas.add(new TransactionData("其他商品", "0.708", "56.00", "39.65", "11-20 08:06:03"));
        transactionDatas.add(new TransactionData("其他商品", "0.708", "56.00", "39.65", "11-20 08:06:03"));
        transactionDatas.add(new TransactionData("其他商品", "0.708", "56.00", "39.65", "11-20 08:06:03"));
        transactionAdapter.notifyDataSetChanged();

    }
}
