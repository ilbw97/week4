package kr.ac.smu_201721572.week4;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class hw_3 extends AppCompatActivity {
    Chronometer chrono;
    RadioButton rdoCalendar, rdoTime;
    DatePicker datePicker;
    TimePicker timePicker;
    TextView tvYear, tvMonth, tvDay, tvHour, tvMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hw_3);
        setTitle("시간 예약(수정)");

        chrono = (Chronometer) findViewById(R.id.chronometer1);
        rdoCalendar = (RadioButton) findViewById(R.id.rdoCal);
        rdoTime = (RadioButton) findViewById(R.id.rdoTime);
        datePicker=(DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker1);
        tvYear = (TextView) findViewById(R.id.tvYear);
        tvDay = (TextView) findViewById(R.id.tvDate);
        tvHour = (TextView) findViewById(R.id.tvTime);
        tvMinute = (TextView) findViewById(R.id.tvMin);
        tvMonth = (TextView) findViewById(R.id.tvMon);

        timePicker.setVisibility(View.INVISIBLE);
        datePicker.setVisibility(View.INVISIBLE);
        rdoCalendar.setVisibility(View.INVISIBLE);
        rdoTime.setVisibility(View.INVISIBLE);

        // 예약 시작
        // radiobutton 출력.
        chrono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rdoCalendar.setVisibility(View.VISIBLE);
                rdoTime.setVisibility(View.VISIBLE);
                chrono.start();
                chrono.setBase(SystemClock.elapsedRealtime()); // 부팅 이후 시간 return. sleep 으로 소모된 시간 포함.
                chrono.setTextColor(Color.RED);
            }
        });

        // 시간 설정 (timepicker 출력, datepicker 미출력)
        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.INVISIBLE);
            }
        });
        // 날짜 설정 (datepicker 출력, timepicker 미출력)
        rdoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.VISIBLE);
            }
        });


        // 예약 완료(long click)
        tvYear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // chrono stop, textcolor change
                chrono.stop();
                chrono.setTextColor(Color.BLUE);
                // datepicker의 시간들 textview에 출력
                tvYear.setText(Integer.toString(datePicker.getYear()));
                tvMonth.setText(Integer.toString(datePicker.getMonth()+1));
                tvDay.setText(Integer.toString(datePicker.getDayOfMonth()));
                tvHour.setText(Integer.toString(timePicker.getCurrentHour()));
                tvMinute.setText(Integer.toString(timePicker.getCurrentMinute()));

                // radiobutton, datepicker, timepicker 미출력.
                timePicker.setVisibility(View.INVISIBLE);
                datePicker.setVisibility(View.INVISIBLE);
                rdoCalendar.setVisibility(View.INVISIBLE);
                rdoTime.setVisibility(View.INVISIBLE);
                return false;
            }
        });
    }
}
