package com.example.calculatricesimple;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // variables
    private TextView textoResultado;
    private StringBuilder entradaActual;
    private double operando1, operando2;
    private Operador operadorActual;
    private boolean nuevoCalculo;

    // operateurs
    private enum Operador {
        SUMAR, RESTAR, MULTIPLICAR, DIVIDIR
    }

    // Methode apel craacion de l'activite
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues et variables
        textoResultado = findViewById(R.id.textoResultado);
        entradaActual = new StringBuilder();

        // Configuration des boutons
        configurarBotones();
    }

    // Configuration des boutons
    private void configurarBotones() {

        int[] idsBotonesNumeros = {
                R.id.boton0, R.id.boton1, R.id.boton2, R.id.boton3, R.id.boton4,
                R.id.boton5, R.id.boton6, R.id.boton7, R.id.boton8, R.id.boton9
        };

        // listener pour les boutons
        View.OnClickListener listenerNumero = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nuevoCalculo) {
                    entradaActual.setLength(0);
                    nuevoCalculo = false;
                }
                Button boton = (Button) v;
                entradaActual.append(boton.getText().toString());
                textoResultado.setText(entradaActual.toString());
            }
        };


        for (int id : idsBotonesNumeros) {
            findViewById(id).setOnClickListener(listenerNumero);
        }

        // listeners pour les boutons operateurs
        findViewById(R.id.botonSumar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manejarOperador(Operador.SUMAR);
            }
        });

        findViewById(R.id.botonRestar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manejarOperador(Operador.RESTAR);
            }
        });

        findViewById(R.id.botonMultiplicar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manejarOperador(Operador.MULTIPLICAR);
            }
        });

        findViewById(R.id.botonDividir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manejarOperador(Operador.DIVIDIR);
            }
        });

        // listener pour le bouton "="
        findViewById(R.id.botonIgual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });

        // listener pour le bouton "C"
        findViewById(R.id.botonC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarCalculadora();
            }
        });
    }

    //  operateurs
    private void manejarOperador(Operador operador) {
        if (entradaActual.length() > 0) {
            operando1 = Double.parseDouble(entradaActual.toString());
            entradaActual.setLength(0);
            operadorActual = operador;
            textoResultado.setText("");
        }
    }

    // calculer le resultat
    private void calcularResultado() {
        if (entradaActual.length() > 0) {
            operando2 = Double.parseDouble(entradaActual.toString());
            double resultado = 0;


            switch (operadorActual) {
                case SUMAR:
                    resultado = operando1 + operando2;
                    break;
                case RESTAR:
                    resultado = operando1 - operando2;
                    break;
                case MULTIPLICAR:
                    resultado = operando1 * operando2;
                    break;
                case DIVIDIR:
                    if (operando2 != 0) {
                        resultado = operando1 / operando2;
                    } else {
                        textoResultado.setText("Error"); // erreur diviser par 0
                        return;
                    }
                    break;
            }

            textoResultado.setText(String.valueOf(resultado));
            operando1 = resultado;
            nuevoCalculo = true;
        }
    }

    // reinisialiser la calculatrice
    private void reiniciarCalculadora() {
        entradaActual.setLength(0);
        textoResultado.setText("");
        operando1 = 0;
        operando2 = 0;
        operadorActual = null;
    }
}
