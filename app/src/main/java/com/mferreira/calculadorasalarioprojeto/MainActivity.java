package com.mferreira.calculadorasalarioprojeto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String SALARIO_VALUE = "SALARIO_VALUE";
    public final static String DESCONTO_VALUE = "DESCONTO_VALUE";
    public final static String DEPENDENTES_VALUE = "DEPENDENTES_VALUE";


    private EditText editSalario;
    private EditText editDependentes;
    private EditText editDesconto;
    private Button buttonCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editSalario = findViewById(R.id.editSalario);
        editDependentes = findViewById(R.id.editDependentes);
        editDesconto = findViewById(R.id.editDesconto);

        buttonCalcular = findViewById(R.id.buttonCalcular);
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ResultadoActivity.class);

                //receber valores
                String salarioValue = editSalario.getText().toString();
                String dependentesValue = editDependentes.getText().toString();
                String descontoValue = editDesconto.getText().toString();

                //resgatar valores digitados e enviar para próxima activity
                Double salario = !salarioValue.isEmpty() ? Double.parseDouble(salarioValue) : 0;
                Integer dependentes = !dependentesValue.isEmpty() ? Integer.parseInt(dependentesValue) : 0;
                Double desconto = !descontoValue.isEmpty() ? Double.parseDouble(descontoValue) : 0;

                //definir valores que serão enviados para a próxima activity
                intent.putExtra(SALARIO_VALUE, salario);
                intent.putExtra(DEPENDENTES_VALUE, dependentes);
                intent.putExtra(DESCONTO_VALUE, desconto);

                startActivity(intent);

            }
        });

    }

}
