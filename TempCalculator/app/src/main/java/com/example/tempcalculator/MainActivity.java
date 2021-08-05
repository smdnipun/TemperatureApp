package com.example.tempcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Button btnCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btn_Calculate);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTemp();
            }
        });
    }//end of onResume

    @SuppressLint("SetTextI18n")
    private void calculateTemp() {
        EditText et_temperature = findViewById(R.id.et_temp);
        String temp = et_temperature.getText().toString();

        if(temp.equals("")){ //check empty field
            Toast.makeText(this, "Please Enter a Temperature", Toast.LENGTH_LONG).show();
        }
        else{
            //validate the value
            Float TempNumber = 0.0f;
            Boolean error = Boolean.FALSE;

            Float answer = 0.0f;

            try {
                TempNumber = Float.parseFloat(temp);
            }
            catch (NumberFormatException e) {
                Toast.makeText(this,"Invalid entry",Toast.LENGTH_LONG).show();
                error = Boolean.TRUE;
            }

            if(!error) {
                RadioGroup rg = findViewById(R.id.rgTemp);
                int id = rg.getCheckedRadioButtonId();

                TextView tv = findViewById(R.id.tv_result);

                if(id == R.id.radioBtnCel){
                    // convert it into Fahrenheit and display the answer
                    answer = convertCelciusToFahrenheit(TempNumber);
                }
                else if(id == R.id.radioBtnFah){
                    // convert it into Celcius and display the answer
                    answer = convertFahrenheitToCelcius(TempNumber);
                }
                else {
                    Toast.makeText(this, "Select Celsius or Fahrenheit", Toast.LENGTH_SHORT).show();
                }
                tv.setText(answer + "");
            }
        }
    }

    protected float convertCelciusToFahrenheit(Float value) {
        Float ans = (value - 32) * 5/9;
        return ans;
    }

    protected float convertFahrenheitToCelcius(Float value) {
        Float ans = (value * 9/5) + 32;
        return ans;
    }
}