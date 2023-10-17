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

fun calcularPorcentagem(primeiroValor: Double, segundoValor: Double, errorMessage: String): Double {
    if (segundoValor == 0.0) {
        throw IllegalArgumentException(errorMessage)
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
            val errorMessage = getString(R.string.not_zero)
            val newPercent = calcularPorcentagem(valorAlcool, valorGasolina, errorMessage)
            val resultText: TextView = findViewById(R.id.result)
            val formato = DecimalFormat("0.00")
            val numeroFormatado = formato.format(newPercent)
            val bestOptionText: TextView = findViewById(R.id.bestOption)

            val resultStartString = getString(R.string.result_start)
            val resultEndString = getString(R.string.result_end)
            val bestOptionString = getString(R.string.best_option)
            val alcoholString = getString(R.string.alcohol)
            val gasString = getString(R.string.gas)
            val sameValueString = getString(R.string.same_value)

            resultText.setText("$resultStartString $numeroFormatado% $resultEndString")
            bestOptionText.setText(if(percent > newPercent) {
                "$bestOptionString $alcoholString"
            } else if(percent === newPercent) {
                sameValueString
            } else {
                "$bestOptionString $gasString"
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