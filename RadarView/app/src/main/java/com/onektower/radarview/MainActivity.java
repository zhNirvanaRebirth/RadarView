package com.onektower.radarview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    float[] percents = {0.56f, 0.83f, 0.18f, 0.69f, 0.57f, 0.635f, 0.479f};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadarView radarView = (RadarView) findViewById(R.id.radar);
        radarView.setDatas(getDatas());
    }

    private List<Data> getDatas() {
        List<Data> datas = new ArrayList<>();
        String[] classify = getResources().getStringArray(R.array.datas);
        for (int i = 0; i < classify.length; i++) {
            Data data = new Data();
            data.setClassify(classify[i]);
            data.setPercent(percents[i]);
            datas.add(data);
        }
        return datas;
    }
}
