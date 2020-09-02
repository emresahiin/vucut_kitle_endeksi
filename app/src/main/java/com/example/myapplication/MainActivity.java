package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.myapplication.R.id.editText;

public class MainActivity extends AppCompatActivity{
    private EditText editText;
    private TextView boy_tv, durum_tv, ideal_tv, kilo_tv;
    private SeekBar seekBar;
    private RadioGroup radioGroup;
    private boolean erkekmi = true;
    private double boy = 0.0;
    private int kilo = 50;
    private RadioGroup.OnCheckedChangeListener radioGroupOlayIsleyicisi=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId==R.id.Erkek)
            erkekmi=true;
        else if (checkedId==R.id.Kadın)
            erkekmi=false;
guncelle();
        }
    };
    private SeekBar.OnSeekBarChangeListener seekBarOLayIsleyicisi=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            kilo=30+progress;
            guncelle();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private TextWatcher editTextOlayIsleyicisi= new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
          try{
              boy=Double.parseDouble(s.toString())/100.0;

          }catch (NumberFormatException e){
              boy=0.0;
          }
          guncelle();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        boy_tv = findViewById(R.id.boy_tv);
        durum_tv = findViewById(R.id.durum_tv);
        ideal_tv = findViewById(R.id.ideal_tv);
        kilo_tv = findViewById(R.id.kilo_tv);
        radioGroup = findViewById(R.id.radioGrup);
        seekBar = findViewById(R.id.seekBar);
        editText.addTextChangedListener(editTextOlayIsleyicisi);
        seekBar.setOnSeekBarChangeListener(seekBarOLayIsleyicisi);
        radioGroup.setOnCheckedChangeListener(radioGroupOlayIsleyicisi);

        guncelle();
    }


    private void guncelle() {
        kilo_tv.setText(String.valueOf(kilo)+" kg");
        boy_tv.setText(String.valueOf(boy)+" m");
        int ideal_kiloErkek= (int) (50+2.3*(boy*100*0.4-60));
        int ideal_kiloKadın= (int) (45.5+2.3*(boy*100*0.4-60));
        double vki=kilo/(boy*boy);
        if (erkekmi) {
            ideal_tv.setText(String.valueOf(ideal_kiloErkek));
            if (17.5 < vki && vki <= 20.7) {
                durum_tv.setBackgroundResource(R.color.zayif);
                durum_tv.setText(R.string.zayif);
            } else if (20.7 < vki && vki <= 26.4) {
                durum_tv.setText("Normal kilodasınız.");
            } else if (26.4 < vki && vki <= 27.8) {
                durum_tv.setText("Kilonuz olması gerekenden fazla.");
            } else if (27.8 < vki && vki <= 31.1) {
                durum_tv.setText("Fazla kilolu.");
            } else if (31.8 < vki && vki <= 34.9) {
                durum_tv.setText("Üzgünüm obezsiniz.");
            } else {
                durum_tv.setText("Doktora Görünmelisiniz.");
            }
        }

        else{
            ideal_tv.setText(String.valueOf(ideal_kiloKadın));
            if(17.5 < vki && vki <= 19.1)
                durum_tv.setText("Normal kilodasınız.");
            else if (19.1 < vki && vki <= 25.8){
                durum_tv.setText("Kilonuz olması gerekenden fazla.");
            }
            else if (25.8< vki && vki <= 27.3){
                durum_tv.setText("Fazla kilolu.");
            }
            else if (27.3< vki && vki <= 34.9){
                durum_tv.setText("Üzgünüm obezsiniz.");
            }
            else {
                durum_tv.setText("Doktora Görünmelisiniz.");
            }
    }
}}