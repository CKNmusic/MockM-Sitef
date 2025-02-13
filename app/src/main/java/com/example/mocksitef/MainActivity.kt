package com.example.mocksitef

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textViewRecebido = findViewById<TextView>(R.id.texto_recebido)
        val btnConfirmar = findViewById<Button>(R.id.btnConfirmar)
        val btnCancelar = findViewById<Button>(R.id.btnCancelar)

        val extras = intent.extras
        Log.d("MainActivity", "Extras: $extras")
        val builder = StringBuilder()
        var modalidade = "0"
        var numParcelas = "0"

        if (extras != null) {
            for (key in extras.keySet()) {
                val value = when {
                    extras.containsKey(key) -> {
                        when (extras.get(key)) {
                            is String -> extras.getString(key)
                            is Int -> extras.getInt(key).toString()
                            is Boolean -> extras.getBoolean(key).toString()
                            is Double -> extras.getDouble(key).toString()
                            is Float -> extras.getFloat(key).toString()
                            else -> "Tipo desconhecido"
                        }
                    }
                    else -> "Chave não encontrada"
                }
                builder.append("$key: $value\n")

                if (key == "modalidade") {
                    if (value != null) {
                        modalidade = value
                    }
                }

                if (key == "numParcelas") {
                    if (value != null) {
                        numParcelas = value
                    }
                }
            }
        } else {
            builder.append("Nenhum dado recebido!")
        }

        textViewRecebido.text = builder.toString()

        btnConfirmar.setOnClickListener {
            val intent = Intent()

            if (modalidade == "200") {
                intent.apply {
                    putExtra("CODRESP", "200")
                    putExtra("MENSAGEM", "Operação de pagamentoCancelada")
                    putExtra("STATUS", "Aprovado")
                    putExtra("VLTROCO", "5.00")
                    putExtra("BANDEIRA", "Visa")
                    putExtra("NSU_SITEF", "987654")
                    putExtra("NSU_HOST", "321654")
                    putExtra("COD_AUTORIZACAO", "456789")
                    putExtra("NUM_PARC", "3")
                    putExtra("VIA_ESTABELECIMENTO", "Comprovante Especial")
                    putExtra("VIA_CLIENTE", "Comprovante Cliente Especial")
                }
            } else {
                intent.apply {
                    putExtra("CODRESP", "0")
                    putExtra("COMP_DADOS_CONF", "Dados de confirmação")
                    putExtra("CODTRANS", "123")
                    putExtra("TIPO_PARC", "0")
                    putExtra("VLTROCO", "0.00")
                    putExtra("REDE_AUT", "Rede Teste")
                    putExtra("BANDEIRA", "MasterCard")
                    putExtra("NSU_SITEF", "123456")
                    putExtra("NSU_HOST", "654321")
                    putExtra("COD_AUTORIZACAO", "789012")
                    putExtra("NUM_PARC", numParcelas)
                    putExtra("VIA_ESTABELECIMENTO", "Comprovante Estabelecimento")
                    putExtra("VIA_CLIENTE", "Comprovante Cliente")
                }
            }

            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        btnCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }

}