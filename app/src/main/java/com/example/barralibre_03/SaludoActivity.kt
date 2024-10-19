package com.example.barralibre_03

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SaludoActivity : AppCompatActivity() {
    private lateinit var saludoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludo)

        saludoTextView = findViewById(R.id.saludoAlUsuario) // Asegúrate de que este ID es correcto

        // Obtener el nombre del Intent
        val nombre = intent.getStringExtra("nombre") // Asegúrate de que la clave es "nombre"

        // Configurar el mensaje de bienvenida
        saludoTextView.text = "Bienvenid@, $nombre" // Asegúrate de que `nombre` no sea null
    }
}
