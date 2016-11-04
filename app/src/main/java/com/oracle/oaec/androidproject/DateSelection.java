package com.oracle.oaec.androidproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class DateSelection extends Activity {
    private DatePicker dp = null;//日期选择器

    private TextView data_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);

        data_ok = (TextView) findViewById(R.id.data_ok);
        data_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        this.dp = (DatePicker) super.findViewById(R.id.dp1);

        //更新日期
        this.dp.updateDate(1990, 8, 16);
    }
}
