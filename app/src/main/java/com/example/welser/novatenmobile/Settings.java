package com.example.welser.novatenmobile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
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
    public static int radioValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        radioGroup=(RadioGroup)findViewById(R.id.radiog);
        five = (RadioButton) findViewById(R.id.five);
        fiveteen = (RadioButton) findViewById(R.id.fiveteen);

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bool =radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(bool);
                radioValue = boolraidosend(bool);
                Toast.makeText(Settings.this, radioButton.getText(), Toast.LENGTH_LONG).show();
            }
        });
        fiveteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int bool =radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(bool);
                radioValue = boolraidosend(bool);
                Toast.makeText(Settings.this, radioButton.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public static int boolraidosend(int bool){
        int wert = bool;
        return wert;
    }









    
}
