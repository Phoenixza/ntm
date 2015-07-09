package com.example.welser.novatenmobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by welser on 02.07.2015.
 */
public class Settings extends Activity {
    public RadioButton five;
    public RadioButton fiveteen;
    public RadioGroup radioGroup;
    public RadioButton radioButton;
    public CheckBox rb_alarm;
    public CheckBox rb_cpu;
    public CheckBox rb_dalarms;
    public CheckBox rb_memory;
    public CheckBox rb_disk;
    public CheckBox rb_view;
    public static int radioValue;
    public Button save;
    public static String selection[] = new String[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        radioGroup=(RadioGroup)findViewById(R.id.radiog);
        five = (RadioButton) findViewById(R.id.five);
        fiveteen = (RadioButton) findViewById(R.id.fiveteen);
        rb_alarm = (CheckBox) findViewById(R.id.rb_alarm);
        rb_cpu = (CheckBox) findViewById(R.id.rb_cpu);
        rb_dalarms = (CheckBox) findViewById(R.id.rb_dalarms);
        rb_memory = (CheckBox) findViewById(R.id.rb_memory);
        rb_disk = (CheckBox) findViewById(R.id.rb_disk);
        rb_view = (CheckBox) findViewById(R.id.rd_view);

        rb_alarm.setChecked(false);



        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(clickHandler);
        fiveteen.setOnClickListener(clickHandler);
        five.setOnClickListener(clickHandler);
        rb_alarm.setOnClickListener(clickHandler);
        rb_cpu.setOnClickListener(clickHandler);
        rb_dalarms.setOnClickListener(clickHandler);
        rb_memory.setOnClickListener(clickHandler);
        rb_disk.setOnClickListener(clickHandler);
        rb_view.setOnClickListener(clickHandler);
    }

    View.OnClickListener clickHandler = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_save:
                    Intent intent = new Intent(Settings.this, MainActivity.class);
                    // Prüfen ob Radiabuttons aktiv sind:

                    if(five.isChecked()){
                        selection[0]= "true";
                    } else{ selection[0] = "false";}

                    if(fiveteen.isChecked()){
                        selection[1]= "true";
                    } else{ selection[1] = "false";}

                    if(rb_alarm.isChecked()){
                        selection[2]= "true";
                    } else{ selection[2] = "false";}

                    if(rb_cpu.isChecked()){
                        selection[3]= "true";
                    } else{ selection[3] = "false";}

                    if(rb_dalarms.isChecked()){
                        selection[4]= "true";
                    } else{ selection[4] = "false";}

                    if(rb_memory.isChecked()){
                        selection[5]= "true";
                    } else{ selection[5] = "false";}

                    if(rb_disk.isChecked()){
                        selection[6]= "true";
                    } else{ selection[6] = "false";}

                    if(rb_view.isChecked()){
                        selection[7]= "true";
                    } else{ selection[7] = "false";}

                    intent.putExtra("Auswahl",selection);
                    startActivity(intent);
                    break;
                case R.id.five:
                    int boola =radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(boola);
                    radioValue = boolraidosend(boola);
                    // Toast.makeText(Settings.this, radioButton.getText(), Toast.LENGTH_LONG).show();
                    break;
                case R.id.fiveteen:
                    int boolb =radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(boolb);
                    radioValue = boolraidosend(boolb);
                    //Toast.makeText(Settings.this, radioButton.getText(), Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };


    public static int boolraidosend(int bool){
        int wert = bool;
        return wert;
    }









    
}
