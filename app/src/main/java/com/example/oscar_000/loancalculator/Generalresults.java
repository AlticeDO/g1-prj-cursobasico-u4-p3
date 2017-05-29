package com.example.oscar_000.loancalculator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Generalresults extends AppCompatActivity {

    TabHost TbH;
    TextView montotexto,tasatexto,plazotexto,pagostexto,pagototal,intertotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalresults);

        TbH = (TabHost) findViewById(R.id.TabHost);
        montotexto=(TextView)findViewById(R.id.textView9);
        tasatexto=(TextView)findViewById(R.id.textView10);
        plazotexto=(TextView)findViewById(R.id.textView11);
        pagostexto=(TextView)findViewById(R.id.textView14);
        pagototal=(TextView)findViewById(R.id.textView18);
        intertotal=(TextView)findViewById(R.id.textView16);

        TbH.setup();

        TabHost.TabSpec tab1 = TbH.newTabSpec("Resultados");
        TabHost.TabSpec tab2 = TbH.newTabSpec("Calendario");

        tab1.setIndicator("Resultados");
        tab1.setContent(R.id.Resultados);

        tab2.setIndicator("Calendario");
        tab2.setContent(R.id.Calendario);


        TbH.addTab(tab1);
        TbH.addTab(tab2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            String monto = (String) bundle.get("MONTO");
            String tasa = (String) bundle.get("TASA");
            String plazo = (String) bundle.get("PLAZO");
            double pagototales=(double) bundle.get("PAGOTOTAL");
            double intertotales=(double) bundle.get("INTERTOTALES");
            double total = (double) bundle.get("TOTAL");
            double monto1 = (double) bundle.get("MONTOS");
            double tasa1 = (double) bundle.get("TASAS");
            double plazo1 = (double) bundle.get("PLAZOS");


            String total1=String.valueOf(total);
            String intertotales1=String.valueOf(intertotales);
            String pagototales1 =String.valueOf(pagototales);
            tasa="%"+tasa;
            monto="$"+monto;
            total1="$"+total1;
            pagototales1="$"+pagototales1;
            intertotales1="$"+intertotales1;
            plazo=plazo+" Meses";
            montotexto.setText(monto);
            tasatexto.setText(tasa);
            plazotexto.setText(plazo);
            pagostexto.setText(total1);
            pagototal.setText(pagototales1);
            intertotal.setText(intertotales1);

            TableLayout tabla = (TableLayout)findViewById(R.id.MyTable);
            TextView textmes = new TextView(this);
            TextView textcuota = new TextView(this);
            TextView textintereses = new TextView(this);
            TextView textamortizacion = new TextView(this);
            TextView textsaldo = new TextView(this);

            TableRow tR2 = new TableRow(this);
            tR2.setPadding(5,5,5,5);

            textmes.setText(R.string.Meses);
            textmes.setGravity(Gravity.CENTER);
            textmes.setBackgroundColor(Color.GRAY);

            textcuota.setText(R.string.Cuota);
            textcuota.setGravity(Gravity.CENTER);
            textcuota.setBackgroundColor(Color.GRAY);


            textintereses.setText(R.string.Intereses);
            textintereses.setGravity(Gravity.CENTER);
            textintereses.setBackgroundColor(Color.GRAY);


            textamortizacion.setText(R.string.Amortizacion);
            textamortizacion.setGravity(Gravity.CENTER);
            textamortizacion.setBackgroundColor(Color.GRAY);


            textsaldo.setText(R.string.Saldo);
            textsaldo.setGravity(Gravity.CENTER);
            textsaldo.setBackgroundColor(Color.GRAY);

            tR2.addView(textmes);
            tR2.addView(textcuota);
            tR2.addView(textintereses);
            tR2.addView(textamortizacion);
            tR2.addView(textsaldo);

            tabla.addView(tR2);

            double intermedio = monto1;
            String[] Intereses = new String[(int)plazo1];
            String[] Amortizacion= new String[(int)plazo1];
            String[] Saldos= new String[(int)plazo1];
            double inter, amort;

            for(int i=0; i<plazo1;i++){
                inter = intermedio*tasa1;
                Intereses[i]=String.valueOf(Math.round(inter*100)/100.0d);
                amort= total-inter;
                Amortizacion[i]=String.valueOf(Math.round(amort*100)/100.0d);
                intermedio= intermedio - amort;
                Saldos[i]=String.valueOf(Math.round(intermedio*100)/100.0d);
             }

            String cuotastring = String.valueOf(Math.round(total*100)/100.0d);

            for(int b=0;b<plazo1;b++){
                 TableRow tR1 = new TableRow(this);
                 tR1.setPadding(5,5,5,5);

                TextView mestable = new TextView(this);
                TextView cuotatable = new TextView(this);
                TextView interestable = new TextView(this);
                TextView amortable = new TextView(this);
                TextView saldotable = new TextView(this);

                 mestable.setText(String.valueOf(b+1));
                 mestable.setGravity(Gravity.CENTER);

                 cuotatable.setText(cuotastring);
                 cuotatable.setGravity(Gravity.CENTER);

                 interestable.setText(Intereses[b]);
                 interestable.setGravity(Gravity.CENTER);

                 amortable.setText(Amortizacion[b]);
                 amortable.setGravity(Gravity.CENTER);

                 saldotable.setText(Saldos[b]);
                 saldotable.setGravity(Gravity.CENTER);

                if(b==(plazo1-1)){
                    saldotable.setText(R.string.Setcero);
                }

                tR1.addView(mestable);
                tR1.addView(cuotatable);
                tR1.addView(interestable);
                tR1.addView(amortable);
                tR1.addView(saldotable);

                tabla.addView(tR1);

            }
        }
    }
    }

