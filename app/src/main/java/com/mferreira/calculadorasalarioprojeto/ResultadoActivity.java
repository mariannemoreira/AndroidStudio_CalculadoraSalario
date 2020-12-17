package com.mferreira.calculadorasalarioprojeto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private Button buttonVoltar;

    //resgatar os valores
    private TextView salarioTextView;
    private TextView inssTextView;
    private TextView irrfTextView;
    private TextView descontosTextView;
    private TextView salarioLiquidoTextView;
    private TextView totalDescontoTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //inicia variaveis
        //continuar
        salarioTextView = findViewById(R.id.textViewResultSalario);
        inssTextView = findViewById(R.id.inssTextView);
        irrfTextView = findViewById(R.id.irrfTextView);
        descontosTextView = findViewById(R.id.descontosTextView);
        salarioLiquidoTextView = findViewById(R.id.salarioLiquidoTextView);
        totalDescontoTextView = findViewById(R.id.totalDescontoTextView);

                //resgata valores enviados
        Double salario = getIntent().getDoubleExtra(MainActivity.SALARIO_VALUE, 0);
        Integer dependentes = getIntent().getIntExtra(MainActivity.DEPENDENTES_VALUE, 0);
        Double desconto = getIntent().getDoubleExtra(MainActivity.DESCONTO_VALUE, 0);

        Double descontoInss = descontoInss(salario);
        Double descontoIrrf = descontoIrrf(salario - descontoInss -(dependentes*189.59));
        Double salarioLiquido = salario - descontoInss - descontoIrrf - desconto;
        Double descontoPorcentagem = (1-(salarioLiquido/salario))* 100;

        salarioTextView.setText(String.format("%.2f", salario));
        inssTextView.setText(String.format("-%.2f", descontoInss));
        irrfTextView.setText(String.format("-%.2f", descontoIrrf));
        descontosTextView.setText(String.format("-%.2f", desconto));
        salarioLiquidoTextView.setText(String.format("%.2f", salarioLiquido));
        totalDescontoTextView.setText((String.format("%.2f", descontoPorcentagem)));

        buttonVoltar = findViewById(R.id.buttonVoltar);

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private double descontoInss(double salary){
        double descont = 0;

        if(salary <= 1045) {
            descont = salary*0.075;
        }else if (salary <=2089.6){
            descont = (salary*0.9)-15.67;
        }else if (salary <= 3134.4){
            descont = (salary*0.12) - 78.36;
        } else if (salary <=6101.06){
            descont = (salary*0.14) - 141.05;
        } else {
            descont = 713.10;
        }

        return descont;
    }
    private double descontoIrrf(double salaryBase){

        double desconto = 0;

        if(salaryBase <= 1903.98){
            desconto = 0;
        } else if (salaryBase <=2862.65){
            desconto = (salaryBase*0.075)-142.8;
        } else if (salaryBase <= 3751.05){
            desconto = (salaryBase*0.15) - 354.8;
        } else if (salaryBase <=4664){
            desconto = (salaryBase*0.225) - 636.13;
        } else {
            desconto = (salaryBase*0.275)-869.36;
        }

        return desconto;
    }

}