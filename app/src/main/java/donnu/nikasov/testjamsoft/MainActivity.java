package donnu.nikasov.testjamsoft;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

//По осі X має відображатись ціна, По осі Y має відображатись швидкість (line chart) і вага (bar chart).
//(Підказка: Треба буде використати CombinedChart)
//Завантаження даних про машини не має бути автоматичним, треба захардкодити.
//Зліва зверху має бути інфо блок який показує інформацію про останню вибрану точку на чарті у такій послідовності:

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener{

    private List<Automobile> automobileList;
    private CombinedChart mChart;
    Typeface font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        automobileList = Automobile.getAutoList();

        mChart = (CombinedChart) findViewById(R.id.combinedChart);

        mChart.getDescription().setText("Цена/Скорость,Вес");
        //mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);

        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE});

        //панель с легендой
        Legend legend = mChart.getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        //правая ось
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(100f);

        //левая ось
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(100f);

        //ось X
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setAxisMinimum(36000f);
        xAxis.setAxisMaximum(120000f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);

        CombinedData combinedData = new CombinedData();

        combinedData.setData(createLineData(automobileList));
        combinedData.setData(createBarData(automobileList));

        font = Typeface.createFromAsset(getAssets(), "fonts/11773.ttf");

//        combinedData.setValueTypeface(font);
        mChart.setTouchEnabled(true);

        mChart.setOnChartValueSelectedListener(this);
        mChart.animateY(1500, Easing.EasingOption.EaseInOutCubic);
        InfoMarkerView mv = new InfoMarkerView(this, R.layout.marker);
        mv.setChartView(mChart);
        mChart.setMarker(mv);

        mChart.setData(combinedData);
        mChart.invalidate();
    }

    private LineData createLineData(List<Automobile> automobileList) {

        int itemCount = automobileList.size();

        ArrayList<Entry> entries = new ArrayList<>();

        for (int index = 0; index < itemCount; index++) {
//            каждый шаг
//            entries.add(new Entry(index + 0.5f, (float)automobileList.get(index).getSpeed()));
            entries.add(new Entry((float)automobileList.get(index).getPrice(), (float)automobileList.get(index).getSpeed()));

        }

        LineDataSet set = new LineDataSet(entries, "Скорость");

        set.setColor(Color.rgb(69, 209, 110));
        set.setCircleColor(Color.rgb(69, 209, 110));
        set.setFillColor(Color.rgb(69, 209, 110));
        set.setLineWidth(3f);
        set.setCircleRadius(6f);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(11f);
        set.setValueTextColor(Color.BLACK);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        LineData lineData = new LineData();

        lineData.addDataSet(set);

        return lineData;
    }

    private BarData createBarData(List<Automobile> automobileList) {

        int itemCount = automobileList.size();

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int index = 0; index < itemCount; index++) {
//            каждый шаг
//            entries.add(new BarEntry(index + 0.5f, (float)automobileList.get(index).getWeight()));
            entries.add(new BarEntry((float)automobileList.get(index).getPrice(), (float)automobileList.get(index).getWeight()));
        }

        BarDataSet set = new BarDataSet(entries, "Вес");
        set.setColor(Color.rgb(173, 255, 197));
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(10f);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        float barWidth = 2800f;

        BarData barData = new BarData(set);
        barData.setBarWidth(barWidth);

        return barData;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
//        Если надо освобождать фокус когда отпускаешь
//        mChart.highlightValue(null);
    }

    @Override
    public void onNothingSelected() {

    }
}
