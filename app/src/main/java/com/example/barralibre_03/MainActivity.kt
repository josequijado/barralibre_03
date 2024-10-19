package com.example.barralibre_03

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.content.Intent
import android.os.Handler
import android.widget.TextView
import android.view.Gravity

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.txtNombre) // Asegúrate de que el ID coincide con el XML
        sendButton = findViewById(R.id.btnAceptar) // Asegúrate de que el ID coincide con el XML

        nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendButton.isEnabled = s.toString().trim().length >= 3
                // Capitalizar correctamente las palabras
                if (!s.isNullOrEmpty()) {
                    val formattedText = capitalizeWords(s.toString())
                    if (nameEditText.text.toString() != formattedText) {
                        nameEditText.setText(formattedText)
                        nameEditText.setSelection(formattedText.length) // Para mantener el cursor al final
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        sendButton.setOnClickListener {
            val nombreUsuario = nameEditText.text.toString().trim()

            // Crear el Toast con el diseño personalizado
            val customToastLayout = layoutInflater.inflate(R.layout.custom_toast_ma, null)
            val toastText = customToastLayout.findViewById<TextView>(R.id.toastText)
            toastText.text = "Hola, $nombreUsuario"

            val customToast = Toast(applicationContext)
            customToast.duration = Toast.LENGTH_LONG
            customToast.view = customToastLayout

            // Ajustar la posición del Toast
            customToast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 500) // Cambia 200 según sea necesario

            // Mostrar el Toast
            customToast.show()

            // Deshabilitar el botón y el campo de texto
            sendButton.isEnabled = false
            nameEditText.isEnabled = false

            // Iniciar la nueva actividad después de un retraso
            Handler().postDelayed({
                val intent = Intent(this, SaludoActivity::class.java)
                intent.putExtra("nombre", nombreUsuario)
                startActivity(intent)
            }, 3500) // 3500 milisegundos = 3.5 segundos
        }
    }

    // Función para capitalizar la primera letra de cada palabra
    private fun capitalizeWords(input: String): String {
        return input.split(" ").joinToString(" ") { word ->
            word.lowercase().replaceFirstChar { char -> char.titlecase() }
        }
    }
}
