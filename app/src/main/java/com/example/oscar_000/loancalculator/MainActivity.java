package com.example.oscar_000.loancalculator;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner2,spinner3;
    TabHost Tab;
    EditText textmonto, texttasa, textplazo;
    Button button1;
    Calculator cal;
    public static int a=0,b=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tab = (TabHost)findViewById(R.id.TabHost);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        spinner3=(Spinner)findViewById(R.id.spinner3);
        textmonto=(EditText)findViewById(R.id.editText1);
        texttasa=(EditText)findViewById(R.id.editText2);
        textplazo=(EditText)findViewById(R.id.editText3);
        button1=(Button)findViewById(R.id.button1);

        this.spinner2.setOnItemSelectedListener(this);
        this.spinner3.setOnItemSelectedListener(this);

        List list2 = new ArrayList();
        list2.add("Anual");
        list2.add("Mensual");
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(arrayAdapter2);

        List list3 = new ArrayList();
        list3.add("Meses");
        list3.add("Años");
        list3.add("Dias");
        ArrayAdapter arrayAdapter3 = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list3);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(arrayAdapter3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tasa,plazo, monto;
                String plazo1=textplazo.getText().toString();
                String monto1=textmonto.getText().toString();
                String tasa1=texttasa.getText().toString();

                if(plazo1.isEmpty() || monto1.isEmpty() || tasa1.isEmpty()){
                    Toast.makeText(MainActivity.this, "No debe dejar ningún campo vacío", Toast.LENGTH_SHORT).show();
                }
                else if(Float.parseFloat(texttasa.getText().toString())>100){
                    texttasa.setError("La tasa no debe ser mayor al 100%");
                }
                else {
                    texttasa.setError(null);
                    monto = Double.parseDouble(monto1);
                    tasa=Double.parseDouble(tasa1)/100;
                    plazo=Double.parseDouble(plazo1);
                    if(a==1){
                        tasa=tasa/11.8356164;
                    }

                    if(b==2){
                        plazo=plazo*12;
                    }else if(b==3){
                        plazo=plazo/30;
                    }

                    cal=new Calculator(monto,plazo,tasa);
                    double[] datos=cal.Values();
                    double intertotal= datos[0];
                    double pagototal=datos[1];
                    double pago=datos[2];

                    Intent intent = new Intent(MainActivity.this,Generalresults.class);
                    intent.putExtra("MONTO",monto1);
                    intent.putExtra("TASA",tasa1);
                    intent.putExtra("PLAZO",plazo1);
                    intent.putExtra("INTERTOTALES",intertotal);
                    intent.putExtra("PAGOTOTAL",pagototal);
                    intent.putExtra("TOTAL",pago);
                    intent.putExtra("MONTOS",monto);
                    intent.putExtra("TASAS",tasa);
                    intent.putExtra("PLAZOS",plazo);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinner2:
                if(parent.getItemAtPosition(position).toString().equals("Anual")){
                    a=1;
                }else a=0;
                break;
            case R.id.spinner3:
                if(parent.getItemAtPosition(position).toString().equals("Meses")){
                    b=1;
                }else if(parent.getItemAtPosition(position).toString().equals("Años")){
                    b=2;
                }else b=3;
            }
        }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}




