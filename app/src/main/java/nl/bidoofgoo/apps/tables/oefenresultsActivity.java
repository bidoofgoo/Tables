package nl.bidoofgoo.apps.tables;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

import nl.bidoofgoo.apps.tables.Misc.ButtonClick;
import nl.bidoofgoo.apps.tables.Misc.Function;

public class oefenresultsActivity extends AppCompatActivity {

    private PieChart mChart;
    private int goed;
    private int fout;

    public void onCreate(Bundle savedInstanceScore){
        super.onCreate(savedInstanceScore);
        setContentView(R.layout.activity_oefenenresults);

        goed = getIntent().getExtras().getInt("goed");
        fout = getIntent().getExtras().getInt("fout");

        mChart = (PieChart) findViewById(R.id.chart);
        mChart.setDescription(" description ");
        mChart.setTouchEnabled(false);
        mChart.setDrawSliceText(true);
        mChart.getLegend().setEnabled(false);
        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        setData();

        Button home = (Button) findViewById(R.id.home);
        ButtonClick.setButtonClickFunction(home, getResources(), new Function() {
            @Override
            public void whatToDo() {
                finish();
            }
        });
    }

    private void setData(){
        ArrayList<Entry> waarden = new ArrayList<Entry>();
        ArrayList<String> beschrijvingen = new ArrayList<String>();
        ArrayList<Integer> kleurtjes = new ArrayList<Integer>();

        waarden.add(new Entry(goed, 0));
        waarden.add(new Entry(fout, 1));
        beschrijvingen.add("Goed beantwoord");
        beschrijvingen.add("Fout beantwoord");
        kleurtjes.add(Color.rgb(42,171,140));
        kleurtjes.add(Color.rgb(27,140,140));

        PieDataSet dataSet = new PieDataSet(waarden, "Beantwoorde vragen");
        dataSet.setColors(kleurtjes);

        PieData data = new PieData(beschrijvingen, dataSet);
        data.setValueTextSize(15);
        data.setValueTextColor(Color.rgb(255,255,255));
        mChart.setData(data);
        mChart.invalidate();
    }


}
