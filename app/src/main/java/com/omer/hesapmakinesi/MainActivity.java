package com.omer.hesapmakinesi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView bar;
    ArrayList<String> gosSayi = new ArrayList<>();
    ArrayList<String> duzgunList = new ArrayList<>();
    Double sonuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.Bar);
        bar.setText("");

    }
    public void obut0(View view){
        ekle("0");
    }
    public void obut1(View view){
        ekle("1");
    }
    public void obut2(View view){
        ekle("2");
    }
    public void obut3(View view){
        ekle("3");
    }
    public void obut4(View view){
        ekle("4");
    }
    public void obut5(View view){
        ekle("5");
    }
    public void obut6(View view){
        ekle("6");
    }
    public void obut7(View view){
        ekle("7");
    }
    public void obut8(View view){
        ekle("8");
    }
    public void obut9(View view){
        ekle("9");
    }
    public void obutgeri(View view){
        if(!gosSayi.isEmpty()){
            gosSayi.remove(gosSayi.size()-1);
            String deger = "";
            for(int i = 0;i<gosSayi.size();i++){
                deger+=gosSayi.get(i);
            }
            bar.setText(deger);
        }

    }
    public void obuttop(View view){
        ekle("+");
    }
    public void obutcik(View view){
        ekle("-");
    }
    public void obutcarp(View view){
        ekle("X");
    }
    public void obutbol(View view){
        ekle("/");
    }
    public void obutnokta(View view){
        ekle(".");
    }
    public void obuttemiz(View view){
        bar.setText("");
        sonuc = 0.0;
        gosSayi.clear();
        duzgunList.clear();

    }
    public void obuthafiza(View view){
        gosSayi.clear();
        duzgunList.clear();
        gosSayi.add(Double.toString(sonuc));
        Toast.makeText(getApplicationContext(),sonuc+" Hafızaya Alındı",Toast.LENGTH_LONG).show();
        sonuc = 0.0;
    }

    public void obutson(View view){
        if(gosSayi.get(gosSayi.size()-1).equals("+") || gosSayi.get(gosSayi.size()-1).equals("-") || gosSayi.get(gosSayi.size()-1).equals("/") || gosSayi.get(gosSayi.size()-1).equals("X")){
            Toast.makeText(getApplicationContext(),"Hatalı İşlem",Toast.LENGTH_LONG).show();
        }
        else if(gosSayi.get(0).equals("/") || gosSayi.get(0).equals("X")){
            Toast.makeText(getApplicationContext(),"Hatalı İşlem",Toast.LENGTH_LONG).show();
        }
        else{
            boolean kontrol3 = noktaKontrol(gosSayi);
            if (kontrol3){
                String a = "";
                boolean kontrol = islemKontrol(gosSayi);
                if(kontrol){
                    for(int i = 0 ; i<gosSayi.size();i++){
                        if(gosSayi.get(i).equals("+") || gosSayi.get(i).equals("-") || gosSayi.get(i).equals("X") || gosSayi.get(i).equals("/")){
                            duzgunList.add(a);
                            duzgunList.add(gosSayi.get(i));
                            a="";
                        }
                        else{
                            a+=gosSayi.get(i);
                        }
                    }
                    duzgunList.add(a);
                    boslukSil(duzgunList);
                    sonuc = 0.0;
                    for(int i = 0; i<duzgunList.size();i++){
                        if(duzgunList.get(i).equals("X") || duzgunList.get(i).equals("/")){
                            double temp = 0.0;
                            if (duzgunList.get(i).equals("X")){
                                temp += Double.parseDouble(duzgunList.get(i-1))*Double.parseDouble(duzgunList.get(i+1));
                                duzgunList.remove(i+1);
                                duzgunList.remove(i);
                                duzgunList.set(i-1,Double.toString(temp));
                                i =0;
                            }
                            else{
                                temp += Double.parseDouble(duzgunList.get(i-1))/Double.parseDouble(duzgunList.get(i+1));
                                duzgunList.remove(i+1);
                                duzgunList.remove(i);
                                duzgunList.set(i-1,Double.toString(temp));
                                i =0;
                            }
                        }
                    }

                    eksiKontrol(duzgunList);
                    artiCikar(duzgunList);
                    for(int i = 0; i<duzgunList.size();i++){
                        if(duzgunList.get(i).equals("-")){
                            if (duzgunList.get(i).equals("-")){
                                Double temp = Double.parseDouble(duzgunList.get(i+1));
                                temp = -temp;
                                duzgunList.set(i+1,Double.toString(temp));
                                duzgunList.remove(i);

                            }
                        }
                    }
                    for(int i = 0 ; i<duzgunList.size();i++){
                        sonuc += Double.parseDouble(duzgunList.get(i));
                    }
                    bar.setText(""+sonuc);
                }
                else{
                    Toast.makeText(getApplicationContext(),"İşlem Hatası",Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(),"Nokta Hatası",Toast.LENGTH_LONG).show();
            }


        }

    }
    public void ekle(String a){
        if(gosSayi.size() == 17){
            Toast.makeText(getApplicationContext(),"Maximum 17 karakter",Toast.LENGTH_LONG).show();
        }
        else {
            gosSayi.add(a);
            bar.setText(bar.getText().toString()+a);
        }


    }
    public boolean islemKontrol(ArrayList<String> gosSayi){
        for(int i = 0 ; i<gosSayi.size();i++){
            if(gosSayi.get(i).equals("/") || gosSayi.get(i).equals("X")){
                if(gosSayi.get(i+1).equals("/") || gosSayi.get(i+1).equals("X")){
                    return false;
                }
            }
        }
        return true;
    }
    public void boslukSil(ArrayList<String> sayiList){
        for(int i = 0 ; i<sayiList.size();i++){
            if(sayiList.get(i).equals("")){
                sayiList.remove(i);
            }
        }
    }
    public void artiCikar(ArrayList duzgunList){
        for(int i = 0 ; i<duzgunList.size();i++){
            if(duzgunList.get(i).equals("+")){
                duzgunList.remove(i);
                i = 0;
            }
        }
    }
    public void eksiKontrol(ArrayList duzgunList){
        for(int i = 0 ; i<duzgunList.size();i++){
            if(duzgunList.get(i).equals("-")){
                if(duzgunList.get(i+1).equals("-")){
                    duzgunList.set(i,"+");
                    duzgunList.remove(i+1);

                }
            }
        }
    }
    public boolean noktaKontrol(ArrayList list){
        for(int i = 0 ; i<list.size();i++){
            if (list.get(i).equals(".")){
                if(list.get(i+1).equals(".")){
                    return  false;
                }
            }

        }
        return true;
    }

}
