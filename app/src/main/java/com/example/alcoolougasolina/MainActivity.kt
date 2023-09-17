package com.example.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.EditText
import android.widget.Button
import android.text.TextWatcher
import android.text.Editable
import android.widget.TextView
import java.text.DecimalFormat

fun calcularPorcentagem(primeiroValor: Double, segundoValor: Double): Double {
    if (segundoValor == 0.0) {
        throw IllegalArgumentException("O segundo valor não pode ser zero.")
    }

    val porcentagem = (primeiroValor / segundoValor) * 100.0
    return porcentagem
}

class MainActivity : AppCompatActivity() {
    var percent:Double = 70.0
    var valorGasolina:Double = 0.0
    var valorAlcool:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            percent=savedInstanceState.getDouble("percent")
            valorGasolina=savedInstanceState.getDouble("valorGasolina")
            valorAlcool=savedInstanceState.getDouble("valorAlcool")
        }

        val edGasolina: EditText = findViewById(R.id.edGasolina)
        edGasolina.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Este método é chamado antes de o texto ser alterado
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Este método é chamado durante a alteração do texto
            }

            override fun afterTextChanged(s: Editable?) {
                if(s !== null && s.toString().isNotEmpty()) {
                    val textoAtual = s.toString().toDouble()
                    valorGasolina = textoAtual
                }
            }
        })

        val edAlcool: EditText = findViewById(R.id.edAlcool)
        edAlcool.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Este método é chamado antes de o texto ser alterado
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Este método é chamado durante a alteração do texto
            }

            override fun afterTextChanged(s: Editable?) {
                if(s !== null && s.toString().isNotEmpty()) {
                    val textoAtual = s.toString().toDouble()
                    valorAlcool = textoAtual
                }
            }
        })

        val switchPercent: Switch = findViewById(R.id.swPercentual)
        switchPercent.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                switchPercent.text = "75%"
                percent = 75.0
            } else {
                switchPercent.text = "70%"
                percent = 70.0
            }
        }

        val buttonCalc: Button = findViewById(R.id.btCalcular)
        buttonCalc.setOnClickListener() {
            Log.d("PDM23","No onResume, $valorAlcool gas $valorGasolina")
            val newPercent = calcularPorcentagem(valorAlcool, valorGasolina)
            val resultText: TextView = findViewById(R.id.result)
            val formato = DecimalFormat("0.00")
            val numeroFormatado = formato.format(newPercent)
            val bestOptionText: TextView = findViewById(R.id.bestOption)

            resultText.setText("O valor do alcool equivale a $numeroFormatado% do valor da gasolina")
            bestOptionText.setText(if(percent > newPercent) {
                "A melhor opção é o alcool"
            } else if(percent === newPercent) {
                "Alcool e gasolina são igualmente rentáveis"
            } else {
                "A melhor opção é a gasolina"
            })
        }
    }

    override fun onResume(){
        super.onResume()
        Log.d("PDM23","No onResume, $percent")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("percent",percent)
        outState.putDouble("valorAlcool",valorAlcool)
        outState.putDouble("valorGasolina",valorGasolina)
        super.onSaveInstanceState(outState)
    }

    override fun onStart(){
        super.onStart()
        Log.d("PDM23","No onStart, $percent")
    }

    override fun onPause(){
        super.onPause()
        Log.d("PDM23","No onPause, $percent")
    }
    override fun onStop(){
        super.onStop()
        Log.d("PDM23","No onStop, $percent")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("PDM23","No onDestroy, $percent")
    }
}