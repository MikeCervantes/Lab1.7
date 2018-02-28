package com.example.kid_d_000.lab13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity{
    TextView textView;
    EditText texto;
    Button boton;
    Random r;
    int cont=0;
    double num = (double) 0.9;
    double resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView);
        texto = findViewById(R.id.editText);
        boton=findViewById(R.id.button);
        r=new Random();

        resultado = r.nextFloat() * (3.0 - 1.0) + 1.0;
        texto.setText(String.format("%.1f", resultado));

        final Thread t=new Thread(){
            @Override
            public void run(){
                while(!isInterrupted()){
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (cont > 20) {
                                    cont = 0;
                                    num = (float) 0.9;
                                }
                                cont++;
                                num += 0.1;
                                textView.setText(String.format("%.1f", num));
                            }
                        });
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                double num1=Double.parseDouble(texto.getText().toString());
                double num2=Double.parseDouble(textView.getText().toString());
                if(num1==num2) {
                    Toast.makeText(MainActivity.this, "¡Bien Hecho!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Fallaste", Toast.LENGTH_SHORT).show();
                    resultado = r.nextFloat() * (3.0 - 1.0) + 1.0;
                    texto.setText(String.format("%.1f", resultado));
                }
            }
        });
    }
}
