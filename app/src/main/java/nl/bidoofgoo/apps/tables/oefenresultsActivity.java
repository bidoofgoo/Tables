package nl.bidoofgoo.apps.tables;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.Map;

public class oefenresultsActivity extends AppCompatActivity {

    private PieChart mChart;

    public void onCreate(Bundle savedInstanceScore){
        super.onCreate(savedInstanceScore);
        setContentView(R.layout.activity_oefenenresults);

        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setDescription(" description ");
        mChart.setTouchEnabled(false);
        mChart.setDrawSliceText(true);
        mChart.getLegend().setEnabled(false);
        mChart.setTransparentCircleColor(Color.rgb(130, 130, 130));
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        }


}
