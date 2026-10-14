
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
    private lateinit var etxtMatricula: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var pbCarga: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->
            val systemBar = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            v.setPadding(
                systemBar.left,
                systemBar.top,
                systemBar.right,
                systemBar.bottom
            )
            insets
        }

        tvMensaje = findViewById(R.id.tvMensaje)
        etxtNombre = findViewById(R.id.etxtNombre)
        etxtMatricula = findViewById(R.id.etxtMatricula)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        pbCarga = findViewById(R.id.pbCarga)

        pbCarga.visibility = View.GONE

        btnRegistrar.setOnClickListener {
            registrarse()
        }

        btnLimpiar.setOnClickListener {
            limpiar()
        }
    }

    private fun registrarse() {
        val nombre = etxtNombre.text.toString().trim()
        val matricula = etxtMatricula.text.toString().trim()

        // Validar matrícula
        if (matricula.isEmpty()) {
            Toast.makeText(
                this,
                "Escribe tu matrícula, por favor",
                Toast.LENGTH_SHORT
            ).show()
            etxtMatricula.error = "Ingresa tu matrícula"
            etxtMatricula.requestFocus()
            return
        }

        if (matricula.length < 8) {
            etxtMatricula.error = "Error: matrícula incompleta"
            Toast.makeText(
                this,
                "Error: matrícula incompleta",
                Toast.LENGTH_SHORT
            ).show()
            etxtMatricula.requestFocus()
            return
        }

        // Validar nombre
        if (nombre.isEmpty()) {
            Toast.makeText(
                this,
                "Escribe tu nombre primero",
                Toast.LENGTH_SHORT
            ).show()
            etxtNombre.error = "Ingresa tu nombre"
            etxtNombre.requestFocus()
            return
        }

        if (nombre.length < 3) {
            Toast.makeText(
                this,
                "El nombre es muy corto",
                Toast.LENGTH_SHORT
            ).show()
            etxtNombre.error = "El nombre debe tener al menos 3 caracteres"
            etxtNombre.requestFocus()
            return
        }

        // Mostrar carga
        pbCarga.visibility = View.VISIBLE
        tvMensaje.text = ""
        btnRegistrar.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            pbCarga.visibility = View.GONE
            tvMensaje.text =
                "Hola, $nombre, BIENVENIDO AL CAMPUS VIRTUAL DE LA UTXJ"
            btnRegistrar.isEnabled = true
        }, 1500)
    }

    private fun limpiar() {
        etxtNombre.setText("")
        etxtMatricula.setText("")
        etxtNombre.error = null
        etxtMatricula.error = null

        pbCarga.visibility = View.GONE
        tvMensaje.text = "Escribe tus datos para registrarte"
        btnRegistrar.text = "Registrar"
        btnRegistrar.isEnabled = true

        etxtNombre.requestFocus()
    }
}
