package com.example.oscar_000.loancalculator;


public class Calculator {
    public double monto, plazo,tasa, pago,intertotal=0d, amortotal=0d, pagototal=0d;
    public Calculator(double newmonto, double newplazo,double newtasa){
        this.monto= newmonto;
        this.plazo=newplazo;
        this.tasa=newtasa;
     }

    public double[] Values(){
        pago = monto * ((tasa * Math.pow((1 + tasa), plazo)) / ((Math.pow((1 + tasa), plazo) - 1)));
        double intermedio=monto;
        for(int i=0; i<plazo;i++){
            double inter = intermedio*tasa;
            double amort= pago-inter;
            intermedio= intermedio - amort;
            intertotal= intertotal+inter;
            amortotal=amortotal+amort;
        }
        pagototal=amortotal+intertotal;
        pagototal=Math.round(pagototal*100)/100.0d;
        intertotal=Math.round(intertotal*100)/100.0d;
        pago=Math.round(pago*100)/100.0d;

        double[] valores = {intertotal,pagototal,pago};
        return valores;
    }
}
