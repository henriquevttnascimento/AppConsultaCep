package com.example.appconsultacep

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.appconsultacep.api.ViaCepClient
import kotlinx.coroutines.launch

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
        val editCep = findViewById<EditText>(R.id.editCep)
        val btnConsultar = findViewById<Button>(R.id.btnConsultar)

        val txtLougradouro = findViewById<EditText>(R.id.txtLougradouro)
        val txtBairro = findViewById<EditText>(R.id.textBairro)
        val txtUF = findViewById<EditText>(R.id.textUF)
        val txtDDD = findViewById<EditText>(R.id.textDDD)
        val txtLocalidade = findViewById<EditText>(R.id.textLocalidade)
        btnConsultar.setOnClickListener {
            val cep = editCep.text.toString()
            if(cep.length != 8){
                editCep.error = "CEP inválido"
                return@setOnClickListener
            }
            lifecycleScope.launch {
                val logradouro = ViaCepClient.instance.buscarEndereco(cep)
                txtLougradouro.setText(logradouro.logradouro)
            }
            lifecycleScope.launch {
                val bairro = ViaCepClient.instance.buscarEndereco(cep)
                txtBairro.setText(bairro.bairro)
            }
            lifecycleScope.launch {
                val uf = ViaCepClient.instance.buscarEndereco(cep)
                txtUF.setText(uf.uf)
            }
            lifecycleScope.launch {
                val ddd = ViaCepClient.instance.buscarEndereco(cep)
                txtDDD.setText(ddd.ddd)
            }
            lifecycleScope.launch {
                val cidade = ViaCepClient.instance.buscarEndereco(cep)
                txtLocalidade.setText(cidade.localidade)
            }
        }

    }
    }