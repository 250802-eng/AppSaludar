package com.example.practica02

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
private lateinit var tvMensaje: TextView
private lateinit var etxtNombre: EditText
private lateinit var etxtMatrícula: EditText
private lateinit var btnRegistrar: Button
private lateinit var btnLimpiar: Button
private lateinit var pbCarga: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) {v, insets ->
            val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
            insets
        }

        tvMensaje = findViewById(R.id.tvMensaje)
        etxtNombre = findViewById(R.id.etxtNombre)
        etxtMatrícula = findViewById(R.id.etxtMatrícula)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        pbCarga = findViewById(R.id.pbCarga)

        btnRegistrar.setOnClickListener {
            registrarse()
        }
        btnLimpiar.setOnClickListener { limpiar() }

    }

    private fun registrarse(){
        val nombre = etxtNombre.text.toString().trim()
        val matricula = etxtMatrícula.text.toString().trim()

        if (matricula.isEmpty()){
            Toast.makeText(this, "Escribe tu matricula porfavor", Toast.LENGTH_SHORT).show()
            return
        }else if (matricula.length <3) {
            Toast.makeText(this, "Error: matricula invalida", Toast.LENGTH_SHORT).show()
        }

        if (nombre.isEmpty()){
            Toast.makeText(this, "Escribe tu nombre Primero", Toast.LENGTH_SHORT).show()
            return
        }else if (nombre.length <3) {
            Toast.makeText(this, "El nombre es muy corto", Toast.LENGTH_SHORT).show()
        }


        pbCarga.visibility = View.VISIBLE
        tvMensaje.text = ""
        btnRegistrar.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            pbCarga.visibility = View.GONE
            tvMensaje.text = "Hola, $nombre, BIENVENIDO AL CAMPUS VIRTUAL DE LA UTXJ "
            btnRegistrar.isEnabled = true
        }, 1500)
    }

    private fun limpiar (){
        etxtNombre.setText("")
        etxtMatrícula.setText("")
        pbCarga.visibility = View.GONE
        tvMensaje.text = "Escribe tus datos para registrarte"
        btnRegistrar.text = "Registrar"
        btnRegistrar.isEnabled = true
        etxtNombre.requestFocus()
    }
}
